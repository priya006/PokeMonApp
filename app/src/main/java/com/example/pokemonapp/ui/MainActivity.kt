package com.example.pokemonapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pokemonapp.domain.repository.PokemonRepository
import com.example.pokemonapp.network.RetrofitInstance
import com.example.pokemonapp.presentation.composables.SetupNavigation
import com.example.pokemonapp.viewmodel.PokemonViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pokeApiService = RetrofitInstance.create()
        // Create an instance of your PokemonRepository
        val repository = PokemonRepository(pokeApiService)
        // Create an instance of your PokemonViewModel
        val viewModel = PokemonViewModel(repository)
        setContent {
            SetupNavigation(viewModel)
        }
    }
}