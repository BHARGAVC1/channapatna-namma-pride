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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
                    subtitle = "We couldn't load the artisan directory. Check your connection.",
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
                                "${artisans.size} registered artisan${if (artisans.size != 1) "s" else ""}",
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

    Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        when (uiState.artisanState) {
            is UiState.Loading -> LoadingView()
            is UiState.Error -> ErrorView((uiState.artisanState as UiState.Error).message)
            is UiState.Success -> {
                val artisan = (uiState.artisanState as UiState.Success).data

                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    // Premium Hero Header with Parallax Portrait
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(340.dp)
                    ) {
                        val sharedModifier = with(sharedTransitionScope) {
                            Modifier.sharedElement(
                                rememberSharedContentState(key = "artisan-avatar-${artisan.artisanId}"),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    translationY = scrollState.value * 0.4f
                                    scaleX = 1f + (scrollState.value * 0.0005f)
                                    scaleY = 1f + (scrollState.value * 0.0005f)
                                }
                                .then(sharedModifier)
                        ) {
                            ArtisanImage(artisan.photoUrl, Modifier.fillMaxSize())
                        }

                        // Scrim for readability
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color.Transparent, Color.Black.copy(0.7f)),
                                        startY = 400f
                                    )
                                )
                        )

                        // Floating Back Button
                        IconButton(
                            onClick = onBack,
                            modifier = Modifier
                                .padding(Spacing.lg)
                                .statusBarsPadding()
                                .size(40.dp)
                                .background(Color.Black.copy(0.3f), CircleShape)
                        ) {
                            Icon(Icons.Default.ArrowBack, null, tint = Color.White)
                        }

                        // Title Overlay
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(Spacing.lg)
                        ) {
                            Text(
                                artisan.name,
                                style = MaterialTheme.typography.headlineLarge,
                                color = Color.White
                            )
                            Text(
                                artisan.craftType,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White.copy(0.8f)
                            )
                        }
                    }

                    Column(
                        Modifier.padding(Spacing.lg),
                        verticalArrangement = Arrangement.spacedBy(Spacing.lg)
                    ) {
                        // Quick Stats
                        Row(horizontalArrangement = Arrangement.spacedBy(Spacing.md)) {
                            StatCard("Experience", "${artisan.experienceYears} Years", Modifier.weight(1f))
                            StatCard("Origin", "Channapatna", Modifier.weight(1f))
                        }

                        // The Story / Legacy
                        SectionHeader(title = "The Legacy", subtitle = "Heritage passed down through generations")
                        Surface(
                            shape = RoundedCornerShape(24.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                        ) {
                            Text(
                                artisan.bio,
                                modifier = Modifier.padding(Spacing.lg),
                                style = MaterialTheme.typography.bodyLarge,
                                lineHeight = 28.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // Workshop Info
                        SectionHeader(title = "Workshop Details", subtitle = "Visit the source of the craft")
                        Surface(
                            shape = RoundedCornerShape(24.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Column(Modifier.padding(horizontal = Spacing.md)) {
                                Spacer(Modifier.height(Spacing.sm))
                                InfoRow("Artisan ID", artisan.artisanId)
                                InfoRow("Location", artisan.locationText)
                                InfoRow("Coord.", "${artisan.latitude}, ${artisan.longitude}")
                            }
                        }

                        // Actions
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(Spacing.md)
                        ) {
                            // Secondary In-App Map
                            OutlinedButton(
                                onClick = onMapClick,
                                modifier = Modifier.weight(1f).height(56.dp),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Icon(Icons.Outlined.Map, null)
                                Spacer(Modifier.width(8.dp))
                                Text("Preview Map")
                            }

                            // Primary Navigation Call
                            Button(
                                onClick = {
                                    val uri = Uri.parse("google.navigation:q=${artisan.latitude},${artisan.longitude}")
                                    val intent = Intent(Intent.ACTION_VIEW, uri)
                                    intent.setPackage("com.google.android.apps.maps")
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.weight(1f).height(56.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                            ) {
                                Icon(Icons.Default.Directions, null)
                                Spacer(Modifier.width(8.dp))
                                Text("Navigate")
                            }
                        }
                        
                        Spacer(Modifier.height(40.dp))
                    }
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
        shape    = RoundedCornerShape(24.dp),
        color    = MaterialTheme.colorScheme.surfaceVariant,
        border   = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
        shadowElevation = 2.dp
    ) {
        Column(Modifier.padding(Spacing.md), verticalArrangement = Arrangement.spacedBy(Spacing.xs)) {
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f))
            Text(value, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
        }
    }
}
