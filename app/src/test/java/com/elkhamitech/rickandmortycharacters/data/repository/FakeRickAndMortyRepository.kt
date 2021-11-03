package com.elkhamitech.rickandmortycharacters.data.repository

import com.elkhamitech.rickandmortycharacters.data.model.Characters
import com.elkhamitech.rickandmortycharacters.data.other.Resource
import com.elkhamitech.rickandmortycharacters.data.stub.CharactersStub

/**
 * Created by A.Elkhami on 03,November,2021
 */
class FakeRickAndMortyRepository : Repository {

    private var shouldReturnError = false

    fun setShouldReturnError(value: Boolean){
        shouldReturnError = value
    }

    override suspend fun getRickAndMortyCharacters(pageNumber: Int)
            : Resource<Characters> {
        return if (shouldReturnError) {
            Resource.Error(message = "error")
        } else {
            Resource.Success(CharactersStub.characters)
        }

    }

}