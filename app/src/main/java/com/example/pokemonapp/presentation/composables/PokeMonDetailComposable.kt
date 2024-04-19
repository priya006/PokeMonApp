package com.example.pokemonapp.presentation.composables.detailpage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.pokemonapp.data.model.PokeMonResult
import com.example.pokemonapp.data.model.PokemonDetailsResponse

@Composable
fun PokeMonDetailComposable(pokemonDetails: PokeMonResult<PokemonDetailsResponse>?, pokeMonId : Int) {

    when(pokemonDetails) {
        is PokeMonResult.Success -> {
            val details = pokemonDetails.data // Accessing the data property directly

            // Create a new instance of PokemonDetailsResponse with modified id
            val modifiedDetails = details.copy(id = pokeMonId)

            // Render Pokemon details here
            Column {
                Text("ID: ${modifiedDetails.id}")
                Text("Name: ${modifiedDetails.name}")
                Text("Height: ${modifiedDetails.height}")
                Text("Weight: ${modifiedDetails.weight}")
            }
        }

        is PokeMonResult.Error -> Text(text = "Error occurred:")
        PokeMonResult.Loading ->      CircularProgressIndicator(
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
                .semantics { contentDescription = "Loading..." }
        )

        null -> Text("No data available")
    }
}

