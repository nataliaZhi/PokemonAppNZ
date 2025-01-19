package dam.pmdm.pokemonappnz.ui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import dam.pmdm.pokemonappnz.R;
import dam.pmdm.pokemonappnz.data.PokemonCaptured;
import dam.pmdm.pokemonappnz.databinding.PokedexCardviewBinding;


/**
 * PokedexAdapter es el adaptador para el RecyclerView que muestra la lista de Pokémon en la Pokédex.
 * Enlaza los datos de los Pokémon capturados con las vistas del RecyclerView.
 */
public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder> {

    // Lista de Pokémon capturados
    private final ArrayList<PokemonCaptured> pokemons;

    // Contexto de la aplicación
    private final Context context;

    private final  PokemonViewModel pokemonViewModel;

    /**
     * Constructor del adaptador PokedexAdapter.
     *
     * @param pokemons Lista de Pokémon capturados.
     * @param context  Contexto de la aplicación.
     */
    public PokedexAdapter(ArrayList<PokemonCaptured> pokemons, Context context, PokemonViewModel pokemonViewModel) {
        this.pokemons = pokemons;
        this.context = context;
        this.pokemonViewModel = pokemonViewModel;

        // Observar los cambios de Pokémon específicos
        pokemonViewModel.getUpdatedPokemon().observeForever(this::updatePokemon);

    }

    /**
     * Método que se llama cuando se crea un nuevo ViewHolder.
     *
     * @param parent   El ViewGroup padre al que se adjuntará el nuevo ViewHolder.
     * @param viewType Tipo de vista del nuevo ViewHolder.
     * @return Un nuevo PokedexViewHolder.
     */
    @NonNull
    @Override
    public PokedexAdapter.PokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Usamos ViewBinding para inflar el layout del item
        PokedexCardviewBinding binding = PokedexCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PokedexAdapter.PokedexViewHolder(binding);
    }

    /**
     * Método para enlazar los datos del Pokémon con el ViewHolder.
     *
     * @param holder   El ViewHolder que se va a enlazar.
     * @param position La posición del Pokémon en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull PokedexViewHolder holder, int position) {
        PokemonCaptured currentPokemon = this.pokemons.get(position);
        holder.bind(currentPokemon, pokemonViewModel);

        // Manejar el evento de clic en cada Pokémon capturado
        holder.itemView.setOnClickListener(view -> {
            if (!pokemonViewModel.isPokemonCaptured(currentPokemon.getName())) {
                itemClicked(currentPokemon, view);
            }
        });


    }

    /**
     * Método que devuelve el número de Pokémon en la lista.
     *
     * @return El número de Pokémon en la lista.
     */
    @Override
    public int getItemCount() {
        return pokemons.size();
    }
    /**
     * Actualiza un Pokémon específico en la lista y notifica al adaptador.
     *
     * @param pokemon El Pokémon que ha sido actualizado.
     */
    public void updatePokemon(PokemonCaptured pokemon) {
        int position = pokemons.indexOf(pokemon);
        if (position != -1) {
            notifyItemChanged(position);
        }
    }

    /**
     * Método que maneja los clics en un Pokémon.
     *
     * @param currentPokemon El Pokémon que fue clickeado.
     * @param view           La vista que fue clickeada.
     */
    private void itemClicked(PokemonCaptured currentPokemon, View view) {
        // Llamar al método que se ejecutará al hacer clic en un Pokémon
        ((MainActivity) context).onPokemonClicked(currentPokemon, view);
    }




    /**
     * ViewHolder que enlaza las vistas del layout del Pokémon capturado.
     */
    public static class PokedexViewHolder extends RecyclerView.ViewHolder {
        // ViewBinding para PokedexCardviewBinding
        private final PokedexCardviewBinding binding;

        /**
         * Constructor del ViewHolder PokedexViewHolder.
         *
         * @param binding ViewBinding para el layout del Pokémon.
         */
        public PokedexViewHolder(PokedexCardviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Método para enlazar los datos del Pokémon con las vistas del layout.
         *
         * @param pokemon El Pokémon que se va a enlazar.
         */

        public void bind(PokemonCaptured pokemon, PokemonViewModel pokemonViewModel) {
            // Asignar los datos del Pokémon al layout
            binding.pokedexName.setText(pokemon.getName());

            // Cargar la imagen con la librería Picasso
            Picasso.get().load(pokemon.getSprites().getFrontDefault()).into(binding.pokedexImage);

            Context context = binding.getRoot().getContext(); // Obtener el contexto
            // Verificar si el Pokémon está en la lista de capturados
            boolean isCaptured = pokemonViewModel.isPokemonCaptured(pokemon.getName());

            if ((isCaptured)) {
                binding.cardViewPokedex.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red));
                binding.getRoot().setEnabled(false); // Deshabilitar el item
                binding.pokedexName.setAlpha(0.5f); // Hacerlo visualmente diferente
            } else {
                binding.cardViewPokedex.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green));
                binding.getRoot().setEnabled(true); // Habilitar el item
                binding.pokedexName.setAlpha(1.0f); // Restaurar la apariencia
            }


        }
    }
}