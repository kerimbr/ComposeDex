package com.kerimbr.compokedex.presentation.pokemon_list.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun PokemonListSubTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = "Search for a Pokémon by name or using the National Pokédex number.",
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f)
        )
    )
}
