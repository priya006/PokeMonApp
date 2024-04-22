package com.example.pokemonapp.presentation.data

import com.example.pokemonapp.data.model.PokemonDetailsResponse
import com.example.pokemonapp.data.model.PokemonListResponse
import com.example.pokemonapp.data.model.Sprites
import com.example.pokemonapp.data.model.Stat
import com.example.pokemonapp.data.model.Stats
import com.example.pokemonapp.data.model.Type
import com.example.pokemonapp.data.model.TypeInfo
import com.example.pokemonapp.network.PokemonApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

// Mocking the behaviour of  PokemonApiService for the Jetpack Preview
class FakePokemonApiService : PokemonApiService {
    override suspend fun getPokemonList(): PokemonListResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonListWithPagination(page: Int, limit: Int): PokemonListResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonDetails(pokemonName: String): PokemonDetailsResponse {
        return PokemonDetailsResponse(
            id = 25,
            name = "Pikachu",
            height = 40,
            weight = 60,
            types = listOf(Type(1, TypeInfo("electric", "type_url"))),
            sprites = Sprites( back_default = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png",back_female = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png", front_shiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png"),
            stats = listOf(Stats(55, 100, Stat("speed", "stat_url")))
        )
    }

    fun getPokemonDetailsFlow(pokemonName: String): Flow<PokemonDetailsResponse> {
        // Simulate an asynchronous API call with predefined Pokemon details using Flow
        return flowOf(
            PokemonDetailsResponse(
                id = 25,
                name = "Pikachu",
                height = 40,
                weight = 60,
                types = listOf(Type(1, TypeInfo("electric", "type_url"))),
                sprites = Sprites(back_default = null, back_female = null, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png"),
                stats = listOf(Stats(55, 100, Stat("speed", "stat_url")))
            )
        )
    }
}
