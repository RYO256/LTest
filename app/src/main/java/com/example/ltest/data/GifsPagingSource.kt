package com.example.ltest.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ltest.data.dto.GifSearchResponseDto
import com.example.ltest.data.dto.toGif
import com.example.ltest.domain.models.Gif

import retrofit2.HttpException
import java.io.IOException

private const val PAGE_SIZE = 30

class GifsPagingSource(
        private val service: WebService,
        private val query: String,
) : PagingSource<Int, Gif>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> =
            try {
                val pageNumber = params.key ?: 1
                val response = service.getGifs(
                        query,
                        PAGE_SIZE,
                        pageNumber
                )
                val previousKey = if (pageNumber > 1) {
                    pageNumber - 1
                } else {
                    null
                }

                val nextKey = response.pagination?.offset?.plus(1)

                LoadResult.Page(
                        data = extractResult(response),
                        prevKey = previousKey,
                        nextKey = nextKey
                )
            } catch (e: IOException) {
                LoadResult.Error(e)
            } catch (e: HttpException) {
                LoadResult.Error(e)
            }

    private fun extractResult(response: GifSearchResponseDto): List<Gif> {
        val gifs = mutableListOf<Gif>()

        for (gifDto in response.data_field ?: listOf()) {
            gifs.add(gifDto.toGif())
        }
        return gifs
    }

    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        return state.anchorPosition
    }
}
