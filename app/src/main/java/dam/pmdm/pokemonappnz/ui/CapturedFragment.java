package dam.pmdm.pokemonappnz.ui;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dam.pmdm.pokemonappnz.data.PokemonCaptured;
import dam.pmdm.pokemonappnz.util.PreferencesHelper;
import dam.pmdm.pokemonappnz.R;
import dam.pmdm.pokemonappnz.data.PokemonService;
import dam.pmdm.pokemonappnz.databinding.FragmentCapturedBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * CapturedFragment es la clase que maneja la pestaña de Pokémon capturados de la aplicación.
 * Muestra una lista de Pokémon que han sido capturados y almacenados en Firebase Firestore.
 */
public class CapturedFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    // ViewBinding para FragmentCapturedBinding
    private FragmentCapturedBinding binding;

    // Lista de Pokémon capturados
    private ArrayList<PokemonCaptured> capturedPokemons;

    // Adaptador para el RecyclerView
    private CapturedPokemonAdapter adapter;

    // Instancia de PreferencesHelper
    private PreferencesHelper preferencesHelper;

    // Instancia de PokemonService
    PokemonService pokemonService;


    /**
     * Método que se llama cuando se crea la vista del fragmento.
     * @param inflater Inflater para inflar la vista del fragmento.
     * @param container Contenedor padre del fragmento.
     * @param savedInstanceState Estado guardado del fragmento.
     * @return La vista raíz del fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout utilizando el binding
        binding = FragmentCapturedBinding.inflate(inflater, container, false);
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

        // Inicializa la lista de pokémons capturados (antes de cargar datos)
        capturedPokemons = new ArrayList<>();

        // Inicializar PreferencesHelper
        preferencesHelper = new PreferencesHelper(requireContext());

        // Inicializar PokemonService
        pokemonService = new PokemonService(requireContext());

        // Cargar los datos desde Firebase
        loadPokemonsFirebase();


        // Configurar el RecyclerView
        adapter = new CapturedPokemonAdapter(capturedPokemons, requireContext());
        binding.recyclerViewCaptured.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewCaptured.setAdapter(adapter);

        // Registrar el listener de SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("AppPreferences", requireContext().MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }

    /**
     * Carga los Pokémon capturados desde Firebase Firestore.
     * Utiliza el servicio PokemonService para recuperar los datos y actualizar la lista de Pokémon capturados.
     */
    private void loadPokemonsFirebase() {

        pokemonService.loadCapturedPokemons(new PokemonService.OnPokemonsLoadedCallback() {

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(List<PokemonCaptured> pokemons) {
                capturedPokemons.clear();
                capturedPokemons.addAll(pokemons);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(requireContext(), "@string/error_loading_pokemon" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Método que se llama cuando el fragmento se vuelve visible para el usuario.
     */
    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_captured);
        }
    }

    /**
     * Método que se llama cuando se destruye la vista del fragmento.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Desregistrar el listener de SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("AppPreferences", requireContext().MODE_PRIVATE);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    /**
     * Método que se llama cuando cambia una preferencia en SharedPreferences.
     * @param sharedPreferences Las preferencias compartidas.
     * @param key La clave de la preferencia que cambió.
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("delete_pokemon_enabled")) {
            boolean isEnabled = preferencesHelper.getBoolean("delete_pokemon_enabled", false);
            adapter.setDeletionEnabled(isEnabled);
        }
    }

    /**
     * Método que maneja la eliminación de un Pokémon capturado.
     * @param pokemon El Pokémon capturado que se va a eliminar.
     * @param position La posición del Pokémon en la lista.
     */
    void onDeleteClicked(PokemonCaptured pokemon, int position) {

        // Eliminar del adaptador
        adapter.removePokemon(position);
        // Lógica para eliminar el Pokémon capturado
        ((MainActivity) requireActivity()).onDeleteClicked(pokemon, position);

    }
}
