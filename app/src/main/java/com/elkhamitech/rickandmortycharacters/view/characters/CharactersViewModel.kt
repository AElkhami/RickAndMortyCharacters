package com.elkhamitech.rickandmortycharacters.view.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elkhamitech.rickandmortycharacters.data.other.Status
import com.elkhamitech.rickandmortycharacters.data.repository.Repository
import com.elkhamitech.rickandmortycharacters.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState(isLoading = true))
    var uiStateFlow = _uiState.asStateFlow()

    fun getCharacters(pageNumber: Int) {
        viewModelScope.launch {
            val response = repository.getRickAndMortyCharacters(pageNumber)

            when (response.status) {
                Status.SUCCESS -> {
                    response.data?.let { characters ->
                        _uiState.value = UiState(
                            characters = characters.results,
                            isLoading = false
                        )
                    } ?: run {
                        _uiState.value = UiState(
                            error = UiState.Error.NetworkError,
                            isLoading = false
                        )
                    }
                }
                Status.FAILED -> {
                    response.message?.let { message ->
                        _uiState.value = UiState(
                            error = UiState.Error.UnknownError,
                            message = message,
                            isLoading = false
                        )
                    } ?: run {
                        _uiState.value = UiState(
                            error = UiState.Error.UnknownError,
                            isLoading = false
                        )
                    }
                }
                Status.LOADING -> {
                    _uiState.value = UiState(
                        isLoading = true
                    )
                }
            }
        }
    }

}