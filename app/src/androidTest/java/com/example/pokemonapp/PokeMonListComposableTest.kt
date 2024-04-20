//package com.example.pokemonapp
//
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithContentDescription
//import androidx.compose.ui.test.onNodeWithText
//import com.example.pokemonapp.data.model.PokeMonResult
//import com.example.pokemonapp.data.model.Pokemon
//import com.example.pokemonapp.data.model.PokemonListResponse
//import com.example.pokemonapp.presentation.composables.listpage.PokeMonListComposable
//import com.example.pokemonapp.viewmodel.PokemonViewModel
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mockito.mock
//
//
//class PokeMonListComposableTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//    val mockViewModel = mock(PokemonViewModel::class.java)
//    @Test
//    fun pokeMonListComposable_RenderSuccess() {
//
//        // Launch the composable
//        composeTestRule.setContent {
//            PokeMonListComposable(
//                pokemonViewModel = mockViewModel,
//                onItemClick = {}
//            )
//        }
//
//        // Verify if the list items are rendered
//        composeTestRule.onNodeWithText("ivysaur").assertExists()
//        composeTestRule.onNodeWithText("venusaur").assertExists()
//    }
//
//    @Test
//    fun pokeMonListComposable_TestLoadingState() {
//        // Set the pokemonList state to Loading
//        val loadingState: PokeMonResult<PokemonListResponse> = PokeMonResult.Loading
//
//        // Launch the composable with the loading state
//        composeTestRule.setContent {
//            PokeMonListComposable(pokemonViewModel = mockViewModel, onItemClick = {})
//        }
//
//        // Verify that CircularProgressIndicator is present
//        composeTestRule.onNodeWithContentDescription("Loading...").assertExists()
//    }
//
//
//    @Test
//    fun pokeMonListComposable_RenderError() {
//        // Set up an error result
//        val errorResult: PokeMonResult<PokemonListResponse> = PokeMonResult.Error("Failed to fetch data")
//
//        // Launch the composable
//        composeTestRule.setContent {
//            PokeMonListComposable(
//                pokemonViewModel = mockViewModel,
//                onItemClick = {}
//            )
//        }
//
//        // Verify if the error message is rendered
//        composeTestRule
//            .onNodeWithText("Error occurred:", ignoreCase = true)
//            .assertExists()
//    }
//}