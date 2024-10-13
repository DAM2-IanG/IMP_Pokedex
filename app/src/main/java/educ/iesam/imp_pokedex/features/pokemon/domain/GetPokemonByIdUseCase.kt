package educ.iesam.imp_pokedex.features.pokemon.domain

class GetPokemonByIdUseCase (private val repository: PokemonRepository){

    suspend fun invoke(id: Int): Pokemon {
        return repository.getPokemonById(id)
    }

}