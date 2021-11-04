package com.elkhamitech.rickandmortycharacters.view

import com.elkhamitech.rickandmortycharacters.data.model.Character

/**
 * Created by A.Elkhami on 04,November,2021
 */
data class UiState(
    val characters: List<Character> = listOf(),
    val error: Error? = null,
    val message: String? = null,
    val isLoading: Boolean = false
) {
    sealed class Error {
        object NetworkError : Error()
        object UnknownError : Error()
    }
}