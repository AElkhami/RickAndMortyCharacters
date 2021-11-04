package com.elkhamitech.rickandmortycharacters.di

import com.elkhamitech.rickandmortycharacters.data.remote.RickAndMortyApi
import com.elkhamitech.rickandmortycharacters.data.repository.Repository
import com.elkhamitech.rickandmortycharacters.data.repository.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by A.Elkhami on 04,November,2021
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(api : RickAndMortyApi) = RickAndMortyRepository(api) as Repository
}