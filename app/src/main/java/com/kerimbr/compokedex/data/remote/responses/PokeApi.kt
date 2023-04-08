package com.kerimbr.compokedex.data.remote.responses

import com.kerimbr.compokedex.data.remote.responses.pokemon.PokemonResponse
import com.kerimbr.compokedex.data.remote.responses.pokemon_list.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponse


    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Query("name") name: String
    ): PokemonResponse

}