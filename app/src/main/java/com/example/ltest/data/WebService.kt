package com.example.ltest.data

import com.example.ltest.data.dto.GifSearchResponseDto
import com.example.ltest.data.dto.GiphyItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("gifs/random?api_key=25O5TvmvF0o4acDev4uhiALU3Vy53KiI")
    suspend fun getRandomGif(): GiphyItemDto

    @GET("gifs/search?api_key=25O5TvmvF0o4acDev4uhiALU3Vy53KiI")
    suspend fun getGifs(
            @Query("q") query: String,
            @Query("limit") size: Int,
            @Query("offset") page: Int,
    ): GifSearchResponseDto

}