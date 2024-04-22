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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.pokemonapp.data.model.PokeMonResult
import com.example.pokemonapp.viewmodel.PokemonViewModel

/**
 * A composable function to display details of a Pokémon.
 *
 * @param pokemonViewModel The [PokemonViewModel] responsible for fetching Pokémon details.
 * @param pokemonName The ID of the Pokémon whose details should be displayed.
 * @param modifier The modifier for the composable, used to customize its layout and appearance.
 */
@Composable
fun PokeMonSearchDetailComposable(
    pokemonViewModel: PokemonViewModel,
    pokemonName: String
) {

    val pokemonDetails by pokemonViewModel.pokemonDetails.collectAsState()
    val searchText by pokemonViewModel.searchText.collectAsState()
    val isSwitchToggled by pokemonViewModel.isSwitchToggled.collectAsState()

    LaunchedEffect(pokemonName) {
        val nameToLoad = if (searchText.isBlank()) pokemonName else searchText
        pokemonViewModel.fetchPokemonDetails(nameToLoad)
    }

    androidx.compose.material3.Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { newValue -> pokemonViewModel.onSearchTextChange(newValue) },
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

                    // Create a new instance of PokemonDetailsResponse with modified id
                    val pokeMonDetailsModified =
                        pokemonDetailsResultData.copy(name = pokemonDetailsResultData.name)
                    val types = pokemonDetailsResultData.types
                    val stats = pokemonDetailsResultData.stats
                    val typeName = types.firstOrNull()?.type?.name ?: "Unknown Type"
                    val statsNumber = stats.firstOrNull()?.base_stat ?: 0

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

                is PokeMonResult.Error ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Error occured"
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

