package com.example.ltest.data.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meta (

	val status : Int?,
	val msg : String?,
	val response_id : String?
): Parcelable