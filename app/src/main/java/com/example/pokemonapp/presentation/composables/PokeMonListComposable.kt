package com.example.pokemonapp.presentation.composables.listpage


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemonapp.data.model.PokemonListResponse
import com.example.pokemonapp.data.model.PokeMonResult
import com.example.pokemonapp.data.model.Pokemon


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PokeMonListComposable(
    pokemonList: PokeMonResult<PokemonListResponse>,
    onItemClick: (Int) -> Unit
) {

    when (pokemonList) {
        is PokeMonResult.Success -> {
            LazyColumn {
                items(pokemonList.data.results) { item ->
                    PokemonListItem(pokemonList = item,  onItemClick)
                }
            }
        }

        is PokeMonResult.Error -> Text(text = "Error occurred:")
        PokeMonResult.Loading ->      CircularProgressIndicator(
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
                .semantics { contentDescription = "Loading..." }
        )
    }
}


@Composable
fun PokemonListItem(pokemonList: Pokemon,onItemClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onItemClick(pokemonList.extractPokemonId() ?: -1) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = pokemonList.name ?: "Service returned nothing",
                style = TextStyle(fontSize = 20.sp)
            )
        }
    }
}

