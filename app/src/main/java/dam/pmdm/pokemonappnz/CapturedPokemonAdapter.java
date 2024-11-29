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

public class CapturedPokemonAdapter extends RecyclerView.Adapter<CapturedPokemonAdapter.PokemonViewHolder> {

    private final ArrayList<Pokemon> capturedPokemonList;
    private final Context context;

    public CapturedPokemonAdapter(ArrayList<Pokemon> capturedPokemonList, Context context) {
        this.capturedPokemonList = capturedPokemonList;
        this.context = context;
    }

    // Método que crea el ViewHolder
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Usamos ViewBinding para inflar el layout del item
        PokemonCardviewBinding binding = PokemonCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PokemonViewHolder(binding);
    }

    // Método para enlazar los datos con el ViewHolder
    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon currentPokemon = this.capturedPokemonList.get(position);
        holder.bind(currentPokemon);

        // Manejar el evento de clic en cada Pokémon capturado
        holder.itemView.setOnClickListener(view -> itemClicked(currentPokemon, view));
    }

    @Override
    public int getItemCount() {
        return capturedPokemonList.size();
    }

    // Método que maneja los clics en un Pokémon capturado
    private void itemClicked(Pokemon currentPokemon, View view) {
        // Llamar al método que se ejecutará al hacer clic en un Pokémon
        ((MainActivity) context).onPokemonClicked(currentPokemon, view);
    }

    // ViewHolder que enlaza las vistas del layout del Pokémon capturado
    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        private final PokemonCardviewBinding binding;

        public PokemonViewHolder(PokemonCardviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Pokemon pokemon) {
            // Asignar los datos del Pokémon al layout
            binding.pokemonName.setText(pokemon.getName());

            // Cargar la imagen con una librería como Picasso o Glide
            Picasso.get().load(pokemon.getImageUrl()).into(binding.pokemonImage);
        }
    }
}