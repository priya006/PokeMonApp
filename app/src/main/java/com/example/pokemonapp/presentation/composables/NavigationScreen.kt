package com.example.pokemonapp.presentation.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokemonapp.presentation.composables.detailpage.PokeMonSearchDetailComposable
import com.example.pokemonapp.viewmodel.PokemonViewModel

/**
 * Composable function to navigate from the list screen to the detail screen using Jetpack Navigation.
 *
 * @param pokemonViewModel ViewModel containing the data and logic for the Pokemon-related features.
 */
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
            route = "detail/{pokemonName}",
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
        ) { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString("pokemonName")
            if (pokemonName != null) {
                DetailScreen(pokemonViewModel, pokemonName)
            } else {
                DisplayError(navController)
            }
        }
    }
}

/**
 * Composable function representing the list screen of the Pokemon app.
 *
 * @param navController NavController used for navigation between composables.
 * @param pokemonViewModel ViewModel containing the data and logic for the Pokemon-related features.
 */
@Composable
fun ListScreen(
    navController: NavController,
    pokemonViewModel: PokemonViewModel
) {
    //pagination is handled
    PagingListPage(pokemonViewModel = pokemonViewModel, onItemClick = { pokemonName ->
        navController.navigate("detail/$pokemonName") {
            popUpTo("detail_screen_route") { inclusive = true }
            launchSingleTop = true
        }
        pokemonViewModel.onSearchTextChange("")
    })
}

/**
 * Composable function representing the detail screen of the Pokemon app.
 *
 * @param pokemonViewModel ViewModel containing the data and logic for the Pokemon-related features.
 * @param pokeMonId ID of the Pokemon to display details for.
 */
@Composable
fun DetailScreen(
    pokemonViewModel: PokemonViewModel,
    pokemonName: String
) {
    
    PokeMonSearchDetailComposable(
        pokemonViewModel = pokemonViewModel,
        pokemonName = pokemonName
    )
}

/**
 * Composable function for displaying an error and navigating back to the previous screen.
 *
 * @param navController NavController used for navigating back to the previous screen.
 */
@Composable
fun DisplayError(navController: NavController) {
    navController.popBackStack()
}
