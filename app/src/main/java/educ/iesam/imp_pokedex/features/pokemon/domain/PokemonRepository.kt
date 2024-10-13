package educ.iesam.imp_pokedex.features.pokemon.domain

interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): PokemonList

}