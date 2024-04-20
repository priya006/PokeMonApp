package com.example.pokemonapp.dagger

import com.example.pokemonapp.domain.repository.PokemonRepository
import com.example.pokemonapp.network.PokeMonApiService
import com.example.pokemonapp.network.RetrofitInstance
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
    fun providePokeMonApiService(): PokeMonApiService {
            return RetrofitInstance.create()
    }

    @Singleton
    @Provides
    fun providePokeMonRepository(pokeMonApiService: PokeMonApiService): PokemonRepository {
        return PokemonRepository(pokeMonApiService)
    }

    @Singleton
    @Provides
    fun providePokeMonViewModelFactory(pokemonRepository: PokemonRepository): PokeMonViewModelFactory {
        return PokeMonViewModelFactory(pokemonRepository)
    }
}