package com.example.rickandmortyapi.presentation.screens.fragment.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyapi.domain.model.episode.Episode
import com.example.rickandmortyapi.domain.usecase.GetAllEpisodesUseCase
import com.example.rickandmortyapi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class EpisodeViewModel (
    private val getAllEpisodesUseCase: GetAllEpisodesUseCase
) : BaseViewModel() {

    private val _searchTxt = MutableStateFlow("")
    val searchTxt = _searchTxt.asStateFlow()

    fun getAllEpisodes():Flow<PagingData<Episode>>{
        return getAllEpisodesUseCase().cachedIn(viewModelScope)
    }

}