package com.channapatna.nammapride.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.ui.components.*
import com.channapatna.nammapride.ui.theme.Spacing
import com.channapatna.nammapride.viewmodel.CatalogViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CatalogScreen(
    onToyClick: (String) -> Unit,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AppTopBar("Toy Catalog", onBack)

        // Premium Search bar
        Column(
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(vertical = Spacing.sm)
        ) {
            OutlinedTextField(
                value         = uiState.query,
                onValueChange = viewModel::onSearchChange,
                placeholder   = { Text("Search by name, material, category…", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)) },
                modifier      = Modifier.fillMaxWidth().padding(horizontal = Spacing.md),
                singleLine    = true,
                shape         = RoundedCornerShape(22.dp),
                colors        = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedBorderColor   = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    unfocusedBorderColor = Color.Transparent
                ),
                leadingIcon  = { Icon(Icons.Default.Search, "Search", tint = MaterialTheme.colorScheme.primary) },
                trailingIcon = {
                    if (uiState.query.isNotBlank()) {
                        IconButton(onClick = { viewModel.onSearchChange("") }) {
                            Icon(Icons.Default.Close, "Clear search", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            )
            
            // Category Quick Filters
            val categories = listOf("All", "Spinning Toys", "Figurines", "Rattles", "Nesting Toys", "Heritage Collection")
            var selectedCategory by remember { mutableStateOf("All") }

            LazyRow(
                modifier = Modifier.padding(vertical = Spacing.xs),
                horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
                contentPadding = PaddingValues(horizontal = Spacing.md)
            ) {
                items(categories) { cat ->
                    FilterChip(
                        selected = selectedCategory == cat,
                        onClick  = {
                            selectedCategory = cat
                            viewModel.onCategoryFilter(if (cat == "All") null else cat)
                        },
                        label    = { Text(cat, style = MaterialTheme.typography.labelMedium) },
                        colors   = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor     = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), thickness = 0.5.dp)

        AnimatedContent(
            targetState   = uiState.toysState,
            transitionSpec = { fadeIn(tween(250)) togetherWith fadeOut(tween(150)) },
            label         = "catalogState"
        ) { state ->
            when (state) {
                is UiState.Loading -> ShimmerToyList(Modifier.padding(Spacing.md))
                is UiState.Error   -> ErrorView(state.message)
                is UiState.Empty   -> EmptyState(
                    query     = uiState.query,
                    onClear   = { viewModel.onSearchChange("") },
                    onSuggest = { viewModel.onSearchChange(it) }
                )
                is UiState.Success -> {
                    val toys = state.data
                    LazyColumn(
                        contentPadding    = PaddingValues(horizontal = Spacing.md, vertical = Spacing.md),
                        verticalArrangement = Arrangement.spacedBy(Spacing.md)
                    ) {
                        item {
                            Text(
                                "${toys.size} item${if (toys.size != 1) "s" else ""}",
                                style    = MaterialTheme.typography.labelSmall,
                                color    = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                            )
                            Spacer(Modifier.height(6.dp))
                        }
                        itemsIndexed(toys, key = { _, t -> t.toyId }) { _, toy ->
                            ToyCard(
                                toy = toy,
                                onFavoriteToggle = { viewModel.onFavoriteClick(toy) },
                                sharedTransitionScope = sharedTransitionScope,
                                animatedVisibilityScope = animatedVisibilityScope,
                                onClick = { onToyClick(toy.toyId) }
                            )
                        }
                    }
                }
                else -> LoadingView()
            }
        }
    }
}

@Composable
private fun EmptyState(query: String, onClear: () -> Unit, onSuggest: (String) -> Unit) {
    PremiumEmptyState(
        title = if (query.isEmpty()) "Start exploring" else "No results for \"$query\"",
        subtitle = "Try searching for animals, vehicles, or wood types.",
        icon = Icons.Default.SearchOff,
        actionText = if (query.isNotEmpty()) "Clear search" else null,
        onAction = if (query.isNotEmpty()) onClear else null
    )
}
