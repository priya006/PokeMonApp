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

/**
 * Dagger Hilt module responsible for providing application-wide dependencies.
 * These dependencies include the PokeMon API service, repository, and ViewModel factory.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of the PokeMon API service.
     *
     * @return Instance of the PokeMon API service.
     */
    @Singleton
    @Provides
    fun providePokeMonApiService(): PokemonApiService {
            return PokeMonApi.createService()
    }

    /**
     * Provides a singleton instance of the PokeMon repository.
     *
     * @param pokeMonApiService Instance of the PokeMon API service.
     * @return Instance of the PokeMon repository.
     */
    @Singleton
    @Provides
    fun providePokeMonRepository(pokeMonApiService: PokemonApiService): PokemonRepository {
        return PokemonRepository(pokeMonApiService)
    }

    /**
     * Provides a singleton instance of the PokeMon ViewModel factory.
     *
     * @param pokemonRepository Instance of the PokeMon repository.
     * @return Instance of the PokeMon ViewModel factory.
     */
    @Singleton
    @Provides
    fun providePokeMonViewModelFactory(pokemonRepository: PokemonRepository): PokeMonViewModelFactory {
        return PokeMonViewModelFactory(pokemonRepository)
    }
}