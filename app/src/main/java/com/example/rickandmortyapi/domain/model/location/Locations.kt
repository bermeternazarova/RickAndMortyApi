package com.example.rickandmortyapi.domain.model.location

import com.example.rickandmortyapi.domain.model.base.Info

data class Locations(
    val info: Info,
    val results: List<Location>
)