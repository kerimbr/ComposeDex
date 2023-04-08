package com.kerimbr.compokedex.di

import com.kerimbr.compokedex.data.remote.PokeApi
import com.kerimbr.compokedex.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        api: PokeApi
    ): PokemonRepository = PokemonRepository(api)


}