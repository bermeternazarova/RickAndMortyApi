package com.example.rickandmortyapi.data.remote

import com.example.rickandmortyapi.data.base.BaseResponse
import com.example.rickandmortyapi.data.model.character.CharacterDt
import com.example.rickandmortyapi.data.model.episode.EpisodeDt
import com.example.rickandmortyapi.data.model.location.LocationDt
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ):BaseResponse<CharacterDt>

    @GET("location")
    suspend fun getAllLocation(
        @Query("page")page:Int
    ):BaseResponse<LocationDt>

    @GET("episode")
    suspend fun getAllEpisode(
        @Query("page")page:Int
    ):BaseResponse<EpisodeDt>
}