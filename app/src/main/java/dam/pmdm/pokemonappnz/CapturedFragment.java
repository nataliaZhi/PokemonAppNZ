package dam.pmdm.pokemonappnz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dam.pmdm.pokemonappnz.databinding.FragmentCapturedBinding;
import java.util.ArrayList;
import java.util.Arrays;


public class CapturedFragment extends Fragment {


    private FragmentCapturedBinding binding;
    private ArrayList<PokemonCaptured> capturedPokemons;
    private CapturedPokemonAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout utilizando el binding
        binding = FragmentCapturedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Inicializa la lista de pokémons capturados (antes de cargar datos)
        capturedPokemons = new ArrayList<>();

        // Cargar los datos desde la API
        loadPokemonsFirebase();

        // Configurar el RecyclerView
        adapter = new CapturedPokemonAdapter(capturedPokemons, requireContext());
        binding.recyclerViewCaptured.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewCaptured.setAdapter(adapter);


    }

    private void loadPokemonsFirebase() {
//
//    //para prueba manual
//
//            capturedPokemons.add(new PokemonCaptured(
//                    "Bulbasaur",
//                    1,
//                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
//                    Arrays.asList("Grass", "Poison"),
//                    6.9f,  // Peso en kg
//                    0.7f   // Altura en metros
//            ));
//
//            capturedPokemons.add(new PokemonCaptured(
//                    "Charmander",
//                    4,
//                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
//                    Arrays.asList("Fire"),
//                    8.5f,  // Peso en kg
//                    0.6f   // Altura en metros
//            ));
//
//            capturedPokemons.add(new PokemonCaptured(
//                    "Squirtle",
//                    7,
//                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png",
//                    Arrays.asList("Water"),
//                    9.0f,  // Peso en kg
//                    0.5f   // Altura en metros
//            ));
//
//            capturedPokemons.add(new PokemonCaptured(
//                    "Pikachu",
//                    25,
//                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
//                    Arrays.asList("Electric"),
//                    6.0f,  // Peso en kg
//                    0.4f   // Altura en metros
//            ));
//
//            capturedPokemons.add(new PokemonCaptured(
//                    "Eevee",
//                    133,
//                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/133.png",
//                    Arrays.asList("Normal"),
//                    6.5f,  // Peso en kg
//                    0.3f   // Altura en metros
//            ));
//

        }

    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_captured);
        }
    }
}