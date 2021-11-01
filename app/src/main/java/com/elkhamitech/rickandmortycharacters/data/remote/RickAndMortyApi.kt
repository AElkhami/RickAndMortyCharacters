package com.elkhamitech.rickandmortycharacters.data.remote

import com.elkhamitech.rickandmortycharacters.data.model.Characters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by A.Elkhami on 01,November,2021
 */
interface RickAndMortyApi {
    @GET("/character")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<Characters>
}