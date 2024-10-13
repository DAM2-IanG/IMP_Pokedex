package educ.iesam.imp_pokedex.features.pokemon.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import educ.iesam.imp_pokedex.databinding.FragmentPokemonListBinding

class PokemonListFragment : Fragment() {

    private lateinit var adapter: PokemonAdapter
    private lateinit var viewModel: PokemonListViewModel
    private lateinit var factory: PokemonFactory

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PokemonAdapter(emptyList()) { pokemonId ->
            val action = PokemonListFragmentDirections.pokemonListFragmentToPokemonDetailFragment(pokemonId)
            findNavController().navigate(action)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter


        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()


                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    loadNextPage()
                }
            }
        })

        factory = PokemonFactory()
        viewModel = factory.buildPokemonListViewModel()

        setupObservers()
    }

    private fun loadNextPage() {
        viewModel.loadNextPage()
    }

    private fun setupObservers() {
        val observer = Observer<PokemonListViewModel.UiState> { uiState ->
            Log.d("PokemonListFragment", "Observer triggered: $uiState")

            if (uiState.isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }

            uiState.pokemonList?.let { pokemonList ->
                Log.d("PokemonListFragment", "Pokemons: ${pokemonList.results}")
                adapter.updateData(pokemonList.results)
            }

            uiState.error?.let { error ->
                Log.e("PokemonListFragment", "Error loading Pokémon: $error")
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
