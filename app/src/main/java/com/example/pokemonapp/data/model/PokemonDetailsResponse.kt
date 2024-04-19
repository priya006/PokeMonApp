package com.example.pokemonapp.data.model

data class PokemonDetailsResponse(
    val id: Int,
    val name: String?,
    val height: Int,
    val weight: Int,
    val types: List<Type>,
    val sprites: Sprites
)

data class Type(
    val slot: Int,
    val type: TypeInfo
)

data class TypeInfo(
    val name: String,
    val url : String
)

data class Sprites (
    val back_default : String?,
    val back_female : String?,
    val front_shiny : String?
)