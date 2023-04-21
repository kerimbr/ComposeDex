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
            navArgument("number") {
                type = NavType.IntType
            }
        )
    )

    // xxx/{arg1}/{arg2}
    fun generateRouteWithArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }

    // xxx/arg1/arg2
    fun getRouteWithArgs(vararg args: Pair<String, Any>): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/${arg.second}")
            }
        }
    }

}
