package com.kerimbr.compokedex.presentation.pokemon_list

import com.kerimbr.compokedex.domain.models.PokedexListEntry

data class PokemonListState(
    val pokemonList: List<PokedexListEntry> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
)
