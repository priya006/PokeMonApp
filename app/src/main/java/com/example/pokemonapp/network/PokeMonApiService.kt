package com.example.pokemonapp.network

import com.example.pokemonapp.data.model.PokemonListResponse
import retrofit2.http.GET

interface PokeMonApiService {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonListResponse
}