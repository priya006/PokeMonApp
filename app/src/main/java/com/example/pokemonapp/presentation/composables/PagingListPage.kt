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
import androidx.paging.compose.items



/**
 * Composable that represents a screen displaying a paginated list of Pokémon.
 * It uses Jetpack Paging to load data in pages and supports infinite scrolling.
 *
 * @param pokemonViewModel The view model providing access to Pokémon data.
 * @param onItemClick Callback function triggered when a Pokémon card is clicked.
 */
@Composable
fun PagingListPage(
    pokemonViewModel: PokemonViewModel,
    onItemClick : (Int) -> Unit
) {
    val pager = remember {
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { pokemonViewModel.getPokemonPagingSource() }
        )
    }

    val pagingData = pager.flow.collectAsLazyPagingItems()
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(2f)) {
            items(pagingData) { pokemon ->

                if (pokemon != null) {
                    ClickablePokemonCard(pokemon = pokemon, onItemClick =  onItemClick)

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