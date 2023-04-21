package com.kerimbr.compokedex.presentation.pokemon_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.palette.graphics.Palette
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.kerimbr.compokedex.core.utils.calculateColorPalette
import com.kerimbr.compokedex.domain.models.PokedexListEntry
import com.kerimbr.compokedex.presentation.ScreenDestinations


@Composable
fun PokemonGridItem(
    entry: PokedexListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val dominantColor: MutableState<Color> = remember {
        mutableStateOf(Color.Transparent)
    }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(entry.imageUrl)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .crossfade(500)
            .diskCacheKey(entry.imageUrl)
            .memoryCacheKey(entry.imageUrl)
            .build(),
    )


    Box(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.small)
            .background(color = dominantColor.value.copy(alpha = 0.3f))
            .clickable {
                navController.navigate(
                    ScreenDestinations.PokemonDetailScreen.getRouteWithArgs(
                        Pair("number", entry.number)
                    )
                )
            }
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SubcomposeAsyncImage(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(shape = MaterialTheme.shapes.small),
                model = painter.request,
                contentDescription = entry.pokemonName,
                onSuccess = { painter ->
                    if (dominantColor.value == Color.Transparent) {
                       val palette: Palette = calculateColorPalette(painter.result.drawable)
                        palette.dominantSwatch?.rgb?.let {
                            dominantColor.value = Color(it)
                        }
                    }

                }
            )

            // Pokemon Name
            Text(
                text = entry.pokemonName,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.W700
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Pokemon Number
            Text(
                text = "#${String.format("%03d", entry.number)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.6f
                ),
                modifier = Modifier.padding(horizontal = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

        }

    }


}

