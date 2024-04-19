package com.example.pokemonapp.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokemonapp.presentation.composables.detailpage.PokeMonDetailComposable
import com.example.pokemonapp.presentation.composables.listpage.PokeMonListComposable
import com.example.pokemonapp.viewmodel.PokemonViewModel

@Composable
fun NavigateFromListToDetailScreen(
    pokemonViewModel: PokemonViewModel,
    backgroundColor: Color = Color.Blue
) {
    val navController = rememberNavController()
    val backgroundColorModifier = Modifier.background(backgroundColor)
    NavHost(navController, startDestination = "list") {
        composable("list") {
            ListScreen(navController,backgroundColorModifier = backgroundColorModifier, pokemonViewModel)
        }
        composable(
            route = "detail/{pokemonId}",
            arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
        ) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getInt("pokemonId")
            if (pokemonId != null) {
                DetailScreen(pokemonViewModel,pokemonId)
            } else {
                // Handle the case where pokemonId is null
            }
        }
    }
}

@Composable
fun ListScreen(
    navController: NavController,
    backgroundColorModifier: Modifier,
    pokemonViewModel: PokemonViewModel
) {
    PokeMonListComposable(
        pokemonViewModel = pokemonViewModel,
        onItemClick = { pokemonId ->
            navController.navigate("detail/$pokemonId")
        },
        modifier = backgroundColorModifier
    )
}
@Composable
fun DetailScreen(
    pokemonViewModel: PokemonViewModel,
    pokemonId : Int
) {
    // Invoke the PokeMonDetailComposable and pass the pokemonId
    PokeMonDetailComposable(
        pokemonViewModel = pokemonViewModel,
        pokeMonId = pokemonId
    )
}
