package com.channapatna.nammapride.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.ui.components.*
import com.channapatna.nammapride.ui.theme.Spacing
import com.channapatna.nammapride.viewmodel.FavoritesViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FavoritesScreen(
    onToyClick: (String) -> Unit,
    onBrowseCatalog: () -> Unit,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val state by viewModel.favoritesState.collectAsStateWithLifecycle()

    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AppTopBar("Saved Toys", onBack)

        AnimatedContent(
            targetState = state,
            transitionSpec = { fadeIn(tween(250)) togetherWith fadeOut(tween(150)) },
            label = "favoritesState"
        ) { s ->
            when (s) {
                is UiState.Loading -> ShimmerToyList(Modifier.padding(Spacing.md))
                is UiState.Error -> ErrorView(s.message)
                is UiState.Empty -> EmptyFavorites(onBrowseCatalog)
                is UiState.Success -> {
                    val toys = s.data
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = Spacing.md, vertical = Spacing.md),
                        verticalArrangement = Arrangement.spacedBy(Spacing.md)
                    ) {
                        itemsIndexed(toys, key = { _, t -> t.toyId }) { _, toy ->
                            ToyCard(
                                toy = toy,
                                onFavoriteToggle = { viewModel.onToggleFavorite(toy) },
                                sharedTransitionScope = sharedTransitionScope,
                                animatedVisibilityScope = animatedVisibilityScope,
                                onClick = { onToyClick(toy.toyId) }
                            )
                        }
                    }
                }
                else -> {}
            }
        }
    }
}

@Composable
private fun EmptyFavorites(onBrowseCatalog: () -> Unit) {
    PremiumEmptyState(
        title      = "Your handcrafted collection awaits",
        subtitle   = "Tap the heart on any toy to save it here.",
        icon       = Icons.Default.BookmarkBorder,
        actionText = "Browse catalog",
        onAction   = onBrowseCatalog
    )
}
