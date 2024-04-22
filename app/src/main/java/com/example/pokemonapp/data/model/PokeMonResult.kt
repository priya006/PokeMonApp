package com.example.pokemonapp.data.model


/**
 * A sealed class representing the result of an operation, which can be either loading, successful, or an error.
 *
 * @param T The type of data returned in case of success.
 */
sealed class PokeMonResult<out T> {
    /**
     * Represents the loading state of an operation.
     */
    data object Loading : PokeMonResult<Nothing>()

    /**
     * Represents a successful operation with the result data.
     *
     * @param data The data resulting from a successful operation.
     */
    data class Success<out T>(val data: T) : PokeMonResult<T>()

    /**
     * Represents an error state of an operation with an associated error message and optional cause.
     *
     * @param message The error message describing the failure.
     * @param cause The optional cause of the error, typically an exception.
     */
    data class Error(val message: String, val cause: Exception? = null) : PokeMonResult<Nothing>() {
        override fun toString(): String {
            return "Error(message='$message', cause=${cause?.message})"
        }
    }
}