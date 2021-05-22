package com.example.ltest.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gif(val url: String, val preview: String) : Parcelable
