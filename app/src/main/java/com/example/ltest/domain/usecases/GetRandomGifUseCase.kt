package com.example.ltest.domain.usecases

import com.example.ltest.core.Resource
import com.example.ltest.domain.models.Gif
import com.example.ltest.domain.repo.GifsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomGifUseCase @Inject constructor(
        private val albumsRepository: GifsRepository,
) {
    operator fun invoke(): Flow<Resource<Gif>> = albumsRepository.getRandom()
}