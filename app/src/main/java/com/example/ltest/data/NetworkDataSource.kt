package com.example.ltest.data

import com.example.ltest.core.Resource
import com.example.ltest.data.dto.GiphyItemDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NetworkDataSource @Inject constructor(
        private val webService: WebService
) {
    fun getRandomGif(): Flow<Resource<GiphyItemDto>> =
            flow {
                emit(
                        try {
                            Resource.Success(
                                    webService.getRandomGif()
                            )
                        } catch (e: Exception) {
                            Resource.Failure(e)
                        }
                )
            }
}