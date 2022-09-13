package com.jrubiralta.beerlistapp.data.response

import com.google.gson.annotations.SerializedName

data class BeerResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("abv") val abv: Float?,
    @SerializedName("image_url") val img: String?,
    @SerializedName("food_pairing") val foodPairing: List<String>
)