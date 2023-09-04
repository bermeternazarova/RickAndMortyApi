package com.example.rickandmortyapi.domain.model.base

data class Info(
    val count: Int,
    val page: Int,
    val next: String,
    val prev: Any
)
