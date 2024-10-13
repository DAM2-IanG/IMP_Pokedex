package educ.iesam.imp_pokedex.features.pokemon.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import educ.iesam.imp_pokedex.features.pokemon.domain.GetPokemonListUseCase
import educ.iesam.imp_pokedex.features.pokemon.domain.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PokemonListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
): ViewModel() {


    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    private var currentOffset= 0
    private val limit = 20
    private var hasMorePages = true


    init {
        loadPokemonList(currentOffset)
    }

    private fun loadPokemonList( offset: Int) {
        if (!hasMorePages) {
            Log.d("PokemonListViewModel", "loadPokemonList: No more pages to load")
            return
        }

        viewModelScope.launch (Dispatchers.IO) {
            _uiState.postValue(UiState(isLoading = true))
            try {
                val pokemonList = getPokemonListUseCase.invoke(limit, offset)
                if (pokemonList.results.isEmpty()) {
                    hasMorePages = false
                }
                Log.d("PokemonListViewModel", "loadPokemonList: $pokemonList")
                _uiState.postValue(UiState(pokemonList = pokemonList, isLoading = false))
            } catch (e: Exception) {
                _uiState.postValue(
                    UiState(isLoading = false, error = e.message)
                )
            }
        }
    }

    fun loadNextPage() {
        currentOffset += limit
        loadPokemonList(currentOffset)
    }

    data class UiState (
        val isLoading: Boolean = false,
        val pokemonList: PokemonList? = null,
        val error: String? = null,
    )
}