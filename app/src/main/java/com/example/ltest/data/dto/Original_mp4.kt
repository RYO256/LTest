package com.example.ltest.data.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Original_mp4 (

	val height : Int?,
	val mp4 : String?,
	val mp4_size : Int?,
	val width : Int?
): Parcelable