package com.example.ltest.data.dto

import android.os.Parcelable
import com.example.ltest.domain.models.Gif
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data (
		val type : String?,
		val id : String?,
		val url : String?,
		val slug : String?,
		val bitly_gif_url : String?,
		val bitly_url : String?,
		val embed_url : String?,
		val username : String?,
		val source : String?,
		val title : String?,
		val rating : String?,
		val content_url : String?,
		val source_tld : String?,
		val source_post_url : String?,
		val is_sticker : Int?,
		val import_datetime : String?,
		val trending_datetime : String?,
		val images : Images?,
		val user : User?,
		val image_original_url : String?,
		val image_url : String?,
		val image_mp4_url : String?,
		val image_frames : Int?,
		val image_width : Int?,
		val image_height : Int?,
		val fixed_height_downsampled_url : String?,
		val fixed_height_downsampled_width : Int?,
		val fixed_height_downsampled_height : Int?,
		val fixed_width_downsampled_url : String?,
		val fixed_width_downsampled_width : Int?,
		val fixed_width_downsampled_height : Int?,
		val fixed_height_small_url: String?,
		val fixed_height_small_still_url: String?,
		val fixed_height_small_width: Int?,
		val fixed_height_small_height: Int?,
		val fixed_width_small_url: String?,
		val fixed_width_small_still_url: String?,
		val fixed_width_small_width: Int?,
		val fixed_width_small_height: Int?,
		val caption: String?
) : Parcelable

fun Data.toGif(): Gif = Gif(
		this.images?.fixed_height?.url ?: "",
		this.images?.preview_gif?.url ?: "",
)