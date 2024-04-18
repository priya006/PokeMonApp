package com.example.pokemonapp.domain.repository

import com.example.pokemonapp.data.model.PokemonListResponse
import com.example.pokemonapp.network.PokeMonApiService

class PokemonRepository(val pokeMonApiService : PokeMonApiService) {

    suspend fun getPokemonList(): PokemonListResponse {
        return pokeMonApiService.getPokemonList()
    }

}