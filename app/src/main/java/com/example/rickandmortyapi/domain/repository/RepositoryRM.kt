package com.example.rickandmortyapi.domain.repository

import androidx.paging.PagingData
import com.example.rickandmortyapi.domain.model.character.Character
import com.example.rickandmortyapi.domain.model.episode.Episode
import com.example.rickandmortyapi.domain.model.location.Location
import kotlinx.coroutines.flow.Flow

interface RepositoryRM {

    fun getAllCharacters(): Flow<PagingData<Character>>
    fun getAllEpisodes():Flow<PagingData<Episode>>
    fun getALlLocation():Flow<PagingData<Location>>
}