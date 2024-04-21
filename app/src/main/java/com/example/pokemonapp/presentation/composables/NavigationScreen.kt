package com.example.pokemonapp.presentation.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokemonapp.presentation.composables.detailpage.PokeMonDetailComposable
import com.example.pokemonapp.viewmodel.PokemonViewModel

@Composable
fun NavigateFromListToDetailScreen(
    pokemonViewModel: PokemonViewModel,
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "list") {
        composable("list") {
            ListScreen(navController = navController, pokemonViewModel)
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
    pokemonViewModel: PokemonViewModel
) {
    //pagination is handled
    PagingListPage(pokemonViewModel = pokemonViewModel , onItemClick = { pokemonId ->
        navController.navigate("detail/$pokemonId")
    })


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
