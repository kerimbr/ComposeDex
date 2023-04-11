package com.kerimbr.compokedex.presentation.pokemon_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kerimbr.compokedex.domain.models.PokedexListEntry
import com.kerimbr.compokedex.presentation.pokemon_list.PokemonListViewModel


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonGridList(
    pokemonList: List<PokedexListEntry>,
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {

    FlowRow{
        pokemonList.forEach { pokemonEntry ->
            PokemonGridItem(
                entry = pokemonEntry,
                navController = navController,
                modifier = Modifier
                    .fillMaxSize(0.5f)
                    .padding(8.dp),
                onGenerateDominantColor = viewModel::calculateDominantColor
            )
        }
    }

}