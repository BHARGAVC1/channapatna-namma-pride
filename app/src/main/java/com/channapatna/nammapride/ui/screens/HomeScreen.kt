package com.channapatna.nammapride.ui.screens

import androidx.camera.core.*
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.data.local.entity.Toy
import com.channapatna.nammapride.ui.components.ToyImage
import com.channapatna.nammapride.ui.components.ArtisanInitialsAvatar
import com.channapatna.nammapride.ui.components.ActionCard
import com.channapatna.nammapride.ui.components.OfflineBanner
import com.channapatna.nammapride.ui.components.SectionHeader
import com.channapatna.nammapride.ui.theme.Spacing
import com.channapatna.nammapride.viewmodel.HomeViewModel
import com.google.accompanist.permissions.*
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors

@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalGetImage
@Composable
fun HomeScreen(
    onVerified: (String) -> Unit,
    onCatalogClick: () -> Unit,
    onStoryClick: () -> Unit,
    onArtisansClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showScanner  by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(uiState.verifyState) {
        if (uiState.verifyState is UiState.Success) {
            // Confirm haptic with LongPress fallback
            try {
                haptic.performHapticFeedback(HapticFeedbackType.Confirm)
            } catch (e: Exception) {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            }
            onVerified((uiState.verifyState as UiState.Success).data.toy.toyId)
            viewModel.reset()
        }
    }

    DisposableEffect(Unit) {
        onDispose { showScanner = false }
    }

    val cameraPermission = rememberPermissionState(android.Manifest.permission.CAMERA)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            AnimatedHeader(scrollState)
            
            Spacer(Modifier.height(40.dp)) // Space for floating search bar

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Spacing.lg),
                verticalArrangement = Arrangement.spacedBy(Spacing.lg)
            ) {
                if (uiState.isOffline) {
                    OfflineBanner()
                }

                VerificationCard(
                    toyId          = uiState.toyId,
                    verifyState    = uiState.verifyState,
                    showScanner    = showScanner,
                    onToyIdChange  = viewModel::onToyIdChange,
                    onVerify       = viewModel::verify,
                    onReset        = viewModel::reset,
                    onToggleScanner = {
                        if (cameraPermission.status.isGranted) showScanner = !showScanner
                        else cameraPermission.launchPermissionRequest()
                    }
                )

                if (!cameraPermission.status.isGranted && cameraPermission.status.shouldShowRationale) {
                    PermissionRationaleCard(onRequest = { cameraPermission.launchPermissionRequest() })
                }

                if (uiState.verifyState is UiState.Error) {
                    ErrorBanner(message = (uiState.verifyState as UiState.Error).message)
                }

                if (showScanner) {
                    ScannerCard(
                        onCodeScanned = { code ->
                            showScanner = false
                            viewModel.onToyIdChange(code)
                            viewModel.verify()
                        },
                        onClose = { showScanner = false }
                    )
                }

                ArtisanSpotlightSection(
                    state = uiState.artisanSpotlight,
                    onArtisanClick = { _ -> onArtisansClick() }
                )

                FeaturedToysSection(
                    state = uiState.featuredToys,
                    onToyClick = { _ -> onCatalogClick() }
                )

                SectionHeader(title = "Explore", subtitle = "Discover the heritage of Channapatna")
                Row(horizontalArrangement = Arrangement.spacedBy(Spacing.md)) {
                    ActionCard("Catalog", "Browse toys", Icons.Outlined.GridView, Modifier.weight(1f), onCatalogClick)
                    ActionCard("Artisans", "Meet makers", Icons.Outlined.People, Modifier.weight(1f), onArtisansClick)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(Spacing.md)) {
                    ActionCard("Saved", "Your favorites", Icons.Outlined.FavoriteBorder, Modifier.weight(1f), onFavoritesClick)
                    ActionCard("The Story", "Heritage", Icons.Outlined.AutoStories, Modifier.weight(1f), onStoryClick)
                }
                Spacer(Modifier.height(120.dp)) // Extra space for bottom nav
            }
        }

        // Floating & Pinned Search Bar (Step 7)
        val isPinned by remember { derivedStateOf { scrollState.value > 400 } }
        val searchOffset by animateDpAsState(
            targetValue = if (isPinned) 0.dp else 190.dp,
            label = "searchOffset"
        )
        val searchPadding by animateDpAsState(
            targetValue = if (isPinned) 0.dp else Spacing.lg,
            label = "searchPadding"
        )
        
        val density = androidx.compose.ui.platform.LocalDensity.current
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    translationY = with(density) { searchOffset.toPx() }
                }
                .padding(horizontal = searchPadding)
                .statusBarsPadding()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .then(if (!isPinned) Modifier.shadow(8.dp, RoundedCornerShape(22.dp)) else Modifier),
                shape = if (isPinned) RoundedCornerShape(0.dp) else RoundedCornerShape(22.dp),
                color = MaterialTheme.colorScheme.surface,
                border = if (isPinned) BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)) else null
            ) {
                OutlinedTextField(
                    value = uiState.toyId,
                    onValueChange = viewModel::onToyIdChange,
                    modifier = Modifier.fillMaxSize(),
                    placeholder = { Text("Search handcrafted toys…", style = MaterialTheme.typography.bodyMedium) },
                    leadingIcon = { Icon(Icons.Default.Search, null, tint = MaterialTheme.colorScheme.primary) },
                    trailingIcon = {
                        if (uiState.toyId.isNotEmpty()) {
                            IconButton(onClick = { viewModel.onToyIdChange("") }) {
                                Icon(Icons.Default.Close, null)
                            }
                        }
                    },
                    shape = if (isPinned) RoundedCornerShape(0.dp) else RoundedCornerShape(22.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    singleLine = true
                )
            }
        }
    }
}

