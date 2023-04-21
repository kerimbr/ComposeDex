package com.kerimbr.compokedex.data.repository

import com.kerimbr.compokedex.data.remote.PokeApi
import com.kerimbr.compokedex.data.remote.responses.pokemon.PokemonResponse
import com.kerimbr.compokedex.data.remote.responses.pokemon_list.PokemonListResponse
import com.kerimbr.compokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApi: PokeApi
): PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResponse {
        return pokeApi.getPokemonList(limit, offset)
    }

    override suspend fun getPokemonInfo(pokemonNumber: Int): PokemonResponse {
        return pokeApi.getPokemonInfo(pokemonNumber)
    }
}