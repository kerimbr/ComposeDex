package com.kerimbr.compokedex.presentation.pokemon_list.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.*
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.kerimbr.compokedex.domain.models.PokedexListEntry
import com.kerimbr.compokedex.presentation.pokemon_list.PokemonListViewModel
import java.util.*


@Composable
fun PokemonGridItem(
    entry: PokedexListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    onGenerateDominantColor: (Drawable, (Color) -> Unit) -> Unit
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
                        onGenerateDominantColor(painter.result.drawable) { color ->
                            dominantColor.value = color
                        }
                    }

                }
            )

            // Pokemon Name
            Text(
                text = entry.pokemonName.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.US
                    ) else it.toString()
                },
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

