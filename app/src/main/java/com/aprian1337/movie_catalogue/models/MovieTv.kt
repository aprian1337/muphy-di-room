package com.aprian1337.movie_catalogue.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieTv(
    val id: String?="",
    val title: String?="",
    val genre: String?="",
    val length: String?="",
    val overview: String?="",
    val image: Int?=0,
    val parental: String? = ""
) : Parcelable