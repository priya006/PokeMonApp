package com.example.pokemonapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonapp.presentation.composables.NavigateFromListToDetailScreen
import com.example.pokemonapp.viewmodel.PokeMonViewModelFactory
import com.example.pokemonapp.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokeMonListActivity : ComponentActivity() {

    @Inject
    lateinit var pokeMonViewModelFactory : PokeMonViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = ViewModelProvider(this, pokeMonViewModelFactory).get(PokemonViewModel::class.java)
            NavigateFromListToDetailScreen(viewModel)
        }
    }
}