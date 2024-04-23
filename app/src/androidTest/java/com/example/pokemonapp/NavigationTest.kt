package com.example.pokemonapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pokemonapp.presentation.composables.NavigateFromListToDetailScreen
import com.example.pokemonapp.ui.PokeMonListActivity
import com.example.pokemonapp.viewmodel.PokemonViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<PokeMonListActivity>()

    @Mock
    lateinit var mockViewModel: PokemonViewModel
    @Composable
    @Test
    fun testNavigationFromListToDetailScreen() {

        // Launch the app

            NavigateFromListToDetailScreen(pokemonViewModel = mockViewModel)


        // Click on an item in the list screen
        composeTestRule.onNodeWithText("Item 1").performClick()

        // Check if navigation occurred correctly
        composeTestRule.onNodeWithText("Detail Screen").assertIsDisplayed()
    }
}