package com.example.ltest.data.dto

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.mockito.Mockito

import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class DataKtTest {

    @Test
    fun toGif() {
        val data = mock(Data::class.java)
        val images = mock(Images::class.java)
        val fixedHeight = mock(Fixed_height::class.java)
        val previewGif = mock(Preview_gif::class.java)

        Mockito.`when`(data.images).thenReturn(images)
        Mockito.`when`(images.fixed_height).thenReturn(fixedHeight)
        Mockito.`when`(images?.preview_gif).thenReturn(previewGif)

        Mockito.`when`(fixedHeight.url).thenReturn("url")
        Mockito.`when`(previewGif.url).thenReturn("preview")

        val gif = data.toGif()

        assert(gif.url == "url")
        assert(gif.preview == "preview")
    }
}