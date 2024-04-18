package com.example.pokemonapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.pokemonapp.data.model.PokeMonResult
import com.example.pokemonapp.data.model.Pokemon
import com.example.pokemonapp.data.model.PokemonListResponse
import com.example.pokemonapp.presentation.composables.listpage.PokeMonListComposable
import org.junit.Rule
import org.junit.Test

class PokeMonListComposableTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pokeMonListComposable_RenderSuccess() {
        // Set up a successful result
        val pokemonList = PokeMonResult.Success(
            PokemonListResponse(
                count = 2,
                next = null,
                previous = null,
                results = listOf(
                    Pokemon("ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
                    Pokemon("venusaur", "https://pokeapi.co/api/v2/pokemon/3/")
                )
            )
        )

        // Launch the composable
        composeTestRule.setContent {
            PokeMonListComposable(
                pokemonList = pokemonList,
                onItemClick = {}
            )
        }

        // Verify if the list items are rendered
        composeTestRule.onNodeWithText("ivysaur").assertExists()
        composeTestRule.onNodeWithText("venusaur").assertExists()
    }

    @Test
    fun pokeMonListComposable_TestLoadingState() {
        // Set the pokemonList state to Loading
        val loadingState: PokeMonResult<PokemonListResponse> = PokeMonResult.Loading

        // Launch the composable with the loading state
        composeTestRule.setContent {
            PokeMonListComposable(pokemonList = loadingState, onItemClick = {})
        }

        // Verify that CircularProgressIndicator is present
        composeTestRule.onNodeWithContentDescription("Loading...").assertExists()
    }


    @Test
    fun pokeMonListComposable_RenderError() {
        // Set up an error result
        val errorResult: PokeMonResult<PokemonListResponse> = PokeMonResult.Error("Failed to fetch data")

        // Launch the composable
        composeTestRule.setContent {
            PokeMonListComposable(
                pokemonList = errorResult,
                onItemClick = {}
            )
        }

        // Verify if the error message is rendered
        composeTestRule
            .onNodeWithText("Error occurred:", ignoreCase = true)
            .assertExists()
    }
}