package com.example.rickandmortyapi.data.model.episode

import com.example.rickandmortyapi.data.mapper.DataMapper
import com.example.rickandmortyapi.domain.model.episode.Episode

data class EpisodeDt(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
):DataMapper<Episode>{
    override fun toMapping(): Episode {
        return Episode(
            id, name, air_date, episode, characters, url, created
        )
    }
}
