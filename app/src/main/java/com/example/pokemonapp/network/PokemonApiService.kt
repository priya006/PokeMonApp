package com.example.pokemonapp.network

import com.example.pokemonapp.data.model.PokemonDetailsResponse
import com.example.pokemonapp.data.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit interface defining endpoints for interacting with the Pokemon API.
 */
interface PokemonApiService {
    /**
     * Retrieves a list of Pokemon.
     * @return [PokemonListResponse] containing the list of Pokemon.
     */
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonListResponse

    /**
     * Retrieves a paginated list of Pokemon.
     * @param page The offset of the page to retrieve.
     * @param limit The maximum number of items per page. Default is 20.
     * @return [PokemonListResponse] containing the paginated list of Pokemon.
     */
    @GET("pokemon")
    suspend fun getPokemonListWithPagination(
        @Query("offset") page: Int,
        @Query("limit") limit: Int = 20
    ): PokemonListResponse

    /**
     * Retrieves details of a specific Pokemon.
     * @param pokeMonId The ID of the Pokemon to retrieve details for.
     * @return [PokemonDetailsResponse] containing the details of the specified Pokemon.
     */
    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") pokeMonId: Int): PokemonDetailsResponse
}