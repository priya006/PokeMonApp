package com.example.pokemonapp.presentation.composables.detailpage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.pokemonapp.data.model.PokeMonResult
import com.example.pokemonapp.data.model.PokemonDetailsResponse

@Composable
fun PokeMonDetailComposable(pokemonDetails: PokeMonResult<PokemonDetailsResponse>?) {
    // Implement your detail view UI here
    Text(text = "Detail view for ${pokemonDetails}")
}