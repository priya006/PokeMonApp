package com.example.pokemonapp.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokemonapp.presentation.composables.detailpage.PokeMonDetailComposable
import com.example.pokemonapp.presentation.composables.listpage.PokeMonListComposable
import com.example.pokemonapp.viewmodel.PokemonViewModel

@Composable
fun SetupNavigation(pokemonViewModel: PokemonViewModel) {
    val navController = rememberNavController()
    val backgroundColorModifier = Modifier.background(Color.Blue)
    NavHost(navController, startDestination = "list") {
        composable("list") {
            PokeMonListComposable(
                pokemonViewModel = pokemonViewModel,
                onItemClick = { pokemonId ->
                    navController.navigate("detail/$pokemonId")
                },
                modifier = backgroundColorModifier
            )
        }
        composable(
            route = "detail/{pokemonId}",
            arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
        ) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getInt("pokemonId")
            if (pokemonId != null) {
                // Invoke the PokeMonDetailComposable and pass the pokemonId
                PokeMonDetailComposable(
                    pokemonViewModel = pokemonViewModel,
                    pokeMonId = pokemonId
                )
            } else {
                // Handle the case where pokemonId is null
            }
        }
    }
}
