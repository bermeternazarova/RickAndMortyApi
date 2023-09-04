package com.example.rickandmortyapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyapi.presentation.base.BaseViewModel
import com.example.rickandmortyapi.presentation.navigation.BuildNavGraph
import com.example.rickandmortyapi.presentation.ui.theme.RickAndMortyApiTheme
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main(viewModel = viewModel())
        }
    }
}
@Composable
private fun Main(viewModel: BaseViewModel) {
    RickAndMortyApiTheme {
        val navController = rememberNavController()
        BuildNavGraph(navController = navController, viewModel =viewModel )
    }
}