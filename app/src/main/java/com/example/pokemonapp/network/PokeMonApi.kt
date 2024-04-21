package com.example.pokemonapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object PokeMonApi {
    const val BASE_URL = "https://pokeapi.co/api/v2/"

    /**
     * Creates and provides an instance of [PokemonApiService] using Retrofit.
     */
    fun createService(): PokemonApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApiService::class.java)
    }
}