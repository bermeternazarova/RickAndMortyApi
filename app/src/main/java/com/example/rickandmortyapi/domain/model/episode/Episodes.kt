package com.example.rickandmortyapi.domain.model.episode

import com.example.rickandmortyapi.domain.model.base.Info

data class Episodes(
    val info: Info,
    val results: List<Episode>
)