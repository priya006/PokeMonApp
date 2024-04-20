package com.example.pokemonapp.presentation.composables.detailpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.pokemonapp.data.model.PokeMonResult
import com.example.pokemonapp.viewmodel.PokemonViewModel

/**
 * A composable function to display details of a Pokémon.
 *
 * @param pokemonViewModel The [PokemonViewModel] responsible for fetching Pokémon details.
 * @param pokeMonId The ID of the Pokémon whose details should be displayed.
 * @param modifier The modifier for the composable, used to customize its layout and appearance.
 */
@Composable
fun PokeMonDetailComposable(pokemonViewModel: PokemonViewModel,
                            pokeMonId: Int,
                            modifier: Modifier = Modifier) {


    val pokemonDetails by pokemonViewModel.pokemonDetails.observeAsState()
    LaunchedEffect(pokeMonId) {
        pokemonViewModel.fetchPokemonDetails(pokeMonId)
    }
    when(val pokemonDetailsResult = pokemonDetails) {
        is PokeMonResult.Success -> {
            val pokemonDetails = pokemonDetailsResult.data
            val spritesImage = pokemonDetails.sprites

            // Create a new instance of PokemonDetailsResponse with modified id
            val pokeMonDetailsModified = pokemonDetails.copy(id = pokeMonId)
            val types = pokemonDetails.types
            val stats = pokemonDetails.stats
            val typeName = types.firstOrNull()?.type?.name ?: "Unknown Type"
            val statsNumber = stats.firstOrNull()?.base_stat ?: 0


            Box() {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberImagePainter(
                            spritesImage.front_shiny ?: spritesImage.back_default
                        ),
                        contentDescription = "Pokemon Image",
                        modifier = Modifier
                            .size(550.dp) // Set the size of the image
                            .padding(8.dp), // Add padding around the image
                        contentScale = ContentScale.Fit // Scale the image to fit the container
                    )
                    Text("ID:     ${pokeMonDetailsModified.id}")
                    Text("Name:   ${pokeMonDetailsModified.name}")
                    Text("Height: ${pokeMonDetailsModified.height}")
                    Text("Weight: ${pokeMonDetailsModified.weight}")
                    Text("Type:   ${typeName}")
                    Text("Stats:  ${statsNumber}")
                }
            }
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
            {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberImagePainter(
                            spritesImage.front_shiny ?: spritesImage.back_default
                        ),
                        contentDescription = "Pokemon Image",
                        modifier = Modifier
                            .size(500.dp) // Set the size of the image
                            .padding(8.dp), // Add padding around the image
                        contentScale = ContentScale.Fit // Scale the image to fit the container
                    )
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
        null -> Text("No Data to Render in Detailed Screen")
    }
}

