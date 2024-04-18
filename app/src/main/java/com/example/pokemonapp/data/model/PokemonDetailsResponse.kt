package com.example.pokemonapp.data.model

data class PokemonDetailsResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<Type>,
)

data class Type(
    val slot: Int,
    val type: TypeInfo
)

data class TypeInfo(
    val name: String,
    val url : String
)
