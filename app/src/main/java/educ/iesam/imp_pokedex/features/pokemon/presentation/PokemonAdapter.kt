package educ.iesam.imp_pokedex.features.pokemon.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import educ.iesam.imp_pokedex.R
import educ.iesam.imp_pokedex.features.pokemon.domain.Pokemon

class PokemonAdapter (private var pokemons: List<Pokemon>) :
RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {


    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val pokemonNameTextView: TextView = itemView.findViewById(R.id.pokemonName)
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun getItemCount() = pokemons.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
       val pokemon = pokemons[position]
        holder.pokemonNameTextView.text = pokemon.name
    }


    fun updateData(newPokemons: List<Pokemon>) {
        val updatedList = pokemons.toMutableList()
        updatedList.addAll(newPokemons)
        pokemons = updatedList
        notifyDataSetChanged()
    }

}