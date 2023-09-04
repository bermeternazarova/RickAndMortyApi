package com.example.rickandmortyapi.data.model.character

import com.example.rickandmortyapi.data.mapper.DataMapper
import com.example.rickandmortyapi.domain.model.base.Origin

data class OriginCharacterDt(
    val name: String,
    val url: String
):DataMapper<Origin>{
    override fun toMapping(): Origin {
        return Origin(
            name, url
        )
    }
}