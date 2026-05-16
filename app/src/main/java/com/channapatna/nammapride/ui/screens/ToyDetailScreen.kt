package com.channapatna.nammapride.ui.screens

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.ui.components.*
import com.channapatna.nammapride.ui.theme.MotionTokens
import com.channapatna.nammapride.ui.theme.Spacing
import com.channapatna.nammapride.viewmodel.ToyDetailViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ToyDetailScreen(
    toyId: String,
    onArtisanClick: (String) -> Unit,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: ToyDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(toyId) { viewModel.load(toyId) }

    Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        when (uiState.toyState) {
            is UiState.Loading -> LoadingView()
            is UiState.Error   -> ErrorView((uiState.toyState as UiState.Error).message)
            is UiState.Success -> {
                val toy = (uiState.toyState as UiState.Success).data
                
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    // Header Image
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                    ) {
                        // Background gradient behind transparent toy
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.radialGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme.primaryContainer,
                                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                                        )
                                    )
                                )
                        )

                        // Cinematic Zoom + Parallax
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .graphicsLayer {
                                    val speed = 0.5f
                                    translationY = scrollState.value * speed
                                    scaleX = 1.05f + (scrollState.value * 0.0001f)
                                    scaleY = 1.05f + (scrollState.value * 0.0001f)
                                }
                                .then(
                                    with(sharedTransitionScope) {
                                        Modifier.sharedElement(
                                            rememberSharedContentState(key = "image-${toy.toyId}"),
                                            animatedVisibilityScope = animatedVisibilityScope
                                        )
                                    }
                                )
                        ) {
                            ToyImage(
                                toyId = toy.toyId,
                                imageUrl = toy.imageUrl,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(24.dp),
                                contentDescription = toy.name
                            )
                        }

                        // Floating Back Button
                        IconButton(
                            onClick = onBack,
                            modifier = Modifier
                                .padding(Spacing.lg)
                                .statusBarsPadding()
                                .size(40.dp)
                                .background(Color.Black.copy(alpha = 0.2f), CircleShape)
                        ) {
                            Icon(Icons.Default.ArrowBack, null, tint = Color.White)
                        }
                    }

                    Column(
                        Modifier.padding(Spacing.lg),
                        verticalArrangement = Arrangement.spacedBy(Spacing.lg)
                    ) {
                        // Title row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(Modifier.weight(1f)) {
                                Text(
                                    toy.name,
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Text(
                                    toy.category,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            
                            IconButton(onClick = { 
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                viewModel.toggleFavorite(toy) 
                            }) {
                                Icon(
                                    imageVector = if (toy.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "Favorite",
                                    tint = if (toy.isFavorite) Color(0xFFFF4081) else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        }

                        if (toy.isAuthentic) VerifiedBadge()

                        SectionHeader(title = "The Craft", subtitle = "A 200-year legacy in every piece")
                        Surface(
                            shape  = RoundedCornerShape(20.dp),
                            color  = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                        ) {
                            Text(
                                toy.processDescription,
                                modifier = Modifier.padding(Spacing.md),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 24.sp
                            )
                        }

                        SectionHeader(title = "Specifications", subtitle = "Authentic product details")
                        Surface(
                            shape  = RoundedCornerShape(20.dp),
                            color  = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ) {
                            Column(Modifier.padding(horizontal = Spacing.md)) {
                                Spacer(Modifier.height(Spacing.sm))
                                InfoRow("Toy ID", toy.toyId)
                                InfoRow("Material", toy.material)
                                InfoRow("Verification", toy.verificationCode)
                                InfoRow("Price", toy.price)
                            }
                        }

                        if (uiState.artisanState is UiState.Success) {
                            val artisan = (uiState.artisanState as UiState.Success).data
                            SectionHeader(title = "Crafted By", subtitle = "Meet the master artisan")
                            ArtisanCard(
                                artisan = artisan, 
                                sharedTransitionScope = sharedTransitionScope,
                                animatedVisibilityScope = animatedVisibilityScope,
                                onClick = { onArtisanClick(artisan.artisanId) }
                            )
                        }
                        
                        Spacer(Modifier.height(100.dp))
                    }
                }

                // Sticky CTA
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(Spacing.lg)
                        .navigationBarsPadding()
                ) {
                    ChannapatnaButton(
                        text = "Locate Workshop",
                        icon = Icons.Default.LocationOn,
                        onClick = { if (uiState.artisanState is UiState.Success) onArtisanClick((uiState.artisanState as UiState.Success).data.artisanId) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            else -> {}
        }
    }
}
