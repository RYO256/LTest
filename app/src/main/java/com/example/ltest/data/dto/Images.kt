package com.example.ltest.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Images (

	val downsized_large : Downsized_large?,
	val fixed_height_small_still : Fixed_height_small_still?,
	val original : Original?,
	val fixed_height_downsampled : Fixed_height_downsampled?,
	val downsized_still : Downsized_still?,
	val fixed_height_still : Fixed_height_still?,
	val downsized_medium : Downsized_medium?,
	val downsized : Downsized?,
	val preview_webp : Preview_webp?,
	val original_mp4 : Original_mp4?,
	val fixed_height_small : Fixed_height_small?,
	val fixed_height : Fixed_height?,
	val downsized_small : Downsized_small?,
	val preview : Preview?,
	val fixed_width_downsampled : Fixed_width_downsampled?,
	val fixed_width_small_still : Fixed_width_small_still?,
	val fixed_width_small : Fixed_width_small?,
	val original_still : Original_still?,
	val fixed_width_still : Fixed_width_still?,
	val looping : Looping?,
	val fixed_width : Fixed_width?,
	val preview_gif : Preview_gif?,
	@SerializedName("480w_still")
	val w_still480Dto : w_still480Dto?
): Parcelable