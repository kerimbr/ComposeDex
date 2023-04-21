package com.kerimbr.compokedex.presentation.pokemon_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kerimbr.compokedex.core.enums.PokedexListState
import com.kerimbr.compokedex.domain.models.PokedexListEntry
import com.kerimbr.compokedex.presentation.pokemon_list.components.PokemonGridRow
import com.kerimbr.compokedex.presentation.pokemon_list.components.PokemonListSearchBar
import com.kerimbr.compokedex.presentation.pokemon_list.components.PokemonListSubTitle
import com.kerimbr.compokedex.presentation.pokemon_list.components.PokemonListTitle


@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {

    val lazyListState = rememberLazyListState()


    val shouldStartPaginate: Boolean by remember {
        derivedStateOf {
            viewModel.canPaginate && lazyListState.isScrolledToEnd()
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate) {
        if (shouldStartPaginate && viewModel.pokedexListState == PokedexListState.IDLE) {
            print("PAGINATE")
            viewModel.loadPokemonWithPagination()
        }
    }

    Scaffold {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            state = lazyListState,
        ) {

            item {
                PokemonListTitle(
                    modifier = Modifier
                        .padding(
                            top = 32.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                )

                PokemonListSubTitle(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                )

                PokemonListSearchBar(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            val gridItems: List<List<PokedexListEntry>> = viewModel.pokedexList.chunked(2)

            items(
                items = gridItems,
                key = { i -> i.hashCode() }
            ){
                PokemonGridRow(
                    pokemonList = it,
                    navController = navController,
                )
            }

            item(
                key = viewModel.pokedexListState
            ){
                when (viewModel.pokedexListState) {
                    PokedexListState.FIRST_LOADING -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally),
                            strokeCap  = StrokeCap.Round
                        )
                    }
                    PokedexListState.PAGINATING -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally),
                            strokeCap  = StrokeCap.Round
                        )
                    }
                    PokedexListState.END_OF_LIST -> {
                        Text(
                            text = "End of list",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                    else -> {
                        // for reCalculate shouldStartPaginate
                        Text(text = shouldStartPaginate.toString())
                    }
                }
            }

        }
    }


}

fun LazyListState.isScrolledToEnd(): Boolean {
    return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
}

