package com.example.ltest.domain.repo

import androidx.paging.PagingData
import com.example.ltest.core.Resource
import com.example.ltest.domain.models.Gif
import kotlinx.coroutines.flow.Flow

interface GifsRepository {
    fun getRandom(): Flow<Resource<Gif>>
    fun getGifs(query: String): Flow<PagingData<Gif>>
}