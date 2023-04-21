package com.kerimbr.compokedex.presentation.pokemon_details

import com.kerimbr.compokedex.domain.models.PokemonDetails

data class PokemonDetailState(
    val pokemonDetails: PokemonDetails? = null,
    val errorMessage: String? = null,
    val screenState: PokemonDetailScreenState = PokemonDetailScreenState.Loading,
)


enum class PokemonDetailScreenState {
    Loading,
    Error,
    Success
}