package com.example.pokemonapp.dagger

import com.example.pokemonapp.domain.repository.PokemonRepository
import com.example.pokemonapp.network.PokemonApiService
import com.example.pokemonapp.network.PokeMonApi
import com.example.pokemonapp.viewmodel.PokeMonViewModelFactory
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
    fun providePokeMonApiService(): PokemonApiService {
            return PokeMonApi.createService()
    }

    @Singleton
    @Provides
    fun providePokeMonRepository(pokeMonApiService: PokemonApiService): PokemonRepository {
        return PokemonRepository(pokeMonApiService)
    }

    @Singleton
    @Provides
    fun providePokeMonViewModelFactory(pokemonRepository: PokemonRepository): PokeMonViewModelFactory {
        return PokeMonViewModelFactory(pokemonRepository)
    }
}