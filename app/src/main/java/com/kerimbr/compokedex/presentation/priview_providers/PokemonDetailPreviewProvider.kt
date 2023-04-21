package com.kerimbr.compokedex.presentation.priview_providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.kerimbr.compokedex.data.remote.responses.pokemon.*
import com.kerimbr.compokedex.domain.models.PokemonDetails

class PokemonDetailPreviewProvider :
    PreviewParameterProvider<PokemonDetails> {

    override val values: Sequence<PokemonDetails>
        get() = sequenceOf(
            PokemonDetails(
                id = 1,
                name = "Bulbasaur",
                height = 7,
                weight = 69,
                baseExperience = 64,

                types = listOf(
                    Type(
                        slot = 1,
                        type = TypeX(
                            name = "grass",
                            url = "https://pokeapi.co/api/v2/type/12/"
                        )
                    ),
                    Type(
                        slot = 2,
                        type = TypeX(
                            name = "poison",
                            url = "https://pokeapi.co/api/v2/type/4/"
                        )
                    ),
                ),
                abilities = listOf(
                    Ability(
                        ability = AbilityX(
                            name = "overgrow",
                            url = "https://pokeapi.co/api/v2/ability/65/"
                        ),
                        isHidden = false,
                        slot = 1
                    ),
                ),
                stats = listOf(
                    Stat(
                        baseStat = 45,
                        effort = 0,
                        stat = StatX(
                            name = "hp",
                            url = "https://pokeapi.co/api/v2/stat/1/"
                        )
                    ),
                    Stat(
                        baseStat = 49,
                        effort = 0,
                        stat = StatX(
                            name = "attack",
                            url = "https://pokeapi.co/api/v2/stat/2/"
                        )
                    ),
                    Stat(
                        baseStat = 49,
                        effort = 0,
                        stat = StatX(
                            name = "defense",
                            url = "https://pokeapi.co/api/v2/stat/3/"
                        )
                    ),
                ),
                forms = null,
                gameIndices = null,
                heldItems = null,
                moves = null,
                sprites = Sprites(
                    backDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png",
                    backFemale = null,
                    backShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/1.png",
                    backShinyFemale = null,
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    frontFemale = null,
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
                    frontShinyFemale = null,
                    other = null,
                    versions = null
                ),
            )
        )

}