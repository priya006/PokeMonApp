package com.example.pokemonapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonapp.domain.repository.PokemonRepository

/**
 * ViewModelFactory class responsible for instantiating ViewModels with dependencies.
 *
 * @property repository The repository to be injected into the ViewModels.
 */
class ViewModelFactory(private val repository: PokemonRepository) : ViewModelProvider.Factory {
    /**
     * Creates a new instance of the specified ViewModel class.
     *
     * @param modelClass The class of the ViewModel to be instantiated.
     * @return An instance of the specified ViewModel class.
     * @throws IllegalArgumentException if the provided ViewModel class is unknown.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            return PokemonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}