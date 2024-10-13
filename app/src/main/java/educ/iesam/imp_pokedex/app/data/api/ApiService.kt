package educ.iesam.imp_pokedex.app.data.api

import educ.iesam.imp_pokedex.features.pokemon.domain.PokemonList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): PokemonList
}