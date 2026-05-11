package com.channapatna.nammapride.domain.usecase

import com.channapatna.nammapride.data.local.entity.Toy
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.data.repository.IToyRepository
import javax.inject.Inject

class GetToyByIdUseCase @Inject constructor(private val repository: IToyRepository) {
    suspend operator fun invoke(id: String): UiState<Toy> {
        return repository.getToyById(id)
    }
}
