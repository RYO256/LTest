package com.example.ltest.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pagination(
        @SerializedName("total_count")
        val totalCount: Long,
        val count: Long,
        val offset: Int
) : Parcelable