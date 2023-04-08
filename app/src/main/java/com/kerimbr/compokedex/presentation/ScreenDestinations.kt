package com.kerimbr.compokedex.presentation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class ScreenDestinations(
    val route: String,
    val arguments: List<NamedNavArgument>? = null
) {
    object PokemonListScreen : ScreenDestinations(
        route = "pokemon_list_screen"
    )

    object PokemonDetailScreen : ScreenDestinations(
        route = "pokemon_detail_screen",
        arguments = listOf(
            navArgument("dominantColor") {
                type = NavType.IntType
            },
            navArgument("pokemonName") {
                type = NavType.StringType
            }
        )
    )

    fun getRouteWithArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }

}
