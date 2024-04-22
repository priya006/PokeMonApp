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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {

    private val _pokemonList = MutableLiveData<PokeMonResult<PokemonListResponse>>()
    val pokemonList: LiveData<PokeMonResult<PokemonListResponse>> = _pokemonList

    private val _pokemonDetailsBeforeSearch =
        MutableLiveData<PokeMonResult<PokemonDetailsResponse>>()
    val pokemonDetailsBeforeSearch: LiveData<PokeMonResult<PokemonDetailsResponse>> =
        _pokemonDetailsBeforeSearch

    private val _pokemonDetails = MutableStateFlow<PokeMonResult<PokemonDetailsResponse>?>(null)
    val pokemonDetails: StateFlow<PokeMonResult<PokemonDetailsResponse>?> =
        _pokemonDetails.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSwitchToggled = MutableStateFlow(false)
    val isSwitchToggled = _isSwitchToggled.asStateFlow()


    init {
        // Fetch the Pokemon list when the ViewModel is initialized
        fetchPokeMonListView()

        viewModelScope.launch {
            searchText
                .debounce(1000L)
                .collect { text ->
                    if (text.isNotBlank() && _isSwitchToggled.value) {
                        fetchPokemonDetails(text)
                    }
                }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onSwitchToggledChange(newState: Boolean) {
        _isSwitchToggled.value = newState
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
     * Fetches the details of a Pokemon with the specified [pokemonName] from the repository
     * and updates the [_pokemonDetails] LiveData with the result.
     *
     * @param pokemonName The ID of the Pokemon whose details are to be fetched.
     */
    fun fetchPokemonDetails(pokemonName: String) {
        viewModelScope.launch {
            _pokemonDetails.value = PokeMonResult.Loading
            try {
                val pokemonDetailsResponse = repository.getPokemonDetails(pokemonName)
                _pokemonDetails.value = PokeMonResult.Success(pokemonDetailsResponse)
            } catch (e: Exception) {
                handleApiErrorForMutableState(_pokemonDetails, e)
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

    private fun handleApiErrorForMutableState(
        resultStateFlow: MutableStateFlow<PokeMonResult<PokemonDetailsResponse>?>,
        e: Exception
    ) {
        val errorMessage = when (e) {
            is IOException -> "Network error occurred"
            is HttpException -> "HTTP error occurred: ${e.code()}"
            else -> "An error occurred"
        }
        resultStateFlow.value = PokeMonResult.Error(errorMessage, e)
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
