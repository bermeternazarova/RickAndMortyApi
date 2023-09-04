package com.example.rickandmortyapi.data.model.location

import com.example.rickandmortyapi.data.mapper.DataMapper
import com.example.rickandmortyapi.domain.model.location.Location

data class LocationDt(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
):DataMapper<Location>{
    override fun toMapping(): Location {
        return Location(
            id, name, type, dimension, residents, url, created
        )
    }
}
