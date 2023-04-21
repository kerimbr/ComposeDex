package com.kerimbr.compokedex.presentation.pokemon_details.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun BaseExpAnimationProgress(
    indicatorProgress: Float
) {

    var progress by remember { mutableStateOf(0f) }
    val progressAnimDuration = 2000

    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing)
    )

    LaunchedEffect(indicatorProgress){
        progress = indicatorProgress / 500
    }

    Column {
        Box {
            CircularProgressIndicator(
                progress = progressAnimation,
                modifier = Modifier
                    .size(64.dp)
                    .padding(4.dp),
                strokeWidth = 8.dp,
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.1f
                ),
                strokeCap = StrokeCap.Round,
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = String.format("%03d", indicatorProgress.toInt()),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.W800,
                ),
                color = MaterialTheme.colorScheme.secondary.copy(
                    alpha = 0.9f
                ),
            )
        }
        Text(
            text = "Base Exp.",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.W600,
            ),
            color = MaterialTheme.colorScheme.onSurface.copy(
                alpha = 0.8f
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }

}