package com.channapatna.nammapride.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.ui.components.*
import com.channapatna.nammapride.viewmodel.ArtisanViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapScreen(
    artisanId: String,
    onBack: () -> Unit,
    viewModel: ArtisanViewModel = hiltViewModel()
) {
    val uiState by viewModel.selectedState.collectAsStateWithLifecycle()
    LaunchedEffect(artisanId) { viewModel.loadArtisan(artisanId) }

    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AppTopBar("Artisan Location", onBack)
        when (uiState.artisanState) {
            is UiState.Loading -> LoadingView()
            is UiState.Error   -> ErrorView((uiState.artisanState as UiState.Error).message)
            is UiState.Success -> {
                val artisan      = (uiState.artisanState as UiState.Success).data
                val position     = LatLng(artisan.latitude, artisan.longitude)
                val cameraState  = rememberCameraPositionState {
                    this.position = CameraPosition.fromLatLngZoom(position, 15f)
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
                        uiSettings           = MapUiSettings(zoomControlsEnabled = true, myLocationButtonEnabled = false)
                    ) {
                        Marker(
                            state   = MarkerState(position = position),
                            title   = artisan.name,
                            snippet = artisan.craftType
                        )
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
