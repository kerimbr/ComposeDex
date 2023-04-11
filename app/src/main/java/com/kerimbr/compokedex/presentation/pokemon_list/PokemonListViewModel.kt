package com.kerimbr.compokedex.presentation.pokemon_list

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.kerimbr.compokedex.core.constants.AppConstants
import com.kerimbr.compokedex.core.utils.Resource
import com.kerimbr.compokedex.core.utils.generatePokemonArtworkUrl
import com.kerimbr.compokedex.data.remote.responses.pokemon_list.PokemonListResponse
import com.kerimbr.compokedex.domain.models.PokedexListEntry
import com.kerimbr.compokedex.domain.repository.PokemonRepository
import com.kerimbr.compokedex.domain.use_case.pokemon.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private var _currentPage = 0
    private var _isEndOfList = false

    private val _state: MutableState<PokemonListState> = mutableStateOf(PokemonListState())
    val state: State<PokemonListState> = _state

    init {
        loadPokemonWithPagination()
    }

    private fun loadPokemonWithPagination() {
        getPokemonListUseCase(
            limit = AppConstants.LIMIT,
            offset = _currentPage * AppConstants.LIMIT
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {

                    _isEndOfList = _currentPage * AppConstants.LIMIT >= result.data!!.size

                    val pokemonListEntry = result.data.mapIndexed { index, entry ->
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

                    _state.value = _state.value.copy(
                        pokemonList = _state.value.pokemonList + pokemonListEntry,
                        isLoading = false,
                        error = ""
                    )
                    _currentPage++
                }
                is Resource.Error -> {
                    _state.value = PokemonListState(
                        pokemonList = emptyList(),
                        isLoading = false,
                        error = result.message ?: "An unknown error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PokemonListState(
                        pokemonList = emptyList(),
                        isLoading = true,
                        error = ""
                    )
                }
            }


        }.launchIn(viewModelScope)
    }

    fun calculateDominantColor(drawable: Drawable, onFinished: (Color) -> Unit) {
        val bitmap = (drawable as BitmapDrawable).bitmap
            .copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bitmap).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { color ->
                onFinished(Color(color))
            }
        }
    }




}