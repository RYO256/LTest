package com.example.ltest.data.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fixed_width_downsampled (

	val height : Int?,
	val size : Int?,
	val url : String?,
	val webp : String?,
	val webp_size : Int?,
	val width : Int?
): Parcelable