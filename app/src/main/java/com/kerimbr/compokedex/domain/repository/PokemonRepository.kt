package com.kerimbr.compokedex.domain.repository

import com.kerimbr.compokedex.data.remote.responses.pokemon.PokemonResponse
import com.kerimbr.compokedex.data.remote.responses.pokemon_list.PokemonListResponse

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResponse
    suspend fun getPokemonInfo(pokemonNumber: Int): PokemonResponse

}