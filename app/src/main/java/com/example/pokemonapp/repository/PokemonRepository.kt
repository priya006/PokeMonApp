package com.example.pokemonapp.domain.repository

import com.example.pokemonapp.data.model.PokemonDetailsResponse
import com.example.pokemonapp.data.model.PokemonListResponse
import com.example.pokemonapp.network.PokemonApiService
import javax.inject.Inject

/**
 * Repository responsible for handling data operations related to Pokémon.
 *
 * @property pokemonApiService The service interface for making API calls related to Pokémon.
 */
class PokemonRepository @Inject constructor(private val pokemonApiService : PokemonApiService) {

    /**
     * Fetches the list of Pokémon from the API.
     *
     * @return The list of Pokémon retrieved from the API.
     */
    suspend fun getPokemonList(): PokemonListResponse {
        return pokemonApiService.getPokemonList()
    }

    /**
     * Fetches the details of a specific Pokémon identified by [pokeMonId] from the API.
     *
     * @param pokemonName The ID of the Pokémon whose details are to be fetched.
     * @return The details of the specified Pokémon retrieved from the API.
     */
    suspend fun getPokemonDetails(pokemonName : String): PokemonDetailsResponse {
        return pokemonApiService.getPokemonDetails(pokemonName)
    }

    suspend fun getPokemonListPagination(offset : Int): PokemonListResponse {
        return pokemonApiService.getPokemonListWithPagination(offset)
    }
}