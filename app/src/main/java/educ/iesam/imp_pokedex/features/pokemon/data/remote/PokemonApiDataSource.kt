package educ.iesam.imp_pokedex.features.pokemon.data.remote

import educ.iesam.imp_pokedex.app.data.api.ApiClient
import educ.iesam.imp_pokedex.features.pokemon.domain.Pokemon
import educ.iesam.imp_pokedex.features.pokemon.domain.PokemonList

class PokemonApiDataSource {

    private val apiService = ApiClient().apiService

    suspend fun getPokemonList(limit: Int, offset: Int): PokemonList {
        return try {
            apiService.getPokemonList(limit, offset)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getPokemonById(id: Int): Pokemon {
        return try {
            apiService.getPokemonById(id)
        } catch (e: Exception) {
            throw e
        }
    }
}