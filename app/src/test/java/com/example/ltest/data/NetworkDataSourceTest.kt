package com.example.ltest.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ltest.CoroutineTestRule
import com.example.ltest.core.Resource
import com.example.ltest.data.dto.GifSearchResponseDto
import com.example.ltest.data.dto.GiphyItemDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import java.io.IOException

@ExperimentalCoroutinesApi
class NetworkDataSourceTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var sut: NetworkDataSource

    private lateinit var webService: WebService

    private val dispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(dispatcher)

    @Before
    fun setUp() {
        webService = Mockito.mock(WebService::class.java)
        sut = NetworkDataSource(webService)
    }

    @Test
    @Suppress("IllegalIdentifier")
    fun `when getRandom should return success `() = runBlockingTest {
        val gifItemDto = Mockito.mock(GiphyItemDto::class.java)
        Mockito.`when`(webService.getRandomGif()).thenReturn(gifItemDto)

        lateinit var result: Resource<GiphyItemDto>

        testScope.launch {
            sut.getRandomGif().collect {
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
        lateinit var result: Resource<GiphyItemDto>
        testScope.launch {
            sut.getRandomGif().collect {
                result = it
            }
        }

        assert(result is Resource.Failure)

    }
}