package com.jrubiralta.beerlistapp.data.datasources

import androidx.paging.PagingSource
import com.jrubiralta.beerlistapp.data.api.PunkApi
import com.jrubiralta.beerlistapp.data.response.Mapper.toDomain
import com.jrubiralta.beerlistapp.domain.model.BeerModel
import retrofit2.HttpException
import java.io.IOException

class BeerPagingSource(
    private val punkApi: PunkApi,
    private val query: String?
) : PagingSource<Int, BeerModel>() {

    companion object {
        private const val STARTING_KEY = 1
        private const val NETWORK_PAGE_SIZE = 20
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeerModel> {
        val position = params.key ?: STARTING_KEY
        return try {
            val response = punkApi.getBeerList(position, params.loadSize, query)
            val repos = response.body()?.map { it.toDomain() }?: emptyList()
            val nextKey = if (repos.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (position == STARTING_KEY) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}