package com.kerimbr.compokedex.presentation.pokemon_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun PokemonNameAndExp(
    name: String,
    number: Int,
    exp: Int? = null
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround
        ){
            Text(
                text = name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase() else it.toString()
                },
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.W800,
                ),
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.9f
                ),
                modifier = Modifier
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = String.format("#%03d", number),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W400,
                ),
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.8f
                ),
                modifier = Modifier
                    .align(Alignment.Start)
            )
        }

        if (exp != null)
            BaseExpAnimationProgress(
                indicatorProgress = exp.toFloat()
            )

    }

}
