package com.example.rickandmortyapi.data.mapper

interface DataMapper<T> {
    fun toMapping():T
}