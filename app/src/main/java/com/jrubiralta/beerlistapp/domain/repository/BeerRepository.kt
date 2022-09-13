package com.jrubiralta.beerlistapp.domain.repository

import androidx.paging.PagingData
import com.jrubiralta.beerlistapp.domain.model.BeerModel
import kotlinx.coroutines.flow.Flow

interface BeerRepository {

    fun getBeerList(search: String?): Flow<PagingData<BeerModel>>

}