package com.example.pokemonapp.data.model

import android.net.Uri

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)

data class Pokemon(
    val name: String,
    val url: String?
)
{
    fun extractPokemonId(): Int? {
        try {
            // Parse the URL using Uri.parse
            val uri = Uri.parse(url)
            // Get the last path segment which should be the ID
            val idString = uri.lastPathSegment
            // Convert the ID string to an integer
            return idString?.toIntOrNull()
        } catch (e: Exception) {
            // Handle any exceptions that might occur during parsing
            return null
        }
    }
}