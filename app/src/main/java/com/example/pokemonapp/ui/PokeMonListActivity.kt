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

/**
 * Activity responsible for displaying the Pokémon list and handling navigation to the detail screen.
 * This activity is injected with the necessary dependencies using Dagger Hilt and sets up the navigation flow
 * using Jetpack Navigation Compose.
 */

@AndroidEntryPoint
class PokeMonListActivity : ComponentActivity() {

    @Inject
    lateinit var pokeMonViewModelFactory : PokeMonViewModelFactory

    /**
     * Called when the activity is first created. Sets up the content view using Jetpack Compose
     * and initializes the ViewModel using Dagger Hilt.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     * this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     * Otherwise, it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = ViewModelProvider(this, pokeMonViewModelFactory).get(PokemonViewModel::class.java)
            // Set up the navigation flow
            NavigateFromListToDetailScreen(viewModel)
        }
    }
}