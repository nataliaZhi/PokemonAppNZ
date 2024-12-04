package dam.pmdm.pokemonappnz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dam.pmdm.pokemonappnz.databinding.PokemonCardviewBinding;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder>  {


    private final ArrayList<PokemonCaptured> pokemons;
    private final Context context;

    public PokedexAdapter(ArrayList<PokemonCaptured> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }


    @NonNull
    @Override
    public PokedexAdapter.PokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Usamos ViewBinding para inflar el layout del item
        PokemonCardviewBinding binding = PokemonCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PokedexAdapter.PokedexViewHolder(binding);
    }
    // Método para enlazar los datos con el ViewHolder
    @Override
    public void onBindViewHolder(@NonNull PokedexViewHolder holder, int position) {
        PokemonCaptured currentPokemon = this.pokemons.get(position);
        holder.bind(currentPokemon);

        // Manejar el evento de clic en cada Pokémon capturado
        holder.itemView.setOnClickListener(view -> itemClicked(currentPokemon, view));
    }


    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    // Método que maneja los clics en un Pokémon
    private void itemClicked(PokemonCaptured currentPokemon, View view) {
        // Llamar al método que se ejecutará al hacer clic en un Pokémon
        ((MainActivity) context).onPokemonClicked(currentPokemon, view);
    }

    // ViewHolder que enlaza las vistas del layout del Pokémon capturado
    public static class PokedexViewHolder extends RecyclerView.ViewHolder {
        private final PokemonCardviewBinding binding;

        public PokedexViewHolder(PokemonCardviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(PokemonCaptured pokemon) {
            // Asignar los datos del Pokémon al layout
            binding.pokemonName.setText(pokemon.getName());

            // Cargar la imagen con una librería  Picasso
            Picasso.get().load(pokemon.getSprites().getFrontDefault()).into(binding.pokemonImage);
        }
    }
}
