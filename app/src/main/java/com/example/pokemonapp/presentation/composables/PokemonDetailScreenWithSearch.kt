package com.example.pokemonapp.presentation.composables.detailpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.pokemonapp.data.model.PokeMonResult
import com.example.pokemonapp.viewmodel.PokemonViewModel

/**
 * Composable function to display detailed information about a Pokemon, including its name, ID, height,
 * weight, type, and stats. Allows searching for Pokemon by name and toggling auto search.
 *
 * @param pokemonViewModel The view model responsible for handling Pokemon data.
 * @param pokemonName The name of the Pokemon to display detailed information about.
 */
@Composable
fun PokemonDetailScreenWithSearch(
    pokemonViewModel: PokemonViewModel,
    pokemonName: String
) {
    // Collecting the Pokemon details, search text, and switch toggle state from the view model
    val pokemonDetails by pokemonViewModel.pokemonDetails.collectAsState()
    val searchText by pokemonViewModel.searchText.collectAsState()
    val isSwitchToggled by pokemonViewModel.isSwitchToggled.collectAsState()

    // Launching an effect to fetch Pokemon details based on the provided name
    LaunchedEffect(pokemonName) {
        val nameToLoad = searchText?.takeIf { it.isNotBlank() } ?: pokemonName
        pokemonViewModel.fetchPokemonDetails(nameToLoad)
    }

   Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { newTextEntered -> pokemonViewModel.onSearchTextChange(newTextEntered) },
                enabled = true,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Pokemon")
                },
                label = { Text(text = "Enter full pokemon name") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Auto search after 1 second",
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )

                Switch(
                    checked = isSwitchToggled,
                    onCheckedChange = { newState -> pokemonViewModel.onSwitchToggledChange(newState) },
                )
            }

            Button(
                onClick = { pokemonViewModel.fetchPokemonDetails(searchText) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Search")
            }

            when (val pokemonDetailsResult = pokemonDetails) {
                is PokeMonResult.Success -> {
                    val pokemonDetailsResultData = pokemonDetailsResult.data
                    val spritesImage = pokemonDetailsResultData.sprites

                    if (pokemonDetailsResultData != null) {
                        val pokemonName = pokemonDetailsResultData.name ?: "Unknown Name"
                        // Create a new instance of PokemonDetailsResponse with modified id
                        val pokeMonDetailsModified =
                            pokemonDetailsResultData.copy(name = pokemonName)
                        val types = pokemonDetailsResultData.types
                        val stats = pokemonDetailsResultData.stats
                        val typeName = types.firstOrNull()?.type?.name ?: "Unknown Type"
                        val statsNumber = stats.firstOrNull()?.base_stat ?: 0
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                painter = rememberImagePainter(
                                    spritesImage.front_shiny ?: spritesImage.back_default
                                ),
                                contentDescription = "Pokemon Image",
                                modifier = Modifier
                                    .size(200.dp) // Set the size of the image
                                    .padding(8.dp), // Add padding around the image
                                contentScale = ContentScale.Fit // Scale the image to fit the container
                            )
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("ID:     ${pokeMonDetailsModified.id}")
                                Text("Name:   ${pokeMonDetailsModified.name}")
                                Text("Height: ${pokeMonDetailsModified.height}")
                                Text("Weight: ${pokeMonDetailsModified.weight}")
                                Text("Type:   ${typeName}")
                                Text("Stats:  ${statsNumber}")
                            }
                        }
                    }
                }
                is PokeMonResult.Error ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Error Occured"
                        )
                    }

                PokeMonResult.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                null -> Text("No Data to Render in Detailed Screen")
            }
        }
    }
}

