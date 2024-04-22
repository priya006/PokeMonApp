package com.example.pokemonapp.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.pokemonapp.viewmodel.PokemonViewModel

@Composable
fun MasterDetailView(
    pokemonViewModel: PokemonViewModel,
    onItemClick: (String) -> Unit
) {
    // Create a state to hold the selected Pokemon name
    val selectedPokemonName = remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            PagingListPage(pokemonViewModel, { pokemonName ->
                // Update the selected Pokemon name when an item is clicked
                selectedPokemonName.value = pokemonName
                onItemClick(pokemonName)
            })
        }
        Box(modifier = Modifier.weight(1f)) {
            // Display the details of the selected Pokemon
            if (selectedPokemonName.value.isNotEmpty()) {
                PokemonDetailScreenWithSearch(pokemonViewModel, selectedPokemonName.value)
            }
        }
    }
}