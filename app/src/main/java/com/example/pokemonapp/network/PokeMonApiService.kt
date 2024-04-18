package com.example.pokemonapp.network

import com.example.pokemonapp.data.model.PokemonDetailsResponse
import com.example.pokemonapp.data.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeMonApiService {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") pokemonId: Int): PokemonDetailsResponse
}