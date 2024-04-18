package com.example.pokemonapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokemonapp.data.model.PokeMonResult
import com.example.pokemonapp.domain.repository.PokemonRepository
import com.example.pokemonapp.network.RetrofitInstance
import com.example.pokemonapp.presentation.composables.detailpage.PokeMonDetailComposable

import com.example.pokemonapp.presentation.composables.listpage.PokeMonListComposable
import com.example.pokemonapp.viewmodel.PokemonViewModel


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pokeApiService = RetrofitInstance.create()

        setContent {
            // Create an instance of your PokemonRepository
            val repository = PokemonRepository(pokeApiService)
            // Create an instance of your PokemonViewModel
            val viewModel = PokemonViewModel(repository)
            //viewModel.fetchPokemonList()
            val pokemonList by viewModel.pokemonList.observeAsState(initial = PokeMonResult.Loading)
            val pokeDetails by viewModel.pokemonDetails.observeAsState()
            val navController = rememberNavController()

            NavHost(navController, startDestination = "list") {
                composable("list") {
                    PokeMonListComposable(pokemonList = pokemonList) { pokemonId ->
                        //val pokemonId = pokemon.results.firstOrNull()?.extractPokemonId()
                        navController.navigate("detail/${pokemonId}")
                    }
                }

                composable(
                    route = "detail/{pokemonId}",
                    arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val pokemonId = backStackEntry.arguments?.getInt("pokemonId")
                    if (pokemonId != null) {
                        // Call the PokeMonDetailComposable and pass the pokemonId
                        PokeMonDetailComposable(pokemonDetails = pokeDetails)
                    } else {
                        // Handle the case where pokemonId is null
                    }
                }

//                composable(
//                    route = "detail/{pokemonId}",
//                    arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
//                ) { backStackEntry ->
//                    val pokemonId = backStackEntry.arguments?.getInt("pokemonId")
//                    if (pokemonId != null) {
//                        // Use LaunchedEffect to launch the coroutine for fetching Pokemon details
//                        LaunchedEffect(pokemonId) {
//                            try {
//                                // Fetch the Pokemon details using the ID
//                                val pokemonDetails = viewModel.fetchPokemonDetails(pokemonId)
//                                // Navigate to the detail composable with the fetched details
//                                pokemonDetails?.let {
//                                    navController.navigate("detail/$pokemonId")
//                                }
//                            } catch (e: Exception) {
//                                // Handle error
//                            }
//                        }
//                    }
//                }


            }
        }
    }
}