@Composable
private fun ArtisanSpotlightSection(
    state: UiState<List<com.channapatna.nammapride.data.local.entity.Artisan>>,
    onArtisanClick: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.sm)) {
        SectionHeader(title = "Meet the Makers", subtitle = "Master artisans from the toy town")
        
        when (state) {
            is UiState.Success -> {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.md),
                    contentPadding = PaddingValues(end = Spacing.lg)
                ) {
                    items(state.data) { artisan ->
                        Column(
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { onArtisanClick(artisan.artisanId) },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(Spacing.xs)
                        ) {
                            ArtisanInitialsAvatar(
                                name = artisan.name,
                                size = 64,
                                photoUrl = artisan.photoUrl
                            )
                            Text(
                                text = artisan.name.split(" ").last(),
                                style = MaterialTheme.typography.labelMedium,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                maxLines = 1,
                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
            is UiState.Loading -> {
                Row(horizontalArrangement = Arrangement.spacedBy(Spacing.md)) {
                    repeat(4) {
                        Box(Modifier.size(64.dp).clip(androidx.compose.foundation.shape.CircleShape).background(MaterialTheme.colorScheme.surfaceVariant))
                    }
                }
            }
            else -> {}
        }
    }
}

@Composable
private fun FeaturedToysSection(
    state: UiState<List<Toy>>,
    onToyClick: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.sm)) {
        SectionHeader(title = "Heritage Highlights", subtitle = "Masterpieces from the town")
        
        when (state) {
            is UiState.Success -> {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.md),
                    contentPadding = PaddingValues(end = Spacing.lg)
                ) {
                    items(state.data) { toy ->
                        Surface(
                            modifier = Modifier
                                .width(160.dp)
                                .height(160.dp)
                                .clickable { onToyClick(toy.toyId) },
                            shape = RoundedCornerShape(20.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                ToyImage(toy.toyId, toy.imageUrl, Modifier.fillMaxSize())
                                // Name overlay
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.BottomCenter)
                                        .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))))
                                        .padding(Spacing.sm)
                                ) {
                                    Text(
                                        toy.name,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.White,
                                        maxLines = 1,
                                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }
            is UiState.Loading -> {
                Row(horizontalArrangement = Arrangement.spacedBy(Spacing.md)) {
                    repeat(3) {
                        Box(Modifier.size(160.dp).clip(RoundedCornerShape(20.dp)).background(MaterialTheme.colorScheme.surfaceVariant))
                    }
                }
            }
            else -> {}
        }
    }
}

