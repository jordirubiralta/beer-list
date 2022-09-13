package com.jrubiralta.beerlistapp.data.api

import com.jrubiralta.beerlistapp.data.response.BeerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PunkApi {

    @GET("/v2/beers")
    suspend fun getBeerList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("beer_name") search: String?,
    ): Response<List<BeerResponse>>

}