package com.kerimbr.compokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

                    }

                    composable(
                        ScreenDestinations.PokemonDetailScreen.getRouteWithArgs(
                            "dominantColor",
                            "pokemonName"
                        ),
                        arguments = ScreenDestinations.PokemonDetailScreen.arguments!!
                    ) {
                        val dominantColor = remember{
                            val color = it.arguments?.getInt("dominantColor")
                            color?.let { Color(it) }?: Color.White
                        }

                        val pokemonName = remember{
                            it.arguments?.getString("pokemonName")
                        }


                    }

                }
            }
        }
    }
}
