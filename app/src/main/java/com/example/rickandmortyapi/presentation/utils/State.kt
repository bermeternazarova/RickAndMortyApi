package com.example.rickandmortyapi.presentation.utils

import com.example.rickandmortyapi.domain.model.character.Character


data class CharactersState(
    val characters :List<Character> = emptyList(),
    val isLoading :Boolean= false
)