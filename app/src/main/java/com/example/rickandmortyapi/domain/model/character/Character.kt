package com.example.rickandmortyapi.domain.model.character

import com.example.rickandmortyapi.domain.model.base.Location
import com.example.rickandmortyapi.domain.model.base.Origin

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image:String,
    val episode:List<String>,
    val url:String,
    val created:String
)
