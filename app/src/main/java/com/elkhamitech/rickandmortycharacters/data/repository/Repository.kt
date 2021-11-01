package com.elkhamitech.rickandmortycharacters.data.repository

import com.elkhamitech.rickandmortycharacters.data.model.Characters
import com.elkhamitech.rickandmortycharacters.data.other.Resource

/**
 * Created by A.Elkhami on 01,November,2021
 */
interface Repository {

    suspend fun getRickAndMortyCharacters(pageNumber: Int): Resource<Characters>
}