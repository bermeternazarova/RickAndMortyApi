package com.example.rickandmortyapi.presentation.screens.fragment

import androidx.compose.runtime.Composable
import com.example.rickandmortyapi.presentation.screens.fragment.character.CharacterScreen
import com.example.rickandmortyapi.presentation.screens.fragment.location.LocationScreen
import com.example.rickandmortyapi.presentation.screens.fragment.episode.EpisodeScreen

typealias ComposableFunction = @Composable ()->Unit
sealed class TabItem (
    val title:String,
    val screen:ComposableFunction
    ){

    object Character:TabItem("Characters", screen = { CharacterScreen()})
    object Location:TabItem("Locations", screen = { LocationScreen()})
    object Episode:TabItem("Episodes", screen = { EpisodeScreen() })
}