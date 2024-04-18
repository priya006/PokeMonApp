package com.example.pokemonapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.model.PokemonListResponse
import com.example.pokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.launch
import com.example.pokemonapp.data.model.PokeMonResult
import retrofit2.HttpException
import java.io.IOException

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {

    private val _pokemonList = MutableLiveData<PokeMonResult<PokemonListResponse>>()
    val pokemonList: LiveData<PokeMonResult<PokemonListResponse>> = _pokemonList

    init {
        // Fetch the Pokemon list when the ViewModel is initialized
        fetchPokeMonListView()
    }

    fun fetchPokeMonListView() {
        viewModelScope.launch {
            _pokemonList.value = PokeMonResult.Loading
            try {
                val pokemonListResponse = repository.getPokemonList()
                _pokemonList.value = PokeMonResult.Success((pokemonListResponse))
            }
            catch (e: IOException) {
                // Handle network-related errors, such as no internet connection
                _pokemonList.value = PokeMonResult.Error("Network error occurred", e)
            } catch (e: HttpException) {
                // Handle HTTP-related errors, such as server errors
                _pokemonList.value = PokeMonResult.Error("HTTP error occurred: ${e.code()}", e)
            }catch (e: Exception) {
                _pokemonList.value = PokeMonResult.Error("An error occurred", e)
            }
        }
    }
}