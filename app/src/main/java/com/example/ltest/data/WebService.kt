package com.example.ltest.data

import com.example.ltest.data.dto.GiphyItemDto
import retrofit2.http.GET

interface WebService {
    @GET("gifs/random?api_key=25O5TvmvF0o4acDev4uhiALU3Vy53KiI")
    suspend fun getRandomGif(): GiphyItemDto
}