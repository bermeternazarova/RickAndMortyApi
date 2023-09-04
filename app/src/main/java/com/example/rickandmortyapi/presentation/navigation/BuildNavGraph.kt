package com.example.rickandmortyapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmortyapi.presentation.base.BaseViewModel
import com.example.rickandmortyapi.presentation.screens.fragment.MainScreen
import com.example.rickandmortyapi.presentation.screens.fragment.character.CharacterScreen
import com.example.rickandmortyapi.presentation.screens.fragment.episode.EpisodeScreen
import com.example.rickandmortyapi.presentation.screens.fragment.location.LocationScreen

@Composable
fun BuildNavGraph(
    navController: NavHostController,
    viewModel: BaseViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoad.Main.path
    ) {
        addMainScreen(navController, this)
        addCharacterScreen(navController, this)
        addEpisodeScreen(navController, this)
        addLocationScreen(navController, this)
    }

}
private fun addMainScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        route = NavigationRoad.Main.path
    ) {
        MainScreen(navController = navController)
    }
}

private fun addCharacterScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(NavigationRoad.Character.path){
        CharacterScreen()
    }
}

private fun addEpisodeScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
   navGraphBuilder.composable(NavigationRoad.Episode.path){
       EpisodeScreen()
   }
}

private fun addLocationScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(NavigationRoad.Location.path){
        LocationScreen()
    }
}