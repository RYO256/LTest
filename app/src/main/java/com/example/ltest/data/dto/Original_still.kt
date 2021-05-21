package com.example.ltest.data.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Original_still (

	val height : Int?,
	val size : Int?,
	val url : String?,
	val width : Int?
): Parcelable