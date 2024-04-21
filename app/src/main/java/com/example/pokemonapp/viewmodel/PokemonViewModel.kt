package com.example.pokemonapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import com.example.pokemonapp.data.model.PokemonListResponse
import com.example.pokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.launch
import com.example.pokemonapp.data.model.PokeMonResult
import com.example.pokemonapp.data.model.Pokemon
import com.example.pokemonapp.data.model.PokemonDetailsResponse
import com.example.pokemonapp.data.model.PokemonPagingDataSource
import retrofit2.HttpException
import java.io.IOException


class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {

    private val _pokemonList = MutableLiveData<PokeMonResult<PokemonListResponse>>()
    val pokemonList: LiveData<PokeMonResult<PokemonListResponse>> = _pokemonList

    private val _pokemonDetails = MutableLiveData<PokeMonResult<PokemonDetailsResponse>>()
    val pokemonDetails: LiveData<PokeMonResult<PokemonDetailsResponse>> = _pokemonDetails


    init {
        // Fetch the Pokemon list when the ViewModel is initialized
        fetchPokeMonListView()
    }

    /**
     * Fetches the Pokemon list from the repository and updates the [_pokemonList] LiveData
     * with the result.
     */
    fun fetchPokeMonListView() {
        viewModelScope.launch {
            _pokemonList.value = PokeMonResult.Loading
            try {
                val pokemonListResponse = repository.getPokemonList()
                _pokemonList.value = PokeMonResult.Success((pokemonListResponse))
            } catch (e: Exception) {
                handleApiError(_pokemonList, e)
            }
        }
    }


    /**
     * Fetches the details of a Pokemon with the specified [pokeMonId] from the repository
     * and updates the [_pokemonDetails] LiveData with the result.
     *
     * @param pokeMonId The ID of the Pokemon whose details are to be fetched.
     */
    fun fetchPokemonDetails(pokeMonId: Int) {
        viewModelScope.launch {
            _pokemonDetails.value = PokeMonResult.Loading
            try {
                val pokemonDetailsResponse = repository.getPokemonDetails(pokeMonId)
                _pokemonDetails.value = PokeMonResult.Success(pokemonDetailsResponse)
            } catch (e: Exception) {
                handleApiError(_pokemonDetails, e)
            }
        }
    }

    /**
     * Handles API errors by generating an appropriate error message based on the type of exception [e],
     * and updates the [resultLiveData] with the corresponding [PokeMonResult.Error].
     *
     * @param resultLiveData The LiveData to be updated with the error result.
     * @param e The exception representing the API error.
     */
    private fun <T> handleApiError(
        resultLiveData: MutableLiveData<PokeMonResult<T>>,
        e: Exception
    ) {
        val errorMessage = when (e) {
            is IOException -> "Network error occurred"
            is HttpException -> "HTTP error occurred: ${e.code()}"
            else -> "An error occurred"
        }
        resultLiveData.value = PokeMonResult.Error(errorMessage, e)
    }

    /**
     * Provides a PagingSource for fetching a paginated list of Pokémon details from the API.
     *
     * @return A [PokemonPagingDataSource] for paginated Pokémon details.
     */
    fun getPokemonPagingSource(): PagingSource<Int, Pokemon> {
        return PokemonPagingDataSource(repository)
    }
}
