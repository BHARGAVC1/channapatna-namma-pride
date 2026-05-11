package com.channapatna.nammapride.domain.usecase

import com.channapatna.nammapride.data.local.entity.Toy
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.data.repository.IToyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetToysUseCase @Inject constructor(private val repository: IToyRepository) {
    operator fun invoke(): Flow<UiState<List<Toy>>> {
        return repository.getAllToys()
    }
}
