package com.example.pokemonapp.data.model

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)

data class Pokemon(
    val name: String,
    val url: String
)
{
    fun extractPokemonId(): Int? {
        // Define the regex pattern to match the number after the last '/'
        val regex = """[^/]+$""".toRegex()

        // Find the matched substring
        val matchResult = regex.find(url)

        // Extract the matched substring
        val matchedString = matchResult?.value ?: return null

        // Convert the matched substring to an integer
        return matchedString.toIntOrNull()
    }
}