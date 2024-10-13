package educ.iesam.imp_pokedex.features.pokemon.domain

data class PokemonList (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>){
}