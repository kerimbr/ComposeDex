package com.kerimbr.compokedex.domain.mappers

import com.kerimbr.compokedex.data.remote.responses.pokemon.PokemonResponse
import com.kerimbr.compokedex.data.remote.responses.pokemon_list.PokemonListResponse
import com.kerimbr.compokedex.domain.models.Pokemon
import com.kerimbr.compokedex.domain.models.PokemonDetails


fun PokemonListResponse.toPokemonList(): List<Pokemon> = results?.map {
    Pokemon(
        name = it?.name ?: "",
        url = it?.url ?: ""
    )
} ?: emptyList()


fun PokemonResponse.toPokemonDetails(): PokemonDetails = PokemonDetails(
    id = id,
    name = name,
    height = height ?: 0,
    weight = weight ?: 0,
    baseExperience = baseExperience ?: 0,
    forms = forms ?: emptyList(),
    abilities = abilities ?: emptyList(),
    types = types ?: emptyList(),
    stats = stats ?: emptyList(),
    moves = moves ?: emptyList(),
    heldItems = heldItems ?: emptyList(),
    gameIndices = gameIndices ?: emptyList()
)