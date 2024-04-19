package com.example.pokemonapp.presentation.composables.detailpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.pokemonapp.data.model.PokeMonResult
import com.example.pokemonapp.data.model.PokemonDetailsResponse

@Composable
fun PokeMonDetailComposable(pokemonDetails: PokeMonResult<PokemonDetailsResponse>?, pokeMonId : Int) {

    when(pokemonDetails) {
        is PokeMonResult.Success -> {
            val details = pokemonDetails.data
            val sprites = details.sprites

            // Create a new instance of PokemonDetailsResponse with modified id
            val modifiedDetails = details.copy(id = pokeMonId)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
            {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberImagePainter(sprites.front_shiny ?: sprites.back_default ),
                        contentDescription = "Placeholder Image",
                        modifier = Modifier
                            .size(500.dp) // Set the size of the image
                            .padding(8.dp), // Add padding around the image
                        contentScale = ContentScale.Fit // Scale the image to fit the container
                    )
                    Text("ID: ${modifiedDetails.id}")
                    Text("Name: ${modifiedDetails.name}")
                    Text("Height: ${modifiedDetails.height}")
                    Text("Weight: ${modifiedDetails.weight}")
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
        null -> Text("No data available")
    }
}

