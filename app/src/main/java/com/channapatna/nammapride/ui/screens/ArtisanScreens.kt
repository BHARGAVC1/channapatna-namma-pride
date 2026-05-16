package com.channapatna.nammapride.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.ui.components.*
import com.channapatna.nammapride.ui.theme.Spacing
import com.channapatna.nammapride.viewmodel.ArtisanViewModel

// ── Artisan List ──────────────────────────────────────────────────────────────
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ArtisanListScreen(
    onArtisanClick: (String) -> Unit,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: ArtisanViewModel = hiltViewModel()
) {
    val state by viewModel.artisansState.collectAsStateWithLifecycle()

    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AppTopBar("Artisans", onBack)
        AnimatedContent(
            targetState   = state,
            transitionSpec = { fadeIn(tween(250)) togetherWith fadeOut(tween(150)) },
            label         = "artisanListState"
        ) { s ->
            when (s) {
                is UiState.Loading -> ShimmerToyList(Modifier.padding(16.dp))
                is UiState.Error   -> ErrorView(s.message)
                is UiState.Empty   -> PremiumEmptyState(
                    title = "No artisans found",
                    subtitle = "We couldn't load the artisan directory.",
                    icon = Icons.Default.PeopleOutline
                )
                is UiState.Success -> {
                    val artisans = s.data
                    LazyColumn(
                        contentPadding      = PaddingValues(horizontal = Spacing.md, vertical = Spacing.md),
                        verticalArrangement = Arrangement.spacedBy(Spacing.md)
                    ) {
                        item {
                            Text(
                                "${artisans.size} registered artisans",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                            )
                            Spacer(Modifier.height(6.dp))
                        }
                        itemsIndexed(artisans, key = { _, a -> a.artisanId }) { _, artisan ->
                            ArtisanCard(
                                artisan = artisan,
                                onClick = { onArtisanClick(artisan.artisanId) },
                                sharedTransitionScope = sharedTransitionScope,
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                        }
                    }
                }
                else -> LoadingView()
            }
        }
    }
}

// ── Artisan Profile ───────────────────────────────────────────────────────────
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ArtisanProfileScreen(
    artisanId: String,
    onMapClick: () -> Unit,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: ArtisanViewModel = hiltViewModel()
) {
    val uiState by viewModel.selectedState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    LaunchedEffect(artisanId) { viewModel.loadArtisan(artisanId) }

    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AppTopBar("Artisan Profile", onBack)
        when (uiState.artisanState) {
            is UiState.Loading -> LoadingView()
            is UiState.Error -> ErrorView((uiState.artisanState as UiState.Error).message)
            is UiState.Success -> {
                val artisan = (uiState.artisanState as UiState.Success).data

                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(Spacing.lg),
                    verticalArrangement = Arrangement.spacedBy(Spacing.lg)
                ) {
                    // Clean Profile Header
                    Surface(
                        shape  = RoundedCornerShape(24.dp),
                        color  = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                    ) {
                        Row(
                            Modifier.padding(Spacing.lg),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(Spacing.md)
                        ) {
                            Box(
                                modifier = with(sharedTransitionScope) {
                                    Modifier.sharedElement(
                                        rememberSharedContentState(key = "artisan-avatar-${artisan.artisanId}"),
                                        animatedVisibilityScope = animatedVisibilityScope
                                    )
                                }
                            ) {
                                ArtisanInitialsAvatar(artisan.name, 80, photoUrl = artisan.photoUrl)
                            }
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(artisan.name, style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground)
                                Text(artisan.craftType, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(Spacing.xs)) {
                                    Icon(Icons.Default.LocationOn, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(14.dp))
                                    Text(artisan.locationText, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                        }
                    }

                    // Stat cards
                    Row(horizontalArrangement = Arrangement.spacedBy(Spacing.md)) {
                        StatCard("Experience", "${artisan.experienceYears} Years", Modifier.weight(1f))
                        StatCard("Craft", "Traditional", Modifier.weight(1f))
                    }

                    // Bio
                    SectionHeader(title = "The Legacy", subtitle = "Master craftsman from Channapatna")
                    Surface(
                        shape  = RoundedCornerShape(20.dp),
                        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)),
                        color  = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ) {
                        Text(
                            artisan.bio, 
                            modifier = Modifier.padding(Spacing.md),
                            style = MaterialTheme.typography.bodyMedium, 
                            color = MaterialTheme.colorScheme.onSurfaceVariant, 
                            lineHeight = 24.sp
                        )
                    }

                    // Details
                    Surface(
                        shape  = RoundedCornerShape(20.dp),
                        color  = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ) {
                        Column(Modifier.padding(horizontal = Spacing.md)) {
                            Spacer(Modifier.height(Spacing.sm))
                            InfoRow("Artisan ID", artisan.artisanId)
                            InfoRow("Workshop", artisan.locationText)
                            InfoRow("Years Active", "${artisan.experienceYears} years")
                        }
                    }

                    // Actions
                    Row(horizontalArrangement = Arrangement.spacedBy(Spacing.md)) {
                        OutlinedButton(
                            onClick = onMapClick,
                            modifier = Modifier.weight(1f).height(56.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(Icons.Outlined.Map, null)
                            Spacer(Modifier.width(8.dp))
                            Text("Preview Map")
                        }
                        
                        Button(
                            onClick = {
                                val uri = Uri.parse("google.navigation:q=${artisan.latitude},${artisan.longitude}")
                                val intent = Intent(Intent.ACTION_VIEW, uri)
                                intent.setPackage("com.google.android.apps.maps")
                                context.startActivity(intent)
                            },
                            modifier = Modifier.weight(1f).height(56.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(Icons.Default.Directions, null)
                            Spacer(Modifier.width(8.dp))
                            Text("Navigate")
                        }
                    }
                    Spacer(Modifier.height(Spacing.md))
                }
            }
            else -> {}
        }
    }
}

@Composable
private fun StatCard(label: String, value: String, modifier: Modifier) {
    Surface(
        modifier = modifier,
        shape    = RoundedCornerShape(20.dp),
        color    = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        border   = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
    ) {
        Column(Modifier.padding(Spacing.md), verticalArrangement = Arrangement.spacedBy(Spacing.xs)) {
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f))
            Text(value, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
        }
    }
}
