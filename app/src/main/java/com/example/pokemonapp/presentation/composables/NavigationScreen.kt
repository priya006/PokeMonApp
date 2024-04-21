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
            route = "detail/{pokeMonId}",
            arguments = listOf(navArgument("pokeMonId") { type = NavType.IntType })
        ) { backStackEntry ->
            val pokeMonId = backStackEntry.arguments?.getInt("pokeMonId")
            if (pokeMonId != null) {
                DetailScreen(pokemonViewModel,pokeMonId)
            } else {
                // navigating back to the previous screen
                navController.popBackStack()
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
    PagingListPage(pokemonViewModel = pokemonViewModel , onItemClick = { pokeMonId ->
        navController.navigate("detail/$pokeMonId")
    })


}
@Composable
fun DetailScreen(
    pokemonViewModel: PokemonViewModel,
    pokeMonId : Int
) {
    // Invoke the PokeMonDetailComposable and pass the pokeMonId
    PokeMonDetailComposable(
        pokemonViewModel = pokemonViewModel,
        pokeMonId = pokeMonId
    )
}
