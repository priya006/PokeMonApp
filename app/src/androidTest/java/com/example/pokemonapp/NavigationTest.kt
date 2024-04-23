package com.example.pokemonapp

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pokemonapp.presentation.composables.NavigateFromListToDetailScreen
import com.example.pokemonapp.ui.PokeMonListActivity
import com.example.pokemonapp.viewmodel.PokemonViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Mock
    lateinit var mockViewModel: PokemonViewModel
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            // Launch the app
            NavigateFromListToDetailScreen(pokemonViewModel = mockViewModel)
        }
    }


    @Test
    fun testNavigationFromListToDetailScreen() {

        // Click on an item in the list screen
        composeTestRule.onNodeWithText("pidgey").performClick()
        // Check if navigation occurred correctly
        composeTestRule.onNodeWithText("stats").assertIsDisplayed()
    }
}