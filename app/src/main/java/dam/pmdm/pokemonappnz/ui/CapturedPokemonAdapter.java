package dam.pmdm.pokemonappnz.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Locale;

import dam.pmdm.pokemonappnz.data.PokemonCaptured;
import dam.pmdm.pokemonappnz.R;
import dam.pmdm.pokemonappnz.databinding.PokemonCardviewBinding;

/**
 * CapturedPokemonAdapter es el adaptador para el RecyclerView que muestra la lista de Pokémon capturados.
 * Enlaza los datos de los Pokémon capturados con las vistas del RecyclerView.
 */
public class CapturedPokemonAdapter extends RecyclerView.Adapter<CapturedPokemonAdapter.PokemonViewHolder> {

    // Lista de Pokémon capturados
    private final ArrayList<PokemonCaptured> capturedPokemonList;

    // Contexto de la aplicación
    private final Context context;

    // Indica si la eliminación de Pokémon está habilitada
    private boolean isDeletionEnabled;

    /**
     * Constructor del adaptador CapturedPokemonAdapter.
     * @param capturedPokemonList Lista de Pokémon capturados.
     * @param context Contexto de la aplicación.
     */
    public CapturedPokemonAdapter(ArrayList<PokemonCaptured> capturedPokemonList, Context context) {
        this.capturedPokemonList = capturedPokemonList;
        this.context = context;

        // Leer la preferencia de SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        isDeletionEnabled = sharedPreferences.getBoolean("delete_pokemon_enabled", false); // false por defecto
    }

    /**
     * Método que crea el ViewHolder.
     * @param parent El ViewGroup padre al que se adjuntará el nuevo ViewHolder.
     * @param viewType Tipo de vista del nuevo ViewHolder.
     * @return Un nuevo PokemonViewHolder.
     */
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Usamos ViewBinding para inflar el layout del item
        PokemonCardviewBinding binding = PokemonCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PokemonViewHolder(binding);
    }

    /**
     * Método para enlazar los datos del Pokémon con el ViewHolder.
     * @param holder El ViewHolder que se va a enlazar.
     * @param position La posición del Pokémon en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        PokemonCaptured currentPokemon = this.capturedPokemonList.get(position);
        holder.bind(currentPokemon);

        // Manejar el evento de clic en cada Pokémon capturado
        holder.itemView.setOnClickListener(view -> itemClicked(currentPokemon, view));
        holder.binding.deleteButton.setOnClickListener(view -> onDeleteClicked(currentPokemon, position));

        // Mostrar u ocultar el botón de eliminación basado en la preferencia
        if (!isDeletionEnabled) {
            holder.binding.deleteButton.setVisibility(View.GONE);
        } else {
            holder.binding.deleteButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Método que maneja los clics en el botón de eliminación de un Pokémon capturado.
     * @param currentPokemon El Pokémon capturado que se va a eliminar.
     * @param position La posición del Pokémon en la lista.
     */
    private void onDeleteClicked(PokemonCaptured currentPokemon, int position) {
        // Llamar al método que se ejecutará al hacer clic en un Pokémon capturado
        ((MainActivity) context).onDeleteClicked(currentPokemon, position);
    }

    /**
     * Método que devuelve el número de Pokémon en la lista.
     * @return El número de Pokémon en la lista.
     */
    @Override
    public int getItemCount() {
        return capturedPokemonList.size();
    }

    /**
     * Método que maneja los clics en un Pokémon capturado.
     * @param currentPokemon El Pokémon que fue clickeado.
     * @param view La vista que fue clickeada.
     */
    private void itemClicked(PokemonCaptured currentPokemon, View view) {
        // Llamar al método que se ejecutará al hacer clic en un Pokémon capturado
        ((MainActivity) context).onCapturedPokemonClicked(currentPokemon, view);
    }

    /**
     * Método para eliminar un Pokémon de la lista y notificar al adaptador.
     * @param position La posición del Pokémon en la lista.
     */
    public void removePokemon(int position) {
        capturedPokemonList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, capturedPokemonList.size());
    }

    /**
     * Método para habilitar o deshabilitar la eliminación de Pokémon.
     * @param isEnabled true para habilitar, false para deshabilitar.
     */
    public void setDeletionEnabled(boolean isEnabled) {
        this.isDeletionEnabled = isEnabled;
        notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
    }

    /**
     * ViewHolder que enlaza las vistas del layout del Pokémon capturado.
     */
    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        // ViewBinding para PokemonCardviewBinding
        private final PokemonCardviewBinding binding;

        /**
         * Constructor del ViewHolder PokemonViewHolder.
         * @param binding ViewBinding para el layout del Pokémon.
         */
        public PokemonViewHolder(PokemonCardviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Método para enlazar los datos del Pokémon con las vistas del layout.
         * @param pokemon El Pokémon que se va a enlazar.
         */
        public void bind(PokemonCaptured pokemon) {
            // Asignar los datos del Pokémon al layout
            binding.name.setText(pokemon.getName());
            binding.types.setText(String.format(Locale.getDefault(), binding.getRoot().getContext().getString(R.string.type), pokemon.getTypesAsString()));
            binding.weight.setText(String.format(Locale.getDefault(), binding.getRoot().getContext().getString(R.string.weight), pokemon.getWeight()));
            binding.height.setText(String.format(Locale.getDefault(), binding.getRoot().getContext().getString(R.string.height), pokemon.getHeight()));


            // Cargar la imagen con una librería como Picasso
            Picasso.get().load(pokemon.getSprites().getFrontDefault()).into(binding.sprites);
        }
    }
}