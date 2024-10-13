package educ.iesam.imp_pokedex.features.pokemon.data

import educ.iesam.imp_pokedex.features.pokemon.data.remote.PokemonApiDataSource
import educ.iesam.imp_pokedex.features.pokemon.domain.PokemonList
import educ.iesam.imp_pokedex.features.pokemon.domain.PokemonRepository

class PokemonDataRepository (
    private val remoteDataSource: PokemonApiDataSource,
) : PokemonRepository {


    override suspend fun getPokemonList(limit: Int, offset: Int): PokemonList {
        return remoteDataSource.getPokemonList(limit, offset)
    }
}