package com.example.pokemonapp.data.model

sealed class PokeMonResult<out T> {
    object Loading : PokeMonResult<Nothing>()
    data class Success<out T>(val data: T) : PokeMonResult<T>()
    data class Error(val message: String, val cause: Exception? = null) : PokeMonResult<Nothing>()

}