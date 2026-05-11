package com.channapatna.nammapride.domain.usecase

import com.channapatna.nammapride.data.repository.IToyRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(private val repository: IToyRepository) {
    suspend operator fun invoke(id: String, isFavorite: Boolean) {
        repository.toggleFavorite(id, isFavorite)
    }
}
