package com.jrubiralta.beerlistapp.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jrubiralta.beerlistapp.data.api.PunkApi
import com.jrubiralta.beerlistapp.data.datasources.BeerPagingSource
import com.jrubiralta.beerlistapp.domain.model.BeerModel
import com.jrubiralta.beerlistapp.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(
    private val service: PunkApi
) : BeerRepository {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    override fun getBeerList(search: String?): Flow<PagingData<BeerModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { BeerPagingSource(service, search) }
        ).flow
    }

}
