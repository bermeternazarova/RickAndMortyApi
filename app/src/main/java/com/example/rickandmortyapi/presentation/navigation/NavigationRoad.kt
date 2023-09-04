package com.example.rickandmortyapi.presentation.navigation

open class NavigationRoad (
    val path:String
    ) {
    object Main : NavigationRoad("MainScreen")
    object Character : NavigationRoad("CharacterScreen")
    object Episode : NavigationRoad("EpisodeScreen")
    object Location : NavigationRoad("LocationScreen")
}