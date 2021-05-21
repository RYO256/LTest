package com.example.ltest.data.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Original (

	val frames : Int?,
	val hash : String?,
	val height : Int?,
	val mp4 : String?,
	val mp4_size : Int?,
	val size : Int?,
	val url : String?,
	val webp : String?,
	val webp_size : Int?,
	val width : Int?
): Parcelable