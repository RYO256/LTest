package com.example.ltest.domain.repo

import com.example.ltest.core.Resource
import com.example.ltest.domain.models.Gif
import kotlinx.coroutines.flow.Flow

interface GifsRepository {
    fun getAll(): Flow<Resource<Gif>>
}