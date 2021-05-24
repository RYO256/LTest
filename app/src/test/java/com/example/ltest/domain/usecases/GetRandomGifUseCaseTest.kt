package com.example.ltest.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ltest.CoroutineTestRule
import com.example.ltest.core.Resource
import com.example.ltest.domain.models.Gif
import com.example.ltest.domain.repo.GifsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class GetRandomGifUseCaseTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()
    private val dispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(dispatcher)

    private lateinit var sut: GetRandomGifUseCase
    private lateinit var gifsRepository: GifsRepository

    @Before
    fun setUp() {
        gifsRepository = Mockito.mock(GifsRepository::class.java)
        sut = GetRandomGifUseCase(gifsRepository)
    }

    @Test
    fun getRandomGifTestTest() = runBlockingTest {
        val randomGifMock = Mockito.mock(Gif::class.java)
        Mockito.`when`(gifsRepository.getRandom()).thenReturn(flow { Resource.Success(randomGifMock) })
        testScope.launch {
            sut.invoke().collect {
                assertEquals(it, randomGifMock)
            }
        }
    }
}
