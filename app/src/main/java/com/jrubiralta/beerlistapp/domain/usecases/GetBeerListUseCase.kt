package com.jrubiralta.beerlistapp.domain.usecases

import androidx.paging.PagingData
import com.jrubiralta.beerlistapp.domain.model.BeerModel
import com.jrubiralta.beerlistapp.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeerListUseCase @Inject constructor(private val repository: BeerRepository) {

    fun invoke(search: String?): Flow<PagingData<BeerModel>> = repository.getBeerList(search)
}