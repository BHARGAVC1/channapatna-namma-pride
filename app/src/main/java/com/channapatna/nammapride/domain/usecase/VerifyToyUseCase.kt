package com.channapatna.nammapride.domain.usecase

import com.channapatna.nammapride.data.local.entity.ToyWithArtisan
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.data.repository.IToyRepository
import javax.inject.Inject

class VerifyToyUseCase @Inject constructor(private val repository: IToyRepository) {
    suspend operator fun invoke(id: String): UiState<ToyWithArtisan> {
        return repository.verifyToy(id)
    }
}
