package com.kerimbr.compokedex.presentation.pokemon_details.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kerimbr.compokedex.core.utils.parseStatText
import com.kerimbr.compokedex.data.remote.responses.pokemon.Stat
import com.kerimbr.compokedex.data.remote.responses.pokemon.StatX
import com.kerimbr.compokedex.presentation.ui.theme.AppTheme

@Composable
fun PokemonStats(
    modifier: Modifier = Modifier,
    stats: List<Stat>
) {

    stats.forEach { stat ->
        PokemonStatsItem(
            modifier = modifier,
            stat = stat
        )
    }
}


@Composable
fun PokemonStatsItem(
    modifier: Modifier = Modifier,
    stat: Stat
) {

    var progress: Float by remember { mutableStateOf(0f) }
    val progressAnimDuration = 2000

    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = progressAnimDuration,
            easing = FastOutLinearInEasing
        )
    )

    val progressColor: Color by animateColorAsState(
        targetValue = if (progressAnimation < 0.3) {
            MaterialTheme.colorScheme.error
        } else if (progressAnimation < 0.6f) {
            MaterialTheme.colorScheme.tertiary
        } else if (progressAnimation < 1.2f) {
            MaterialTheme.colorScheme.secondary
        } else {
            MaterialTheme.colorScheme.primary
        },
        animationSpec = tween(
            durationMillis = 200,
            easing = FastOutSlowInEasing
        )
    )




    LaunchedEffect(stat.baseStat) {
        progress = stat.baseStat?.div(200f) ?: 0f
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .width(48.dp),
            text = parseStatText(stat.stat),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Normal
            ),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.width(48.dp),
            text = progressAnimation.times(200).toInt().toString(),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            textAlign = TextAlign.Start,
            color = progressColor,
        )
        Spacer(modifier = Modifier.width(8.dp))

        LinearProgressIndicator(
            modifier = Modifier
                .height(20.dp)
                .weight(1f)
                .padding(4.dp),
            progress = progressAnimation,
            color = progressColor,
            trackColor = MaterialTheme.colorScheme.onSurface.copy(
                alpha = 0.1f
            ),
            strokeCap = StrokeCap.Round,
        )
    }


}


@Preview(showBackground = true)
@Composable
fun PreviewPokemonStatsItem() {
    AppTheme {
        Box(modifier = Modifier.padding(32.dp)) {
            PokemonStatsItem(
                stat = Stat(
                    baseStat = 100,
                    effort = 0,
                    stat = StatX(
                        name = "hp",
                        url = "https://pokeapi.co/api/v2/stat/1/"
                    )
                )
            )
        }
    }
}