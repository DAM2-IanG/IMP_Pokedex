package educ.iesam.imp_pokedex.features.pokemon.domain

data class Pokemon(
    val name: String,
    val url: String,
) {
    val id: Int
        get() {
            val urlParts = url.trimEnd('/').split('/')
            return urlParts.last().toIntOrNull() ?: -1
        }
}