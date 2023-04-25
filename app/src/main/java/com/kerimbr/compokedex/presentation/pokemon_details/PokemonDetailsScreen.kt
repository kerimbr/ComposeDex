package com.kerimbr.compokedex.presentation.pokemon_details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kerimbr.compokedex.domain.models.PokemonDetails
import com.kerimbr.compokedex.presentation.pokemon_details.components.PokemonDetailContent
import com.kerimbr.compokedex.presentation.pokemon_details.components.PokemonDetailShimmer
import timber.log.Timber

@Composable
fun PokemonDetailScreen(
    navController: NavController,
    number: Int,
    viewModel: PokemonDetailsViewModel = hiltViewModel()
) {


    LaunchedEffect(key1 = viewModel.pokemonId) {
        if (viewModel.pokemonId == 0) viewModel.pokemonId = number
        Timber.i("PokemonDetailScreen: $number - Getting pokemon details...")
        viewModel.getPokemonDetails(viewModel.pokemonId)
    }

    Scaffold {
        when (viewModel.pokemonDetailState.screenState) {
            PokemonDetailScreenState.Loading -> {
                LoadingDetails()
            }

            PokemonDetailScreenState.Success -> {
                val pokemonDetails: PokemonDetails = viewModel.pokemonDetailState.pokemonDetails!!
                PokemonDetailContent(
                    pokemonDetails = pokemonDetails,
                    number = viewModel.pokemonId,
                    navController = navController
                )
            }

            PokemonDetailScreenState.Error -> {
                ErrorDetails(errorMessage = viewModel.pokemonDetailState.errorMessage!!)
            }
        }
    }
}


@Composable
private fun LoadingDetails() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        PokemonDetailShimmer()
    }
}

@Composable
private fun ErrorDetails(errorMessage: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = errorMessage)
    }
}
