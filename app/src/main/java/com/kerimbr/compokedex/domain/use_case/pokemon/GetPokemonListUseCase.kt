package com.kerimbr.compokedex.domain.use_case.pokemon

import com.kerimbr.compokedex.core.utils.Resource
import com.kerimbr.compokedex.domain.mappers.toPokemonList
import com.kerimbr.compokedex.domain.models.Pokemon
import com.kerimbr.compokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
){
    operator fun invoke(limit: Int, offset: Int): Flow<Resource<List<Pokemon>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val pokemonListResponse = pokemonRepository.getPokemonList(
                    limit = limit,
                    offset = offset
                )
                val pokemonList: List<Pokemon> = pokemonListResponse.toPokemonList()
                emit(Resource.Success(pokemonList))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An unknown error occured"))
            }
        }
    }
}