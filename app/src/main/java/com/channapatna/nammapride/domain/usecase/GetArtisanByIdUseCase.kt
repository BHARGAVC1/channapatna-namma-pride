package com.channapatna.nammapride.domain.usecase

import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.data.repository.IToyRepository
import javax.inject.Inject

class GetArtisanByIdUseCase @Inject constructor(private val repository: IToyRepository) {
    suspend operator fun invoke(id: String): UiState<Artisan> {
        return repository.getArtisanById(id)
    }
}
