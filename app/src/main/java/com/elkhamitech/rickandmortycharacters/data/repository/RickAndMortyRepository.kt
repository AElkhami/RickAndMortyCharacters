package com.elkhamitech.rickandmortycharacters.data.repository

import com.elkhamitech.rickandmortycharacters.data.model.Characters
import com.elkhamitech.rickandmortycharacters.data.other.Resource
import com.elkhamitech.rickandmortycharacters.data.remote.RickAndMortyApi
import javax.inject.Inject

/**
 * Created by A.Elkhami on 01,November,2021
 */
class RickAndMortyRepository @Inject constructor(
    private val api: RickAndMortyApi
) : Repository {

    override suspend fun getRickAndMortyCharacters(pageNumber: Int): Resource<Characters> {
        val response = api.getAllCharacters(pageNumber)
        if (response.isSuccessful) {
            response.body()?.let { characters ->
                return Resource.Success(characters)
            }
                ?: return Resource.Error(message = response.message())
        } else {
            return Resource.Error(message = response.message())
        }
    }

}