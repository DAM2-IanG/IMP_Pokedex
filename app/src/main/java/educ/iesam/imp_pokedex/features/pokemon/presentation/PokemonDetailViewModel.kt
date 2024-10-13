package educ.iesam.imp_pokedex.features.pokemon.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import educ.iesam.imp_pokedex.features.pokemon.domain.GetPokemonByIdUseCase
import educ.iesam.imp_pokedex.features.pokemon.domain.Pokemon
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase
) : ViewModel() {

    private val _pokemonDetail = MutableLiveData<UiState>()
    val pokemonDetail: LiveData<UiState> = _pokemonDetail

    fun loadPokemonDetail(pokemonId: Int) {
        viewModelScope.launch {
            try {
                val pokemon = getPokemonByIdUseCase.invoke(pokemonId)
                _pokemonDetail.postValue(UiState(
                    isLoading = false,
                    pokemon = pokemon
                ))
            } catch (e: Exception){
                // Manejar errores
            }
        }
    }


    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: String? = null,
        val pokemon : Pokemon? = null
    )
}