package com.example.pokemonapp.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemonapp.data.model.Pokemon

/**
 * A composable that represents a clickable card displaying information about a Pokemon.
 *
 * @param pokemon The Pokemon object to display.
 * @param onItemClick Callback function to invoke when the card is clicked, providing the Pokemon ID.
 * @param textSize The size of the text displayed in the card. Default is 20sp.
 * @param horizontalPadding Horizontal padding applied to the card. Default is 16dp.
 * @param verticalPadding Vertical padding applied to the card. Default is 8dp.
 */
@Composable
fun ClickablePokemonCard(
    pokemon: Pokemon,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 20.sp,
    horizontalPadding: Dp = 16.dp,
    verticalPadding: Dp = 8.dp
) {
    val pokeMonId = pokemon.extractPokeMonId() ?: -1

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
            .clickable { onItemClick(pokeMonId) },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding)
        ) {
            Text(
                text = pokemon.name,
                style = TextStyle(fontSize = textSize),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
