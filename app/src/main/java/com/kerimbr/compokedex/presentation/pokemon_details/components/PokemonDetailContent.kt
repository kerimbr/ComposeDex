package com.kerimbr.compokedex.presentation.pokemon_details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kerimbr.compokedex.domain.models.PokemonDetails
import com.kerimbr.compokedex.presentation.priview_providers.PokemonDetailPreviewProvider
import com.kerimbr.compokedex.presentation.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailContent(
    pokemonDetails: PokemonDetails,
    number: Int,
    navController: NavController,
) {


    val scaffoldState = rememberBottomSheetScaffoldState()


    BottomSheetScaffold(
        sheetContent = {
            Column {

                if (pokemonDetails.height != null && pokemonDetails.weight != null) {
                    SectionTitle(title = "Size")

                    PokemonSize(
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 8.dp)
                            .height(100.dp),
                        height = pokemonDetails.height,
                        weight = pokemonDetails.weight
                    )
                }




                Spacer(modifier = Modifier.height(16.dp))
                SectionTitle(title = "Types")

                if (pokemonDetails.types != null) {
                    PokemonTypeList(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        types = pokemonDetails.types
                    )
                } else {
                    Text(
                        text = "No types available",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W400,
                        ),
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.8f
                        ),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }


                Spacer(modifier = Modifier.height(16.dp))
                SectionTitle(title = "Stats")
                Spacer(modifier = Modifier.height(4.dp))

                if (pokemonDetails.stats != null) {
                    PokemonStats(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        stats = pokemonDetails.stats
                    )
                } else {
                    Text(
                        text = "No stats available",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W400,
                        ),
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.8f
                        ),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 400.dp,
        sheetShape = MaterialTheme.shapes.large,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            PokemonContentHeader(
                modifier = Modifier.padding(4.dp),
                number = number,
                navController = navController,
            )

            Spacer(modifier = Modifier.height(8.dp))

            PokemonNameAndExp(
                name = pokemonDetails.name,
                number = number,
                exp = pokemonDetails.baseExperience
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}

@Composable
private fun SectionTitle(
    title: String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.W800,
        ),
        color = MaterialTheme.colorScheme.primary.copy(
            alpha = 0.7f
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
    )
}



@Preview(showBackground = true)
@Composable
fun PokemonDetailContentPreview(
    @PreviewParameter(PokemonDetailPreviewProvider::class)
    pokemonDetails: PokemonDetails,
) {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            PokemonDetailContent(
                pokemonDetails = pokemonDetails,
                number = 1,
                navController = rememberNavController()
            )
        }
    }
}