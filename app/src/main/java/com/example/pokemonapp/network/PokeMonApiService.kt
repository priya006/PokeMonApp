package com.example.pokemonapp.network

import com.example.pokemonapp.data.model.PokemonDetailsResponse
import com.example.pokemonapp.data.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeMonApiService {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonListResponse

    @GET("pokemon")
    suspend fun getPokemonListWithPagination(
        @Query("offset") page: Int,
        @Query("limit") limit: Int = 20
    ): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") pokeMonId: Int): PokemonDetailsResponse
}