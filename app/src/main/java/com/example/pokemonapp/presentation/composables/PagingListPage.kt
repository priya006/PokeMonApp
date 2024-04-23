package com.example.pokemonapp.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokemonapp.viewmodel.PokemonViewModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.paging.compose.items
import com.example.pokemonapp.R


/**
 * Composable that represents a screen displaying a paginated list of Pokémon.
 * It uses Jetpack Paging to load data in pages and supports infinite scrolling.
 *
 * @param pokemonViewModel The view model providing access to Pokémon data.
 * @param onItemClick Callback function triggered when a Pokémon card is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagingListPage(
    pokemonViewModel: PokemonViewModel,
    onItemClick : (String) -> Unit
) {
    val pager = remember {
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { pokemonViewModel.getPokemonPagingSource() }
        )
    }

    val pagingData = pager.flow.collectAsLazyPagingItems()
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = "Pokemon List",
                    style = TextStyle(
                        fontSize = 20.sp, // Adjust the font size as needed
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp) // Add padding if needed
                )
            }
        )
        LazyColumn(modifier = Modifier.weight(2f)) {
            items(pagingData) { pokemon ->

                if (pokemon != null) {
                    ClickablePokemonCard(pokemon = pokemon, contentDescription = stringResource(R.string.clickable_pokemon_card_description)
                        , onItemClick =  onItemClick)
                }
            }
            if (pagingData.loadState.append.endOfPaginationReached.not()) {
                item {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}