package com.kerimbr.compokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kerimbr.compokedex.presentation.pokemon_details.PokemonDetailScreen
import com.kerimbr.compokedex.presentation.pokemon_list.PokemonListScreen
import com.kerimbr.compokedex.presentation.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenDestinations.PokemonListScreen.route
                ) {

                    composable(ScreenDestinations.PokemonListScreen.route) {
                        PokemonListScreen(navController = navController)
                    }

                    composable(
                        ScreenDestinations.PokemonDetailScreen.generateRouteWithArgs(
                            "number"
                        ),
                        arguments = ScreenDestinations.PokemonDetailScreen.arguments!!
                    ) {

                        val number: Int = remember{
                            it.arguments!!.getInt("number")
                        }

                        PokemonDetailScreen(
                            navController = navController,
                            number = number
                        )

                    }

                }
            }
        }
    }
}
