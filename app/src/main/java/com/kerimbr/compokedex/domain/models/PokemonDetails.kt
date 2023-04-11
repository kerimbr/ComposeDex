package com.kerimbr.compokedex.domain.models

import com.kerimbr.compokedex.data.remote.responses.pokemon.*

data class PokemonDetails(
    val id: Int,
    val name: String,

    val height: Int?,
    val weight: Int?,
    val baseExperience: Int?,

    val forms: List<Form>?,
    val abilities: List<Ability>?,
    val types: List<Type>?,
    val stats: List<Stat>?,
    val moves: List<Move>?,
    val heldItems: List<HeldItem>?,
    val gameIndices: List<GameIndice>?,
)
