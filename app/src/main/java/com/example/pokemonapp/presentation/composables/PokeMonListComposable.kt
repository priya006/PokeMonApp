package com.example.pokemonapp.presentation.composables.listpage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokemonapp.data.model.PokeMonResult
import com.example.pokemonapp.presentation.composables.PokemonListItem
import com.example.pokemonapp.viewmodel.PokemonViewModel
@Composable
fun PokeMonListComposable(
    pokemonViewModel: PokemonViewModel,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val pokemonList by pokemonViewModel.pokemonList.observeAsState()
    LazyColumn (modifier = modifier) {
        when (val result = pokemonList) {
            is PokeMonResult.Success -> {
                val pokemonList = result.data.results

                items(pokemonList) { pokemon ->
                    PokemonListItem(pokemon = pokemon, onItemClick)
                }
            }

            is PokeMonResult.Error -> {
                item {
                    Text(
                        text = "Error occurred:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }

            PokeMonResult.Loading -> {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }

            null -> {
                item {
                    Text(
                        text = "Data not available",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}


