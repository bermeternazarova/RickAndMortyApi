package com.example.rickandmortyapi.presentation.di

import com.example.rickandmortyapi.data.repository.RepositoryImple
import com.example.rickandmortyapi.domain.repository.RepositoryRM
import com.example.rickandmortyapi.domain.usecase.GetAllCharactersUseCase
import com.example.rickandmortyapi.domain.usecase.GetAllEpisodesUseCase
import com.example.rickandmortyapi.domain.usecase.GetAllLocationsUseCase
import com.example.rickandmortyapi.presentation.screens.fragment.character.CharacterViewModel
import com.example.rickandmortyapi.presentation.screens.fragment.episode.EpisodeViewModel
import com.example.rickandmortyapi.presentation.screens.fragment.location.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    viewModel { CharacterViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { EpisodeViewModel(get()) }
//    viewModelOf(::CharacterViewModel)
//    viewModelOf(::LocationViewModel)
//    viewModelOf(::EpisodeViewModel)
}
val dataModule = module {
    single <RepositoryRM>{ RepositoryImple(get()) }
   // singleOf(::RepositoryImple){bind<RepositoryRM>()}
}
val domainModule = module {
    factory { GetAllCharactersUseCase(get()) }
    factory { GetAllEpisodesUseCase(get()) }
    factory { GetAllLocationsUseCase(get()) }
}