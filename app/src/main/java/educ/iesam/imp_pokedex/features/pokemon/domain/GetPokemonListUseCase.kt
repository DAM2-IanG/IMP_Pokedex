package educ.iesam.imp_pokedex.features.pokemon.domain

class GetPokemonListUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun invoke(limit: Int, offset: Int): PokemonList {
        return pokemonRepository.getPokemonList(limit, offset)
    }
}