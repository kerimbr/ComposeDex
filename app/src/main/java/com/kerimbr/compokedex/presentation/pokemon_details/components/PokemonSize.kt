package com.kerimbr.compokedex.presentation.pokemon_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FitnessCenter
import androidx.compose.material.icons.rounded.Straighten
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kerimbr.compokedex.presentation.ui.theme.AppTheme

@Composable
fun PokemonSize(
    modifier: Modifier = Modifier,
    height: Int,
    weight: Int
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.tertiaryContainer.copy(
                    alpha = 0.2f
                ),
                shape = MaterialTheme.shapes.small
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.Straighten,
                        contentDescription = "Height",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "$height m",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W500
                            ),
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "Height",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.W300
                            ),
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }

                }

            }
            // Vertical divider
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .width(1.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        shape = MaterialTheme.shapes.large
                    )
                    .align(Alignment.CenterVertically)
            )

            // Weight
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.FitnessCenter,
                        contentDescription = "Weight",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "$weight kg",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W500
                            ),
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "Weight",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.W300
                            ),
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }

                }

            }

        }

    }
}



@Preview(showBackground = true)
@Composable
fun PreviewPokemonSize() {
    AppTheme {
        PokemonSize(
            height = 10,
            weight = 10
        )
    }
}

