package com.channapatna.nammapride.ui.screens

import android.content.Intent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.ui.components.*
import com.channapatna.nammapride.ui.theme.Spacing
import com.channapatna.nammapride.viewmodel.ToyDetailViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ResultScreen(
    toyId: String,
    onArtisanClick: (String) -> Unit,
    onBack: () -> Unit,
    viewModel: ToyDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(toyId) { viewModel.load(toyId) }

    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AppTopBar("Verification Result", onBack)
        when (uiState.toyState) {
            is UiState.Loading -> LoadingView()
            is UiState.Error   -> ErrorView((uiState.toyState as UiState.Error).message)
            is UiState.Success -> {
                val toy = (uiState.toyState as UiState.Success).data

                // Staggered entrance animations
                var heroVisible    by remember { mutableStateOf(false) }
                var detailsVisible by remember { mutableStateOf(false) }
                var artisanVisible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    heroVisible    = true
                    kotlinx.coroutines.delay(180)
                    detailsVisible = true
                    kotlinx.coroutines.delay(160)
                    artisanVisible = true
                }

                Box(Modifier.fillMaxSize()) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(Spacing.lg),
                        verticalArrangement = Arrangement.spacedBy(Spacing.md)
                    ) {
                        // Hero verified card
                        AnimatedVisibility(
                            visible = heroVisible,
                            enter   = scaleIn(
                                initialScale  = 0.85f,
                                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                            ) + fadeIn(tween(350))
                        ) {
                            VerifiedHeroCard(toyName = toy.name)
                        }

                        // Toy image
                        AnimatedVisibility(
                            visible = detailsVisible,
                            enter   = slideInVertically(initialOffsetY = { it / 3 }, animationSpec = tween(300)) + fadeIn(tween(300))
                        ) {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(260.dp)
                                    .clip(RoundedCornerShape(28.dp))
                            ) {
                                ToyImage(toy.toyId, toy.imageUrl, Modifier.fillMaxSize())
                            }
                        }

                        // Details
                        AnimatedVisibility(
                            visible = detailsVisible,
                            enter   = slideInVertically(initialOffsetY = { it / 3 }, animationSpec = tween(320, 50)) + fadeIn(tween(300))
                        ) {
                            Surface(
                                shape  = RoundedCornerShape(24.dp),
                                border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
                                color  = MaterialTheme.colorScheme.surfaceVariant,
                                shadowElevation = 2.dp
                            ) {
                                Column(Modifier.padding(horizontal = Spacing.md)) {
                                    Spacer(Modifier.height(Spacing.sm))
                                    Text(toy.name, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurface)
                                    Text(toy.category, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    Spacer(Modifier.height(Spacing.sm))
                                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), thickness = 0.5.dp)
                                    InfoRow("Toy ID", toy.toyId)
                                    InfoRow("Material", toy.material)
                                    InfoRow("Price", toy.price)
                                    InfoRow("Verification Code", toy.verificationCode)
                                    InfoRow("Process", toy.processDescription)
                                }
                            }
                        }

                        // Artisan card
                        AnimatedVisibility(
                            visible = artisanVisible && uiState.artisanState is UiState.Success,
                            enter   = slideInVertically(initialOffsetY = { it / 3 }, animationSpec = tween(320, 80)) + fadeIn(tween(300))
                        ) {
                            if (uiState.artisanState is UiState.Success) {
                                val artisan = (uiState.artisanState as UiState.Success).data
                                Column(verticalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                                    SectionHeader(title = "Crafted By", subtitle = "Meet the master artisan")
                                    ArtisanCard(artisan = artisan, onClick = { onArtisanClick(artisan.artisanId) })
                                }
                            }
                        }
                        
                        Spacer(Modifier.height(Spacing.md))

                        // Share + artisan action row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(Spacing.sm)
                        ) {
                            // Share this toy's story
                            OutlinedButton(
                                onClick = {
                                    val shareText = buildString {
                                        appendLine("✅ Verified authentic Channapatna toy!")
                                        appendLine()
                                        appendLine("🪆 ${toy.name}")
                                        appendLine("🎨 ${toy.material}")
                                        appendLine("🔖 ID: ${toy.toyId}")
                                        appendLine("🛡 Code: ${toy.verificationCode}")
                                        appendLine()
                                        append("Verified with Namma Pride app — supporting Channapatna artisans.")
                                    }
                                    val sendIntent = android.content.Intent(android.content.Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(android.content.Intent.EXTRA_TEXT, shareText)
                                    }
                                    context.startActivity(android.content.Intent.createChooser(sendIntent, "Share toy story"))
                                },
                                modifier = Modifier.weight(1f).height(48.dp),
                                shape    = RoundedCornerShape(16.dp)
                            ) {
                                Icon(Icons.Default.Share, null, modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(6.dp))
                                Text("Share")
                            }

                            // View artisan button  
                            if (uiState.artisanState is UiState.Success) {
                                val artisan = (uiState.artisanState as UiState.Success).data
                                ChannapatnaButton(
                                    text     = "Meet Artisan",
                                    icon     = Icons.Default.Person,
                                    onClick  = { onArtisanClick(artisan.artisanId) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        Spacer(Modifier.height(Spacing.md))
                    }
                }
            }
            else -> {}
        }
    }
}

@Composable
private fun VerifiedHeroCard(toyName: String) {
    // Pulsing glow
    val pulse by rememberInfiniteTransition(label = "glow").animateFloat(
        initialValue  = 0.85f,
        targetValue   = 1f,
        animationSpec = infiniteRepeatable(tween(1400, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label         = "glowScale"
    )
    Surface(
        modifier = Modifier.scale(1f),
        shape    = RoundedCornerShape(18.dp),
        color    = MaterialTheme.colorScheme.primaryContainer,
        border   = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
    ) {
        Column(
            Modifier.fillMaxWidth().padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                // Glow ring
                Box(
                    Modifier
                        .size((56 * pulse).dp)
                        .clip(androidx.compose.foundation.shape.CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f * pulse))
                )
                Icon(Icons.Default.VerifiedUser, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(48.dp))
            }
            Text("Genuine Channapatna Toy", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
            Text(toyName, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
            Text("This product is verified as an authentic handcrafted item.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f))
        }
    }
}
