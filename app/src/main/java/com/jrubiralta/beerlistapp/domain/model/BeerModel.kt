package com.jrubiralta.beerlistapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeerModel(
    val id: Int,
    val name: String,
    val description: String?,
    val abv: Float?,
    val img: String?,
    val foodPairing: List<String>
) : Parcelable