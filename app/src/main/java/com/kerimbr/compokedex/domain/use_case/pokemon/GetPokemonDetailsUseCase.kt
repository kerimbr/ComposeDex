package com.kerimbr.compokedex.domain.use_case.pokemon

import com.kerimbr.compokedex.core.utils.Resource
import com.kerimbr.compokedex.domain.mappers.toPokemonDetails
import com.kerimbr.compokedex.domain.models.PokemonDetails
import com.kerimbr.compokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
){
    suspend operator fun invoke(pokemonName: String): Flow<Resource<PokemonDetails>> {
        return flow {
            emit(Resource.Loading())
            try {
                val pokemonDetailsResponse = pokemonRepository.getPokemonInfo(pokemonName)
                val pokemon: PokemonDetails = pokemonDetailsResponse.toPokemonDetails()
                emit(Resource.Success(pokemon))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An unknown error occured"))
            }
        }
    }
}