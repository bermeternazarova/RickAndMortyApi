package com.example.rickandmortyapi.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyapi.data.paging.CharacterPagingSource
import com.example.rickandmortyapi.data.paging.EpisodePagingSource
import com.example.rickandmortyapi.data.paging.LocationPagingSource
import com.example.rickandmortyapi.data.remote.ApiService
import com.example.rickandmortyapi.domain.model.character.Character
import com.example.rickandmortyapi.domain.model.episode.Episode
import com.example.rickandmortyapi.domain.model.location.Location
import com.example.rickandmortyapi.domain.repository.RepositoryRM
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImple (
    private val apiService: ApiService
) :RepositoryRM {

    override fun getAllCharacters(): Flow<PagingData<Character>>
    = Pager(
           config = PagingConfig(pageSize = 20),
           pagingSourceFactory = {
               CharacterPagingSource(apiService = apiService)
           }
       ).flow

    override fun getAllEpisodes(): Flow<PagingData<Episode>>
    = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            EpisodePagingSource(apiService = apiService)
        }
    ).flow

    override fun getALlLocation(): Flow<PagingData<Location>>
    = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            LocationPagingSource(apiService = apiService)
        }
    ).flow
}