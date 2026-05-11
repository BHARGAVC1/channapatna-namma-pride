package com.channapatna.nammapride.domain.usecase

import com.channapatna.nammapride.data.local.entity.Toy
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.data.repository.IToyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchToysUseCase @Inject constructor(private val repository: IToyRepository) {
    operator fun invoke(query: String): Flow<UiState<List<Toy>>> {
        return repository.getAllToys().map { state ->
            if (state is UiState.Success && query.isNotBlank()) {
                val filtered = state.data.filter {
                    it.name.contains(query, true) ||
                            it.category.contains(query, true) ||
                            it.material.contains(query, true)
                }
                if (filtered.isEmpty()) UiState.Empty else UiState.Success(filtered)
            } else {
                state
            }
        }
    }
}
