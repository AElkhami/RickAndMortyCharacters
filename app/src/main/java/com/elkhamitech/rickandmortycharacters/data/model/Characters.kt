package com.elkhamitech.rickandmortycharacters.data.model

data class Characters(
    val info: Info? = null,
    val results: List<Character> = listOf()
)