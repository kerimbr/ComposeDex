package com.kerimbr.compokedex.presentation.pokemon_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.palette.graphics.Palette
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.kerimbr.compokedex.core.utils.calculateColorPalette
import com.kerimbr.compokedex.core.utils.generatePokemonArtworkUrl
import com.kerimbr.compokedex.presentation.pokemon_details.PokemonDetailsViewModel

@Composable
fun PokemonContentHeader(
    number: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonDetailsViewModel = hiltViewModel()
) {

    val palette: MutableState<Palette?> = remember(number) {
        mutableStateOf(null)
    }


    val imageUrl: String = remember {
        generatePokemonArtworkUrl(number)
    }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .crossfade(1000)
            .diskCacheKey(imageUrl)
            .memoryCacheKey(imageUrl)
            .build(),
    )


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        palette.value?.dominantSwatch?.rgb?.let { Color(it).copy(alpha = 0.3f) }
                            ?: MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),

                        palette.value?.vibrantSwatch?.rgb?.let { Color(it).copy(alpha = 0.3f) }
                            ?: MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),

                        palette.value?.mutedSwatch?.rgb?.let { Color(it).copy(alpha = 0.3f) }
                            ?: MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f),
                    ),
                    start = Offset(Float.POSITIVE_INFINITY, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY),
                )
            )
    ) {
        Column(
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                ),
            ) {
                Image(
                    imageVector = Icons.Rounded.ChevronLeft,
                    contentDescription = null,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterVertically),
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp,
                    ),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    )
                ) {
                    IconButton(
                        onClick = {
                            val newNumber = if (number == 1) 1000 else number - 1
                            viewModel.pokemonId = newNumber
                        }
                    ) {
                        Image(
                            imageVector = Icons.Rounded.ChevronLeft,
                            contentDescription = "Previous",
                        )
                    }
                }

                SubcomposeAsyncImage(
                    model = painter.request,
                    modifier = modifier
                        .size(200.dp)
                        .clip(CircleShape),
                    contentDescription = number.toString(),
                    onSuccess = {
                        if (palette.value == null) {
                            palette.value = calculateColorPalette(it.result.drawable)
                        }
                    },
                )

                Card(
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterVertically),
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp,
                    ),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    )
                ) {
                    IconButton(onClick = {
                        val newNumber = if (number == 1000) 1 else number + 1
                        viewModel.pokemonId = newNumber
                    }) {
                        Image(
                            imageVector = Icons.Rounded.ChevronRight,
                            contentDescription = "Next",
                        )
                    }
                }

            }
        }


    }
}