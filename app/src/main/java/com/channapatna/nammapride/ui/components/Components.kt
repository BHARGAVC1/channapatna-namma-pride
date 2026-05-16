package com.channapatna.nammapride.ui.components

import android.os.Build
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.channapatna.nammapride.R
import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.Toy
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.graphicsLayer
import com.channapatna.nammapride.ui.theme.MotionTokens
import com.channapatna.nammapride.ui.theme.Spacing

@Composable
fun AppTopBar(title: String, onBack: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBack != null) {
            IconButton(
                onClick  = onBack,
                modifier = Modifier
                    .size(36.dp)
                    .semantics { contentDescription = "Navigate back" }
            ) {
                Icon(Icons.Default.ArrowBack, null, tint = MaterialTheme.colorScheme.onBackground)
            }
            Spacer(Modifier.width(8.dp))
        }
        Text(
            title,
            style    = MaterialTheme.typography.titleLarge,
            color    = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
    HorizontalDivider(color = MaterialTheme.colorScheme.outline, thickness = 0.5.dp)
}

@Composable
fun OfflineBanner() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        Row(
            Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Default.CloudOff,
                null,
                tint = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                "Offline Mode • Viewing cached data",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary, strokeWidth = 2.dp)
    }
}

@Composable
fun ErrorView(message: String, modifier: Modifier = Modifier, onRetry: (() -> Unit)? = null) {
    Box(modifier.fillMaxSize().padding(32.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Icon(Icons.Default.Warning, "Warning", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(40.dp))
            Text(message, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
            if (onRetry != null) {
                Button(onClick = onRetry) {
                    Text("Retry")
                }
            }
        }
    }
}

@Composable
fun ShimmerToyList(modifier: Modifier = Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        repeat(5) { ShimmerToyItem() }
    }
}

@Composable
fun ShimmerToyItem() {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val alpha by transition.animateFloat(
        initialValue  = 0.3f,
        targetValue   = 0.7f,
        animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing), RepeatMode.Reverse),
        label = "alpha"
    )
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape    = RoundedCornerShape(24.dp),
        color    = MaterialTheme.colorScheme.surface,
        border   = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
        shadowElevation = 2.dp
    ) {
        Row(Modifier.padding(Spacing.md), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = alpha))
            )
            Spacer(Modifier.width(Spacing.md))
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                Box(
                    Modifier
                        .fillMaxWidth(0.6f)
                        .height(20.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = alpha))
                )
                Box(
                    Modifier
                        .fillMaxWidth(0.4f)
                        .height(14.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = alpha))
                )
            }
        }
    }
}

