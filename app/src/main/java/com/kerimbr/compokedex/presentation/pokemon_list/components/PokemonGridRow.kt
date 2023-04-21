package com.kerimbr.compokedex.presentation.pokemon_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kerimbr.compokedex.domain.models.PokedexListEntry


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonGridRow(
    pokemonList: List<PokedexListEntry>,
    navController: NavController,
) {

    FlowRow{
        pokemonList.forEach { pokemonEntry ->
            PokemonGridItem(
                entry = pokemonEntry,
                navController = navController,
                modifier = Modifier
                    .fillMaxSize(0.5f)
                    .padding(8.dp),
            )
        }
    }

}