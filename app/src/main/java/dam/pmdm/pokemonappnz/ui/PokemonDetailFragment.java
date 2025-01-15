package dam.pmdm.pokemonappnz.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.Locale;

import dam.pmdm.pokemonappnz.R;
import dam.pmdm.pokemonappnz.databinding.FragmentPokemonDetailBinding;

/**
  Fragmento que muestra los detalles de un Pokémon.
        */
public class PokemonDetailFragment extends Fragment {

    // ViewBinding para FragmentPokemonDetailBinding
    private FragmentPokemonDetailBinding binding;

    /**
     * Método que se llama cuando la vista del fragmento es creada.
     * @param inflater Inflater para inflar la vista del fragmento.
     * @param container Contenedor padre del fragmento.
     * @param savedInstanceState Estado guardado del fragmento.
     * @return La vista raíz del fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar la vista del fragmento utilizando ViewBinding
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Método que se llama después de que la vista del fragmento ha sido creada.
     * @param view La vista del fragmento.
     * @param savedInstanceState Estado guardado del fragmento.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener los detalles del Pokémon del Bundle
        if (getArguments() != null) {
            String pokemonName = getArguments().getString("name"); // Obtener el nombre del Pokémon
            int pokemonId = getArguments().getInt("index"); // Obtener el ID del Pokémon
            String pokemonTypes = getArguments().getString("types"); // Obtener los tipos del Pokémon
            String pokemonImage = getArguments().getString("image"); // Obtener la URL de la imagen del Pokémon
            int pokemonWeight = getArguments().getInt("weight"); // Obtener el peso del Pokémon
            int pokemonHeight = getArguments().getInt("height"); // Obtener la altura del Pokémon

            // Mostrar los detalles del Pokémon en las vistas
            binding.pokemonName.setText(pokemonName);
            binding.pokemonIndex.setText(String.format(Locale.getDefault(), getString(R.string.index), pokemonId));
            binding.pokemonTypes.setText(String.format(Locale.getDefault(), getString(R.string.type), pokemonTypes));
            binding.pokemonWeight.setText(String.format(Locale.getDefault(), getString(R.string.weight), pokemonWeight));
            binding.pokemonHeight.setText(String.format(Locale.getDefault(), getString(R.string.height), pokemonHeight));

            // Cargar la imagen del Pokémon usando Picasso
            Picasso.get().load(pokemonImage).into(binding.pokemonImage);
        } else {
            // Mostrar un mensaje de error si no se pueden cargar los detalles del Pokémon
            Toast.makeText(getContext(), R.string.error_loading_pokemon_details, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método que se llama cuando el fragmento se vuelve visible para el usuario.
     */
    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.detail_title);
        }
    }
}