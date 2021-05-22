package com.example.ltest.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.ltest.core.Resource
import com.example.ltest.data.dto.toGif
import com.example.ltest.domain.models.Gif
import com.example.ltest.domain.repo.GifsRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val PAGE_SIZE = 30

@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class GifsRepositoryImpl @Inject constructor(
        private val networkDataSource: NetworkDataSource,
        private val service: WebService,
) : GifsRepository {

    override fun getRandom(): Flow<Resource<Gif>> =
            flow {
                networkDataSource.getRandomGif().collect {
                    when (it) {
                        is Resource.Success -> {
                            it.data.data?.let {
                                emit(Resource.Success(it.toGif()))
                            } ?: emit(Resource.Failure(Exception("getRandomGif exception")))
                        }

                        else -> emit(Resource.Failure(Exception("getRandomGif exception")))
                    }
                }
            }


    override fun getGifs(query: String): Flow<PagingData<Gif>> =
            Pager(
                    PagingConfig(
                            enablePlaceholders = true,
                            pageSize = PAGE_SIZE,
                            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                    )
            ) {
                GifsPagingSource(
                        service,
                        query,
                )
            }.flow

}