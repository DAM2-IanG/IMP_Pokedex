package educ.iesam.imp_pokedex.features.pokemon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import educ.iesam.imp_pokedex.databinding.FragmentPokemonDetailBinding
import educ.iesam.imp_pokedex.features.pokemon.domain.Pokemon

class PokemonDetailFragment : Fragment() {

    private lateinit var factory : PokemonFactory
    private lateinit var viewModel : PokemonDetailViewModel

    private val pokemonArgs: PokemonDetailFragmentArgs by navArgs()

    private var _binding : FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    private fun bindData(pokemon: Pokemon){
        binding.pokemonNameTextView.text = pokemon.toString()
    }

    private fun setUpObservers(){
        viewModel.pokemonDetail.observe(viewLifecycleOwner) { uiState ->
            uiState.pokemon?.let { pokemon ->
                bindData(pokemon)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factory = PokemonFactory()
        viewModel = factory.buildPokemonDetailViewModel()
        viewModel.loadPokemonDetail(pokemonArgs.pokemonId)
        setUpObservers()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}