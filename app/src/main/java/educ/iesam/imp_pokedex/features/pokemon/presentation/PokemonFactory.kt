package educ.iesam.imp_pokedex.features.pokemon.presentation

import educ.iesam.imp_pokedex.features.pokemon.data.PokemonDataRepository
import educ.iesam.imp_pokedex.features.pokemon.data.remote.PokemonApiDataSource
import educ.iesam.imp_pokedex.features.pokemon.domain.GetPokemonListUseCase

class PokemonFactory {

    private val remoteDataSource = PokemonDataRepository(PokemonApiDataSource())
    private val getPokemonListUseCase = GetPokemonListUseCase(remoteDataSource)

    fun buildPokemonListViewModel() = PokemonListViewModel(getPokemonListUseCase)
}