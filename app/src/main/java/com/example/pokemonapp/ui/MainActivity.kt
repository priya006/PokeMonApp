package com.example.pokemonapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonapp.domain.repository.PokemonRepository
import com.example.pokemonapp.network.RetrofitInstance
import com.example.pokemonapp.presentation.composables.NavigateFromListToDetailScreen
import com.example.pokemonapp.viewmodel.PokemonViewModel
import com.example.pokemonapp.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pokeApiService = RetrofitInstance.create()
        // Create an instance of your PokemonRepository
        val repository = PokemonRepository(pokeApiService)
        // Create a ViewModelProvider.Factory for the PokemonViewModel
        val viewModelFactory = ViewModelFactory(repository)
        setContent {
            val viewModel = ViewModelProvider(this, viewModelFactory).get(PokemonViewModel::class.java)
            NavigateFromListToDetailScreen(viewModel)
        }
    }
}