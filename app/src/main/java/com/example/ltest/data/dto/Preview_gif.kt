package com.example.ltest.data.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Preview_gif (

	val height : Int?,
	val size : Int?,
	val url : String?,
	val width : Int?
): Parcelable