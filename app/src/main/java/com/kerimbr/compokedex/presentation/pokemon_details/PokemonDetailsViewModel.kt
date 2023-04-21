package com.kerimbr.compokedex.presentation.pokemon_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerimbr.compokedex.core.utils.Resource
import com.kerimbr.compokedex.domain.use_case.pokemon.GetPokemonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
): ViewModel(){


    var pokemonDetailState: PokemonDetailState by mutableStateOf(PokemonDetailState())

    var pokemonId: Int by mutableStateOf(0)

    suspend fun getPokemonDetails(pokemonNumber: Int) {
        viewModelScope.launch {
            getPokemonDetailsUseCase(pokemonNumber).collect {
                when(it) {
                    is Resource.Loading -> {
                        pokemonDetailState = PokemonDetailState(
                            pokemonDetails = null,
                            screenState = PokemonDetailScreenState.Loading
                        )
                    }
                    is Resource.Success -> {
                        pokemonId = pokemonNumber
                        pokemonDetailState = PokemonDetailState(
                            pokemonDetails = it.data,
                            screenState = PokemonDetailScreenState.Success
                        )
                    }
                    is Resource.Error -> {
                        pokemonDetailState = PokemonDetailState(
                            pokemonDetails = null,
                            screenState = PokemonDetailScreenState.Error,
                            errorMessage = it.message
                        )
                    }
                }
            }
        }
    }


}