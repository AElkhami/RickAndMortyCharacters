package com.elkhamitech.rickandmortycharacters.view.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elkhamitech.rickandmortycharacters.data.model.Character
import com.elkhamitech.rickandmortycharacters.data.other.Status
import com.elkhamitech.rickandmortycharacters.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: Repository) : ViewModel() {

    private val _charactersList = MutableStateFlow<List<Character>>(listOf())
    var charactersList = _charactersList.asStateFlow()

    private val _errorMessage = MutableStateFlow(String())
    var errorMessage = _errorMessage.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    var loadingState = _loadingState.asStateFlow()

    fun getCharacters(pageNumber: Int) {
        viewModelScope.launch {
            val response = repository.getRickAndMortyCharacters(pageNumber)

            when (response.status) {
                Status.SUCCESS -> {
                    response.data?.let { characters ->
                        _charactersList.value = characters.results
                    }
                    _loadingState.value = false
                }
                Status.FAILED -> {
                    response.message?.let { message ->
                        _errorMessage.value = message
                    }
                    _loadingState.value = false
                }
                Status.LOADING -> {
                    _loadingState.value = true
                }
            }
        }
    }

}