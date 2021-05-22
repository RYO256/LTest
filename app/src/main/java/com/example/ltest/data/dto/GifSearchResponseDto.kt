package com.example.ltest.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GifSearchResponseDto(
        @SerializedName("data")
        val data_field: List<Data>?,
        val pagination: Pagination?,
        val meta: Meta?
) : Parcelable