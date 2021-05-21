package com.example.ltest.data.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (

	val avatar_url : String?,
	val banner_image : String?,
	val banner_url : String?,
	val profile_url : String?,
	val username : String?,
	val display_name : String?,
	val description : String?,
	val is_verified : Boolean?,
	val website_url : String?,
	val instagram_url : String?
): Parcelable