package com.elkhamitech.rickandmortycharacters.di

import com.elkhamitech.rickandmortycharacters.BuildConfig
import com.elkhamitech.rickandmortycharacters.data.remote.RickAndMortyApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by A.Elkhami on 01,November,2021
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder().apply {
        interceptors().add(httpLoggingInterceptor)
    }.build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(BuildConfig.BASE_URL)
            addConverterFactory(GsonConverterFactory.create(Gson()))
            client(okHttpClient)
        }.build()

    @Singleton
    @Provides
    fun provideRickAndMortyApi(retrofit: Retrofit) : RickAndMortyApi =
        retrofit.create(RickAndMortyApi::class.java)

}