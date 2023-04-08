package com.kerimbr.compokedex.domain.repository

import com.kerimbr.compokedex.core.utils.Resource
import com.kerimbr.compokedex.data.remote.PokeApi
import com.kerimbr.compokedex.data.remote.responses.pokemon.PokemonResponse
import com.kerimbr.compokedex.data.remote.responses.pokemon_list.PokemonListResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

// This annotation is used to tell Hilt that this class is scoped to the activity.
@ActivityScoped
class PokemonRepository @Inject constructor(
    private val pokeApi: PokeApi
) {
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonListResponse> {
        return try {
            Resource.Success(pokeApi.getPokemonList(limit, offset))
        } catch (e: Exception) {
            Resource.Error("An unknown error occurred.")
        }
    }

    suspend fun getPokemonInfo(name: String): Resource<PokemonResponse> {
        return try {
            Resource.Success(pokeApi.getPokemonInfo(name))
        } catch (e: Exception) {
            Resource.Error("An unknown error occurred.")
        }
    }


}
