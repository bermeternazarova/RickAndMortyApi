package com.example.rickandmortyapi.domain.model.character

import com.example.rickandmortyapi.domain.model.base.Info

data class Characters(
    val info: Info,
    val results: List<Character>
)

