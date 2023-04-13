package com.kerimbr.compokedex.domain.use_case.pokemon

import com.kerimbr.compokedex.core.utils.Resource
import com.kerimbr.compokedex.core.utils.generatePokemonArtworkUrl
import com.kerimbr.compokedex.domain.mappers.toPokemonList
import com.kerimbr.compokedex.domain.models.PokedexListEntry
import com.kerimbr.compokedex.domain.models.Pokemon
import com.kerimbr.compokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
){
    operator fun invoke(limit: Int, offset: Int): Flow<Resource<List<PokedexListEntry>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val pokemonListResponse = pokemonRepository.getPokemonList(
                    limit = limit,
                    offset = offset
                )
                val pokemonList: List<Pokemon> = pokemonListResponse.toPokemonList()

                val pokemonListEntry = pokemonList.mapIndexed { _, entry ->
                    val number = if(entry.url.endsWith("/")) {
                        entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                    } else {
                        entry.url.takeLastWhile { it.isDigit() }
                    }

                    val imageUrl = generatePokemonArtworkUrl(number.toInt())

                    PokedexListEntry(
                        pokemonName = entry.name,
                        imageUrl = imageUrl,
                        number = number.toInt()
                    )
                }

                emit(Resource.Success(pokemonListEntry))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An unknown error occured"))
            }
        }
    }
}