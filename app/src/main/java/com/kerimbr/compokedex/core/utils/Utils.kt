package com.kerimbr.compokedex.core.utils

fun generatePokemonArtworkUrl(number: Int): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$number.png"
}