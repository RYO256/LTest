package com.example.ltest.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.map
import com.example.ltest.CoroutineTestRule
import com.example.ltest.core.Resource
import com.example.ltest.data.dto.*
import com.example.ltest.domain.models.Gif
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.io.IOException

@ExperimentalCoroutinesApi
class GifsRepositoryImplTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var sut: GifsRepositoryImpl

    private lateinit var webService: WebService
    private lateinit var networkDataSource: NetworkDataSource


    private val dispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(dispatcher)

    @Before
    fun setUp() {
        webService = mock(WebService::class.java)
        networkDataSource = NetworkDataSource(webService)

        sut = GifsRepositoryImpl(networkDataSource, webService)
    }

    @Test
    @Suppress("IllegalIdentifier")
    fun `when getRandom should return success `() = runBlockingTest {
        val gifItemDto = mock(GiphyItemDto::class.java)
        Mockito.`when`(networkDataSource.getRandomGif()).thenReturn(flow { emit(Resource.Success(gifItemDto)) })

        lateinit var result: Resource<Gif>

        testScope.launch {
            sut.getRandom().collect {
                result = it
            }
        }

        assert(result is Resource.Success)

    }

    @Test
    @Suppress("IllegalIdentifier")
    fun `when getRandom should return failure `() = runBlockingTest {
        BDDMockito.given(webService.getRandomGif()).willAnswer {
            throw IOException("Ooops")
        }
        lateinit var result: Resource<Gif>
        testScope.launch {
            sut.getRandom().collect {
                result = it
            }
        }

        assert(result is Resource.Failure)

    }

}