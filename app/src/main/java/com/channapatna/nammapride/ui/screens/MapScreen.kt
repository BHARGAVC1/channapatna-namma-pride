package com.channapatna.nammapride.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.ui.components.*
import com.channapatna.nammapride.viewmodel.ArtisanViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    artisanId: String,
    onBack: () -> Unit,
    viewModel: ArtisanViewModel = hiltViewModel()
) {
    val uiState by viewModel.selectedState.collectAsStateWithLifecycle()
    LaunchedEffect(artisanId) { viewModel.loadArtisan(artisanId) }

    val locationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    
    var mapType by remember { mutableStateOf(MapType.NORMAL) }
    val context = LocalContext.current

    // Auto-request permission on entry if not granted
    LaunchedEffect(Unit) {
        if (!locationPermissionState.status.isGranted) {
            locationPermissionState.launchPermissionRequest()
        }
    }

    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AppTopBar("Artisan Location", onBack)
        when (uiState.artisanState) {
            is UiState.Loading -> LoadingView()
            is UiState.Error   -> ErrorView((uiState.artisanState as UiState.Error).message)
            is UiState.Success -> {
                val artisan      = (uiState.artisanState as UiState.Success).data
                val position     = LatLng(artisan.latitude, artisan.longitude)
                val cameraState  = rememberCameraPositionState {
                    this.position = CameraPosition.fromLatLngZoom(position, 16f)
                }

                // Card slide-up animation
                var cardVisible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(400)
                    cardVisible = true
                }

                Box(Modifier.fillMaxSize()) {
                    GoogleMap(
                        modifier             = Modifier.fillMaxSize(),
                        cameraPositionState  = cameraState,
                        properties           = MapProperties(
                            isMyLocationEnabled = locationPermissionState.status.isGranted,
                            mapType = mapType,
                            isIndoorEnabled = true,
                            isTrafficEnabled = true
                        ),
                        uiSettings           = MapUiSettings(
                            zoomControlsEnabled = false,
                            myLocationButtonEnabled = locationPermissionState.status.isGranted,
                            compassEnabled = true,
                            mapToolbarEnabled = true,
                            rotationGesturesEnabled = true,
                            tiltGesturesEnabled = true
                        )
                    ) {
                        Marker(
                            state   = MarkerState(position = position),
                            title   = artisan.name,
                            snippet = "Tap for directions",
                            onClick = {
                                val gmmIntentUri = Uri.parse("google.navigation:q=${artisan.latitude},${artisan.longitude}")
                                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                                mapIntent.setPackage("com.google.android.apps.maps")
                                context.startActivity(mapIntent)
                                true
                            }
                        )
                    }

                    // Map Type Toggle (Floating)
                    SmallFloatingActionButton(
                        onClick = { 
                            mapType = if (mapType == MapType.NORMAL) MapType.SATELLITE else MapType.NORMAL 
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp),
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.primary
                    ) {
                        Icon(if (mapType == MapType.NORMAL) Icons.Outlined.Map else Icons.Default.Layers, contentDescription = "Toggle Map Type")
                    }

                    if (!locationPermissionState.status.isGranted && locationPermissionState.status.shouldShowRationale) {
                        Surface(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 70.dp, start = 16.dp, end = 16.dp),
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.errorContainer,
                            shadowElevation = 4.dp
                        ) {
                            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    "Location permission needed for full map features.",
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.weight(1f)
                                )
                                TextButton(onClick = { locationPermissionState.launchPermissionRequest() }) {
                                    Text("Grant")
                                }
                            }
                        }
                    }

                    // Bottom info card slides up
                    androidx.compose.animation.AnimatedVisibility(
                        visible = cardVisible,
                        enter   = slideInVertically(
                            initialOffsetY = { it },
                            animationSpec  = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMedium)
                        ) + fadeIn(tween(300)),
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        Surface(
                            modifier        = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            shape           = RoundedCornerShape(18.dp),
                            color           = MaterialTheme.colorScheme.surface,
                            shadowElevation = 8.dp
                        ) {
                            Row(
                                Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                ArtisanInitialsAvatar(artisan.name, 52)
                                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Text(artisan.name, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                                    Text(artisan.craftType, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                        Icon(Icons.Default.LocationOn, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(13.dp))
                                        Text(artisan.locationText, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else -> {}
        }
    }
}
