package com.example.pokemonapp.data.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemonapp.domain.repository.PokemonRepository

/**
 * PagingSource implementation for loading paginated data of Pokémon.
 * This class is responsible for providing data to the Paging library
 * in a paginated manner.
 *
 * @property repository The repository used to fetch Pokémon data.
 */
class PokemonPagingDataSource(private val repository: PokemonRepository) : PagingSource<Int, Pokemon>() {

    /**
     * Provides the refresh key for the Paging library.
     *
     * @param state The current PagingState.
     * @return The refresh key, which is always null in this implementation.
     */
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return null
    }

    /**
     * Loads data for a given page.
     *
     * @param params Parameters for loading data, including the page key and size.
     * @return A LoadResult object containing the loaded data and optionally keys for
     * the previous and next pages.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val pageNumber = params.key ?: 0
            val data = repository.getPokemonListPagination(pageNumber)
            LoadResult.Page(
                data = data.results,
                prevKey = if (pageNumber > 0) pageNumber - 1 else null,
                nextKey = if (data.results.isNotEmpty()) pageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}