@Composable
fun ArtisanImage(url: String?, modifier: Modifier = Modifier) {
    if (!url.isNullOrBlank()) {
        AsyncImage(
            model = url,
            contentDescription = "Artisan portrait",
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    } else {
        Box(
            modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, null, tint = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun ArtisanInitialsAvatar(name: String, size: Int = 48, photoUrl: String? = null) {
    Box(
        Modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
        contentAlignment = Alignment.Center
    ) {
        if (!photoUrl.isNullOrBlank()) {
            ArtisanImage(photoUrl, Modifier.fillMaxSize())
        } else {
            val initials = name.split(" ").take(2).mapNotNull { it.firstOrNull()?.uppercaseChar() }.joinToString("")
            Text(initials, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun VerifiedBadge() {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }
    androidx.compose.animation.AnimatedVisibility(
        visible = visible,
        enter   = scaleIn(initialScale = 0.5f, animationSpec = spring(Spring.DampingRatioMediumBouncy)) + fadeIn()
    ) {
        Row(
            modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)).padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(Icons.Default.Verified, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
            Text("Verified Authentic", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        }
    }
}

private fun toyImageRes(toyId: String): Int? = when (toyId) {
    "T001" -> R.drawable.toy_t001
    "T002" -> R.drawable.toy_t002
    "T003" -> R.drawable.toy_t003
    "T004" -> R.drawable.toy_t004
    "T005" -> R.drawable.toy_t005
    "T006" -> R.drawable.toy_t006
    else   -> null
}

@Composable
fun ToyImage(toyId: String, imageUrl: String? = null, modifier: Modifier = Modifier) {
    val resId = toyImageRes(toyId)
    if (resId != null) {
        Image(
            painter            = painterResource(resId),
            contentDescription = "Handcrafted Toy $toyId",
            modifier           = modifier,
            contentScale       = ContentScale.Fit
        )
    } else if (!imageUrl.isNullOrBlank()) {
        AsyncImage(
            model = coil.request.ImageRequest.Builder(androidx.compose.ui.platform.LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Handcrafted Toy $toyId",
            modifier = modifier,
            contentScale = ContentScale.Fit
        )
    } else {
        Box(
            modifier = modifier.background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.SmartToy,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                modifier = Modifier.size(64.dp)
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ToyCard(
    toy: Toy,
    onFavoriteToggle: (() -> Unit)? = null,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedVisibilityScope: AnimatedVisibilityScope? = null,
    onClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current
    Surface(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        color    = MaterialTheme.colorScheme.surfaceVariant,
        shape    = RoundedCornerShape(24.dp),
        border   = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
        shadowElevation = 4.dp
    ) {
        Row(Modifier.padding(Spacing.md), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .then(
                        if (sharedTransitionScope != null && animatedVisibilityScope != null) {
                            with(sharedTransitionScope) {
                                Modifier.sharedElement(
                                    rememberSharedContentState(key = "image-${toy.toyId}"),
                                    animatedVisibilityScope = animatedVisibilityScope
                                )
                            }
                        } else Modifier
                    )
            ) { ToyImage(toy.toyId, toy.imageUrl, Modifier.fillMaxSize()) }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(toy.name, style = MaterialTheme.typography.titleMedium)
                Text(toy.category, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(toy.price, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            }
            if (onFavoriteToggle != null) {
                IconButton(onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onFavoriteToggle()
                }) {
                    Icon(
                        imageVector = if (toy.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (toy.isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Icon(Icons.Default.ChevronRight, null, tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f))
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ArtisanCard(
    artisan: Artisan,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedVisibilityScope: AnimatedVisibilityScope? = null,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        color    = MaterialTheme.colorScheme.surfaceVariant,
        shape    = RoundedCornerShape(24.dp),
        border   = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
        shadowElevation = 4.dp
    ) {
        Row(Modifier.padding(Spacing.md), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = if (sharedTransitionScope != null && animatedVisibilityScope != null) {
                    with(sharedTransitionScope) {
                        Modifier.sharedElement(
                            rememberSharedContentState(key = "artisan-avatar-${artisan.artisanId}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                    }
                } else Modifier
            ) {
                ArtisanInitialsAvatar(artisan.name, size = 52, photoUrl = artisan.photoUrl)
            }
            Spacer(Modifier.width(Spacing.md))
            Column(Modifier.weight(1f)) {
                Text(artisan.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(artisan.craftType, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        artisan.locationText,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                }
            }
            Icon(Icons.Default.ChevronRight, null, tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f))
        }
    }
}

@Composable
fun CategoryChip(label: String) {
    Box(Modifier.clip(RoundedCornerShape(20.dp)).background(MaterialTheme.colorScheme.surfaceVariant).padding(horizontal = 9.dp, vertical = 3.dp)) {
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column(Modifier.fillMaxWidth().padding(vertical = Spacing.sm)) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f))
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), thickness = 0.5.dp)
}

@Composable
fun PremiumEmptyState(
    title: String,
    subtitle: String,
    icon: ImageVector,
    actionText: String? = null,
    onAction: (() -> Unit)? = null
) {
    val infiniteTransition = rememberInfiniteTransition(label = "empty")
    val floatAnim by infiniteTransition.animateFloat(
        initialValue = -12f,
        targetValue = 12f,
        animationSpec = infiniteRepeatable(tween(2000, easing = LinearOutSlowInEasing), RepeatMode.Reverse),
        label = "float"
    )

    Box(Modifier.fillMaxSize().padding(Spacing.xxl), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            Box(contentAlignment = Alignment.Center) {
                // Background glow
                Box(
                    Modifier
                        .size(140.dp)
                        .then(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) Modifier.blur(50.dp) else Modifier)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f), CircleShape)
                )
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                    modifier = Modifier
                        .size(90.dp)
                        .graphicsLayer { translationY = floatAnim }
                )
            }
            Text(
                title, 
                style = MaterialTheme.typography.titleLarge, 
                textAlign = TextAlign.Center,
                fontFamily = com.channapatna.nammapride.ui.theme.FrauncesFontFamily
            )
            Text(
                subtitle,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
            if (actionText != null && onAction != null) {
                Spacer(Modifier.height(Spacing.sm))
                ChannapatnaButton(text = actionText, onClick = onAction)
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, subtitle: String? = null, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth().padding(bottom = Spacing.sm)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        if (subtitle != null) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun ActionCard(
    label: String,
    sub: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .heightIn(min = 64.dp)
            .clickable(
                onClick = onClick,
                onClickLabel = "Open $label"
            ),
        shape    = RoundedCornerShape(24.dp),
        color    = MaterialTheme.colorScheme.surfaceVariant,
        border   = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
        shadowElevation = 4.dp
    ) {
        Row(
            Modifier.padding(Spacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm)
        ) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                modifier = Modifier.size(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
            Column {
                Text(label, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Text(sub, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun ChannapatnaButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    isLoading: Boolean = false,
    enabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = MotionTokens.smoothSpring(),
        label = "scale"
    )

    Button(
        onClick = onClick,
        modifier = modifier
            .height(56.dp)
            .graphicsLayer { 
                scaleX = scale
                scaleY = scale
            },
        enabled = enabled && !isLoading,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 2.dp
        ),
        interactionSource = interactionSource
    ) {
        if (isLoading) {
            CircularProgressIndicator(Modifier.size(24.dp), color = MaterialTheme.colorScheme.onSecondary, strokeWidth = 2.dp)
        } else {
            if (icon != null) {
                Icon(icon, null, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(Spacing.sm))
            }
            Text(text, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
        }
    }
}
