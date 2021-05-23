package com.example.ltest.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.example.ltest.CoroutineTestRule
import com.example.ltest.domain.models.Gif
import com.example.ltest.domain.repo.GifsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class GetPagedSearchResultsuseCaseTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var sut: GetPagedSearchResultsuseCase
    private lateinit var gifsRepository: GifsRepository

    @Before
    fun setUp() {
        gifsRepository = Mockito.mock(GifsRepository::class.java)
        sut = GetPagedSearchResultsuseCase(gifsRepository)
    }

    @Test
    fun getArticlesTest() = runBlockingTest {
        val gifsPagedListMock = Mockito.mock(PagingData::class.java) as PagingData<Gif>
        Mockito.`when`(gifsRepository.getGifs("test")).thenReturn(flowOf(gifsPagedListMock))
        val result = sut.invoke("test")
        assertEquals(result.first(), gifsPagedListMock)
    }
}
