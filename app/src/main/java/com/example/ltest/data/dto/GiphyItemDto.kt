package com.example.ltest.data.dto

import android.os.Parcelable
import com.example.ltest.domain.models.Gif
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GiphyItemDto(
        val data: Data?,
        val meta: Meta?
): Parcelable

fun GiphyItemDto.toGif(): Gif = Gif(
        this.data?.images?.preview_gif?.url ?: "",
        this.data?.images?.fixed_height?.url ?: "")

