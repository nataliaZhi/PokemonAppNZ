package dam.pmdm.pokemonappnz;


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

import dam.pmdm.pokemonappnz.databinding.FragmentPokemonDetailBinding;

/**
 * Fragmento que muestra los detalles de un Pokémon.
 */
public class PokemonDetailFragment extends Fragment {

    // ViewBinding para FragmentPokemonDetailBinding
    private FragmentPokemonDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener los detalles del Pokémon del Bundle
        if (getArguments() != null) {
            String pokemonName = getArguments().getString("name");
            int pokemonId = getArguments().getInt("index");
            String pokemonTypes = getArguments().getString("types");
            String pokemonImage = getArguments().getString("image");
            int pokemonWeight = getArguments().getInt("weight");
            int pokemonHeight = getArguments().getInt("height");

            // Mostrar los detalles del Pokémon en las vistas
            binding.pokemonName.setText(pokemonName);
            binding.pokemonTypes.setText(String.format(Locale.getDefault(), getString(R.string.type), pokemonTypes));
            binding.pokemonWeight.setText(String.format(Locale.getDefault(), getString(R.string.weight), pokemonWeight));
            binding.pokemonHeight.setText(String.format(Locale.getDefault(), getString(R.string.height), pokemonHeight));

            // Cargar la imagen del Pokémon usando Picasso
            Picasso.get().load(pokemonImage).into(binding.pokemonImage);
        } else {
            Toast.makeText(getContext(), R.string.error_loading_pokemon_details, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.detail_title);
        }
    }
}