package com.example.ltest.data

import com.example.ltest.core.Resource
import com.example.ltest.data.dto.toGif
import com.example.ltest.domain.models.Gif
import com.example.ltest.domain.repo.GifsRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class GifsRepositoryImpl @Inject constructor(
        private val networkDataSource: NetworkDataSource,
) : GifsRepository {

    override fun getRandom(): Flow<Resource<Gif>> =
            flow {
                networkDataSource.getRandomGif().collect {
                    when (it) {
                        is Resource.Success -> {
                            emit(Resource.Success(it.data.toGif()))
                        }

                        else -> emit(Resource.Failure(Exception("getRandomGif exception")))
                    }
                }
            }
}