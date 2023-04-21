package com.kerimbr.compokedex.presentation.pokemon_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kerimbr.compokedex.core.utils.parseTypeToColor
import com.kerimbr.compokedex.data.remote.responses.pokemon.Type
import com.kerimbr.compokedex.presentation.ui.theme.AppTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonTypeList(
    modifier: Modifier = Modifier,
    types: List<Type>?
) {


    FlowRow(
        modifier = modifier,
    ) {
        types?.forEach { type ->
            PokemonTypeChip(type = type)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }

}

@Composable
fun PokemonTypeChip(
    type: Type
) {

    val typeColor: Color = remember(type) {
        parseTypeToColor(type)
    }

    Card(
        modifier = Modifier
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = typeColor.copy(
                alpha = 0.1f
            )
        ),
        shape = MaterialTheme.shapes.extraSmall,
    ) {

        Row {
            Spacer(modifier = Modifier.width(4.dp))
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .size(12.dp)
                    .background(color = typeColor)
                    .align(Alignment.CenterVertically)
            )

            Text(
                modifier = Modifier.padding(4.dp),
                text = "${
                    type.type?.name?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase()
                        else it.toString()
                    }
                }",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.W500,
                color = typeColor
            )
            Spacer(modifier = Modifier.width(4.dp))
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPokemonTypeChip() {
    AppTheme {
        PokemonTypeChip(
            type = Type(
                slot = 1,
                type = com.kerimbr.compokedex.data.remote.responses.pokemon.TypeX(
                    name = "grass",
                    url = "https://pokeapi.co/api/v2/type/12/"
                )
            )
        )
    }
}













