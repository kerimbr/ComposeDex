package com.kerimbr.compokedex.core.utils

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import com.kerimbr.compokedex.data.remote.responses.pokemon.StatX
import com.kerimbr.compokedex.data.remote.responses.pokemon.Type
import com.kerimbr.compokedex.presentation.ui.theme.*
import java.util.Locale

fun generatePokemonArtworkUrl(number: Int): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$number.png"
}


fun calculateColorPalette(drawable: Drawable): Palette {
    val bitmap = (drawable as BitmapDrawable).bitmap
        .copy(Bitmap.Config.ARGB_8888, true)

    return Palette.from(bitmap).generate()
}

fun parseTypeToColor(type: Type): Color {
    return when(type.type?.name?.lowercase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatText(stat: StatX?): String {
    return when(stat?.name?.lowercase(Locale.ROOT) ?: "N/A") {
        "hp" -> "HP"
        "attack" -> "ATK"
        "defense" -> "DEF"
        "special-attack" -> "SP. ATK"
        "special-defense" -> "SP. DEF"
        "speed" -> "SPD"
        else -> "N/A"
    }
}