@Composable
private fun AnimatedHeader(scrollState: ScrollState) {
    val transitionState = remember { MutableTransitionState(false).apply { targetState = true } }
    val transition = updateTransition(transitionState, label = "headerEntrance")

    val headerAlpha by transition.animateFloat(
        transitionSpec = { tween(800, easing = LinearOutSlowInEasing) },
        label = "alpha"
    ) { if (it) 1f else 0f }

    val translateY by transition.animateFloat(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) },
        label = "translateY"
    ) { if (it) 0f else -40f }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .graphicsLayer {
                this.alpha = 1f - (scrollState.value * 0.002f).coerceIn(0f, 1f)
                translationY = scrollState.value * 0.4f
            }
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                    )
                )
            )
            .padding(horizontal = Spacing.lg, vertical = Spacing.xxl)
    ) {
        Column(
            modifier = Modifier.graphicsLayer {
                this.alpha = headerAlpha
                this.translationY = translateY
            },
            verticalArrangement = Arrangement.spacedBy(Spacing.xs)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                Box(
                    modifier = Modifier
                        .size(16.dp, 2.dp)
                        .background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(1.dp))
                )
                Text(
                    text = "CHANNAPATNA HERITAGE",
                    style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 2.sp),
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "Namma Pride",
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 36.sp),
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.semantics { heading() }
            )
            Text(
                text = "The legacy of ivory wood & natural lacquer.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
            )
        }
        
        Icon(
            imageVector = Icons.Default.AutoAwesome,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterEnd)
                .offset(x = 30.dp, y = (-20).dp)
                .alpha(0.1f),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
private fun VerificationCard(
    toyId: String,
    verifyState: UiState<*>,
    showScanner: Boolean,
    onToyIdChange: (String) -> Unit,
    onVerify: () -> Unit,
    onReset: () -> Unit,
    onToggleScanner: () -> Unit
) {
    val isLoading = verifyState is UiState.Loading
    Surface(
        shape  = RoundedCornerShape(16.dp),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline),
        color  = MaterialTheme.colorScheme.surface
    ) {
        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text("Enter Toy ID", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value         = toyId,
                onValueChange = onToyIdChange,
                placeholder   = { Text("e.g. T001", color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)) },
                modifier      = Modifier.fillMaxWidth(),
                singleLine    = true,
                shape         = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search, capitalization = KeyboardCapitalization.Characters),
                keyboardActions = KeyboardActions(onSearch = { onVerify() }),
                trailingIcon = {
                    if (toyId.isNotBlank()) {
                        IconButton(onClick = onReset) { Icon(Icons.Default.Close, "Clear") }
                    }
                }
            )

            AnimatedVerifyButton(isLoading = isLoading, onClick = onVerify)

            Row(verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(Modifier.weight(1f), color = MaterialTheme.colorScheme.outline)
                Text("  or scan QR  ", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                HorizontalDivider(Modifier.weight(1f), color = MaterialTheme.colorScheme.outline)
            }

            OutlinedButton(
                onClick  = onToggleScanner,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape    = RoundedCornerShape(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(if (showScanner) Icons.Default.Close else Icons.Default.QrCodeScanner, null, modifier = Modifier.size(18.dp))
                    Text(if (showScanner) "Close Scanner" else "Scan QR Code")
                }
            }
        }
    }
}

@Composable
private fun AnimatedVerifyButton(isLoading: Boolean, onClick: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue  = 1f,
        targetValue   = if (isLoading) 1.02f else 1f,
        animationSpec = infiniteRepeatable(tween(600), RepeatMode.Reverse),
        label = "pulse"
    )
    Button(
        onClick  = onClick,
        enabled  = !isLoading,
        modifier = Modifier.fillMaxWidth().height(48.dp).scale(pulseScale),
        shape    = RoundedCornerShape(10.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(Modifier.size(18.dp), color = MaterialTheme.colorScheme.onPrimary, strokeWidth = 2.dp)
            Spacer(Modifier.width(10.dp))
            Text("Verifying…")
        } else {
            Icon(Icons.Default.Search, null, Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("Verify Toy")
        }
    }
}

@Composable
private fun ErrorBanner(message: String) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.errorContainer,
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.3f))
    ) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Icon(Icons.Default.ErrorOutline, null, tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(18.dp))
            Text(message, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onErrorContainer)
        }
    }
}

