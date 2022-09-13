package com.jrubiralta.beerlistapp.data.response

import com.jrubiralta.beerlistapp.domain.model.BeerModel

object Mapper {

    fun BeerResponse.toDomain() =
        BeerModel(
            id = this.id,
            name = this.name,
            description = this.description,
            abv = this.abv,
            img = this.img,
            foodPairing = this.foodPairing
        )
}