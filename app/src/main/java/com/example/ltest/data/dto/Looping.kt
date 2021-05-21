package com.example.ltest.data.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Looping (

	val mp4 : String?,
	val mp4_size : Int?
): Parcelable