@Composable
private fun PermissionRationaleCard(onRequest: () -> Unit) {
    Surface(shape = RoundedCornerShape(10.dp), color = MaterialTheme.colorScheme.surfaceVariant) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.CameraAlt, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
            Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                Text("Camera access needed", style = MaterialTheme.typography.labelLarge)
                Text("Required to scan QR codes", style = MaterialTheme.typography.bodySmall)
            }
            TextButton(onClick = onRequest) { Text("Allow") }
        }
    }
}

@ExperimentalGetImage
@Composable
private fun ScannerCard(onCodeScanned: (String) -> Unit, onClose: () -> Unit) {
    Box(Modifier.fillMaxWidth().height(300.dp).clip(RoundedCornerShape(16.dp))) {
        QrScannerView(onCodeScanned)
        ScannerOverlay()
        IconButton(onClick = onClose, modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)) {
            Icon(Icons.Default.Close, null, tint = Color.White)
        }
    }
}

@Composable
private fun ScannerOverlay() {
    val transition = rememberInfiniteTransition(label = "scan")
    val alphaValue by transition.animateFloat(0.4f, 1f, infiniteRepeatable(tween(900), RepeatMode.Reverse), label = "alpha")
    val sweepY by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Restart),
        label = "sweep"
    )

    Canvas(Modifier.fillMaxSize()) {
        val windowSize = minOf(size.width, size.height) * 0.6f
        val left = (size.width - windowSize) / 2f
        val top = (size.height - windowSize) / 2f
        val right = left + windowSize
        val bottom = top + windowSize

        // Background with transparent cutout
        drawRect(Color.Black.copy(alpha = 0.5f))
        drawRect(
            Color.Transparent,
            topLeft = Offset(left, top),
            size = Size(windowSize, windowSize),
            blendMode = BlendMode.Clear
        )

        val green = Color(0xFF4CAF80).copy(alpha = alphaValue)
        val strokeWidth = 8f
        val cornerLength = 40f

        // Top Left
        drawLine(green, Offset(left, top), Offset(left + cornerLength, top), strokeWidth)
        drawLine(green, Offset(left, top), Offset(left, top + cornerLength), strokeWidth)

        // Top Right
        drawLine(green, Offset(right, top), Offset(right - cornerLength, top), strokeWidth)
        drawLine(green, Offset(right, top), Offset(right, top + cornerLength), strokeWidth)

        // Bottom Left
        drawLine(green, Offset(left, bottom), Offset(left + cornerLength, bottom), strokeWidth)
        drawLine(green, Offset(left, bottom), Offset(left, bottom - cornerLength), strokeWidth)

        // Bottom Right
        drawLine(green, Offset(right, bottom), Offset(right - cornerLength, bottom), strokeWidth)
        drawLine(green, Offset(right, bottom), Offset(right, bottom - cornerLength), strokeWidth)

        // Sweep Line
        val sweepLineY = top + (windowSize * sweepY)
        drawLine(
            brush = Brush.horizontalGradient(
                colors = listOf(Color.Transparent, green, Color.Transparent)
            ),
            start = Offset(left + 10f, sweepLineY),
            end = Offset(right - 10f, sweepLineY),
            strokeWidth = 4f
        )
    }
}

@ExperimentalGetImage
@Composable
fun QrScannerView(onCodeScanned: (String) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val executor = remember { Executors.newSingleThreadExecutor() }
    var scanned by remember { mutableStateOf(false) }
    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            ProcessCameraProvider.getInstance(ctx).addListener({
                val provider = ProcessCameraProvider.getInstance(ctx).get()
                val preview = Preview.Builder().build().also { it.setSurfaceProvider(previewView.surfaceProvider) }
                val analysis = ImageAnalysis.Builder().setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build()
                analysis.setAnalyzer(executor) { proxy ->
                    proxy.image?.let {
                        BarcodeScanning.getClient().process(InputImage.fromMediaImage(it, proxy.imageInfo.rotationDegrees))
                            .addOnSuccessListener { codes ->
                                codes.firstOrNull()?.rawValue?.let { code -> if (!scanned) { scanned = true; onCodeScanned(code) } }
                            }.addOnCompleteListener { proxy.close() }
                    } ?: proxy.close()
                }
                provider.bindToLifecycle(lifecycleOwner, CameraSelector.DEFAULT_BACK_CAMERA, preview, analysis)
            }, ContextCompat.getMainExecutor(ctx))
            previewView
        },
        modifier = Modifier.fillMaxSize()
    )
}
