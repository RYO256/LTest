package com.example.ltest.domain.usecases

import androidx.paging.PagingData
import com.example.ltest.domain.models.Gif
import com.example.ltest.domain.repo.GifsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagedSearchResultsuseCase @Inject constructor(
        private val albumsRepository: GifsRepository,
) {
    operator fun invoke(query: String): Flow<PagingData<Gif>> = albumsRepository.getGifs(query)
}