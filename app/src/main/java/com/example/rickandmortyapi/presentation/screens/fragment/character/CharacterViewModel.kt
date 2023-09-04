package com.example.rickandmortyapi.presentation.screens.fragment.character

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyapi.data.repository.RepositoryImple
import com.example.rickandmortyapi.domain.model.character.Character
import com.example.rickandmortyapi.domain.usecase.GetAllCharactersUseCase
import com.example.rickandmortyapi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterViewModel (
 //private val repositoryImple: RepositoryImple
private val getAllCharactersUseCase: GetAllCharactersUseCase
):BaseViewModel() {

    fun getAllCharacters():Flow<PagingData<Character>>{
        return getAllCharactersUseCase().cachedIn(viewModelScope)
      //  repositoryImple.getAllCharacters().cachedIn(viewModelScope)
    }

}