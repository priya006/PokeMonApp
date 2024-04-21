package com.example.pokemonapp.domain.repository

import com.example.pokemonapp.data.model.PokemonDetailsResponse
import com.example.pokemonapp.data.model.PokemonListResponse
import com.example.pokemonapp.network.PokeMonApiService

/**
 * Repository responsible for handling data operations related to Pokémon.
 *
 * @property pokeMonApiService The service interface for making API calls related to Pokémon.
 */
class PokemonRepository(val pokeMonApiService : PokeMonApiService) {

    /**
     * Fetches the list of Pokémon from the API.
     *
     * @return The list of Pokémon retrieved from the API.
     */
    suspend fun getPokemonList(): PokemonListResponse {
        return pokeMonApiService.getPokemonList()
    }

    /**
     * Fetches the details of a specific Pokémon identified by [pokeMonId] from the API.
     *
     * @param pokeMonId The ID of the Pokémon whose details are to be fetched.
     * @return The details of the specified Pokémon retrieved from the API.
     */
    suspend fun getPokemonDetails(pokeMonId : Int): PokemonDetailsResponse {
        return pokeMonApiService.getPokemonDetails(pokeMonId)
    }

    suspend fun getPokemonDetailsPaginationDetails(offset : Int): PokemonListResponse {
        return pokeMonApiService.getPokemonListWithPagination(offset)
    }
}