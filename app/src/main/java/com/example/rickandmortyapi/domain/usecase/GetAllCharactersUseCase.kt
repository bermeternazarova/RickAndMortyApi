package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.domain.repository.RepositoryRM

class GetAllCharactersUseCase(
    private val repositoryRM: RepositoryRM
) {
    operator fun invoke() =
       repositoryRM.getAllCharacters()
}