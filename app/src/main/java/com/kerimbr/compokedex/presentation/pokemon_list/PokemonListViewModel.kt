package com.kerimbr.compokedex.presentation.pokemon_list

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.kerimbr.compokedex.core.constants.AppConstants
import com.kerimbr.compokedex.core.enums.PokedexListState
import com.kerimbr.compokedex.core.utils.Resource
import com.kerimbr.compokedex.domain.models.PokedexListEntry
import com.kerimbr.compokedex.domain.use_case.pokemon.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {


    val pokedexList: SnapshotStateList<PokedexListEntry> = mutableStateListOf()

    private var _page: Int by mutableStateOf(0)
    var canPaginate: Boolean by mutableStateOf(false)
    var pokedexListState: PokedexListState by mutableStateOf(PokedexListState.IDLE)


    init {
        loadPokemonWithPagination()
    }

    fun loadPokemonWithPagination() {
        if (
            pokedexListState == PokedexListState.FIRST_LOADING ||
            pokedexListState == PokedexListState.PAGINATING
        ) return

        viewModelScope.launch {
            if (_page == 0 || (_page != 0 && canPaginate) && pokedexListState == PokedexListState.IDLE) {

                getPokemonListUseCase(
                    limit = AppConstants.LIMIT,
                    offset = _page * AppConstants.LIMIT,
                ).collect{

                    when(it) {
                        is Resource.Loading -> {
                            pokedexListState =
                                if (_page == 0) PokedexListState.FIRST_LOADING
                                else PokedexListState.PAGINATING
                        }
                        is Resource.Success -> {

                            if (it.data!!.isEmpty()) {
                                pokedexListState = PokedexListState.END_OF_LIST
                                return@collect
                            }

                            canPaginate = it.data.size == AppConstants.LIMIT

                            if (_page == 0) {
                                pokedexList.clear()
                                pokedexList.addAll(it.data)
                            } else {
                                pokedexList.addAll(it.data)
                            }

                            pokedexListState = PokedexListState.IDLE
                            _page++
                        }
                        is Resource.Error -> {
                            pokedexListState = PokedexListState.ERROR
                        }
                    }

                }


            }
        }
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

    override fun onCleared() {
        _page = 0
        pokedexListState = PokedexListState.IDLE
        canPaginate = false
        super.onCleared()
    }

}