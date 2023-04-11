package com.kerimbr.compokedex.presentation.pokemon_list.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun PokemonListTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = "ComposeDex",
        style = MaterialTheme.typography.displaySmall.copy(
            fontWeight = FontWeight.W900,
            color = MaterialTheme.colorScheme.tertiary
        )
    )
}