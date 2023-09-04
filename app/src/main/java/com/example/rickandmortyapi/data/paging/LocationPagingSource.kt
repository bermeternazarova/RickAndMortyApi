package com.example.rickandmortyapi.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapi.data.remote.ApiService
import com.example.rickandmortyapi.domain.model.location.Location

class LocationPagingSource(
    private val apiService: ApiService
) :PagingSource<Int,Location>(){

    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        return state.anchorPosition?.let {anchorPos->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        return try {
            val page = params.key?:1
            val response = apiService.getAllLocation(page = page)

            LoadResult.Page(
                data = response.results.map { it.toMapping() },
                prevKey = if (page==1) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1)
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}