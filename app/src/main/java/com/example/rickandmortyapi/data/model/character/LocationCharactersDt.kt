package com.example.rickandmortyapi.data.model.character

import com.example.rickandmortyapi.data.mapper.DataMapper
import com.example.rickandmortyapi.domain.model.base.Location

data class LocationCharactersDt(
    val name: String,
    val url: String
):DataMapper<Location>{
    override fun toMapping(): Location {
        return Location(
            name, url
        )
    }
}
