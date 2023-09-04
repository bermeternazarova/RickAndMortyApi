package com.example.rickandmortyapi.presentation.screens.fragment.location

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyapi.domain.model.location.Location
import com.example.rickandmortyapi.domain.usecase.GetAllLocationsUseCase
import com.example.rickandmortyapi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class LocationViewModel (
    private val getAllLocationsUseCase: GetAllLocationsUseCase
) : BaseViewModel() {

    fun getAllLocation(): Flow<PagingData<Location>> {
        return  getAllLocationsUseCase().cachedIn(viewModelScope)
    }

    private val _searchTxt = MutableStateFlow("")
    val searchTxt = _searchTxt.asStateFlow()

    private val _isSeaching = MutableStateFlow(false)
    val isSeaching = _isSeaching.asStateFlow()

    private val _location= MutableStateFlow(listOf<Location>())
    val location =searchTxt.combine(_location){txt , location ->
        if (txt.isBlank()) location
        else location.filter { matchSearchQuery(query = txt, location = location.single() ) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),
    _location.value)

    fun matchSearchQuery (query:String,location: Location):Boolean{
        val matchCombination = listOf(
            "${location.name}${location.created}",
            "${location.name.first()}${location.created.first()}"
        )
        return matchCombination.any { it.contains(query,ignoreCase = true) }
    }

    fun onSearchTextChange(text:String){
        _searchTxt.value=text
    }
}