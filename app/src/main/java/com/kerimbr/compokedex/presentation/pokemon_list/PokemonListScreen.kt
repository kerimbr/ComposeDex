package com.kerimbr.compokedex.presentation.pokemon_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kerimbr.compokedex.presentation.pokemon_list.components.PokemonGridList
import com.kerimbr.compokedex.presentation.pokemon_list.components.PokemonListSearchBar
import com.kerimbr.compokedex.presentation.pokemon_list.components.PokemonListSubTitle
import com.kerimbr.compokedex.presentation.pokemon_list.components.PokemonListTitle


@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {

    val lazyListState = rememberLazyListState()


    Scaffold(
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                state = lazyListState,
            ) {

                item {
                    PokemonListTitle(
                        modifier = Modifier
                            .padding(
                                top = it.calculateTopPadding().plus(16.dp),
                                start = 16.dp,
                                end = 16.dp
                            )
                    )
                }

                item {
                    PokemonListSubTitle(
                        modifier = Modifier
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                    )
                }


                item {
                    PokemonListSearchBar(
                        modifier = Modifier
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                pokemonListBody(
                    navController = navController,
                    state = viewModel.state.value,
                )

            }
        }
    )
}

    fun LazyListScope.pokemonListBody(
        navController: NavController,
        state: PokemonListState,
    ) {

        when {
            state.isLoading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
            state.error.isNotBlank() -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = state.error)
                    }
                }
            }
            else -> {

                if (state.pokemonList.size % 2 == 0){
                    items(
                        count = state.pokemonList.size / 2,
                        key = { index -> state.pokemonList[index * 2].imageUrl }
                    ) { index ->
                        PokemonGridList(
                            pokemonList = listOf(state.pokemonList[index * 2], state.pokemonList[index * 2 + 1]),
                            navController = navController,
                        )
                    }
                }else{
                    items(
                        count = state.pokemonList.size / 2,
                        key = { index -> state.pokemonList[index * 2].imageUrl }
                    ) { index ->
                        PokemonGridList(
                            pokemonList = listOf(state.pokemonList[index * 2], state.pokemonList[index * 2 + 1]),
                            navController = navController,
                        )
                    }
                    item {
                        PokemonGridList(
                            pokemonList = listOf(state.pokemonList.last()),
                            navController = navController,
                        )
                    }
                }
            }
        }

    }
