package dam.pmdm.pokemonappnz.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dam.pmdm.pokemonappnz.data.PokemonCaptured;
import dam.pmdm.pokemonappnz.data.PokemonResponse;
import dam.pmdm.pokemonappnz.R;
import dam.pmdm.pokemonappnz.data.PokemonRepository;
import dam.pmdm.pokemonappnz.databinding.FragmentPokedexBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * PokedexFragment es la clase que maneja la pestaña Pokédex de la aplicación.
 * Muestra una lista de Pokémon obtenidos de la API y permite agregar Pokémon capturados a la lista personal.
 */
public class PokedexFragment extends Fragment {

    // ViewBinding para FragmentPokedexBinding
    private FragmentPokedexBinding binding;

    // Lista de Pokémon capturados
    private ArrayList<PokemonCaptured> Pokemons;

    // Adaptador para el RecyclerView
    private PokedexAdapter adapter;

    // Repositorio para obtener datos de Pokémon
    private PokemonRepository pokemonRepository;

    // Instancia del ViewModel
    private PokemonViewModel pokemonViewModel;

    /**
     * Método que se llama cuando la vista del fragmento es creada.
     * @param inflater Inflater para inflar la vista del fragmento.
     * @param container Contenedor padre del fragmento.
     * @param savedInstanceState Estado guardado del fragmento.
     * @return La vista raíz del fragmento.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPokedexBinding.inflate(inflater, container, false);
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
        Pokemons = new ArrayList<>();

        pokemonRepository = new PokemonRepository();

        pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);

        // Configurar el RecyclerView
        adapter = new PokedexAdapter(Pokemons, requireContext(), pokemonViewModel);
        binding.recyclerViewPokedex.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recyclerViewPokedex.setAdapter(adapter);

        // Cargar los datos desde la API
        loadPokemonsFromApi();


    }

    /**
     * Método que carga los Pokémon desde la API utilizando el repositorio.
     */
    private void loadPokemonsFromApi() {
        pokemonRepository.getPokemons(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResponse> call,
                                   @NonNull Response<PokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PokemonResponse.PokemonResult> results = response.body().getResults();
                    for (PokemonResponse.PokemonResult result : results) {
                        loadPokemonDetails(result.getName());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<PokemonResponse> call, @NonNull Throwable t) {
                // Manejar error de red
                Toast.makeText(requireContext(), getString(R.string.error_loading_data), Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     * Método que carga los detalles de un Pokémon específico desde la API y lo agrega a la lista.
     * @param name El nombre del Pokémon.
     */
    private void loadPokemonDetails(String name) {
        pokemonRepository.getPokemonCaptured(name, new Callback<PokemonCaptured>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<PokemonCaptured> call, @NonNull Response<PokemonCaptured> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonCaptured pokemon = response.body();
                    // Verificar si el Pokémon está capturado
                    boolean isCaptured = pokemonViewModel.isPokemonCaptured(pokemon.getName());
                    pokemon.setCaptured(isCaptured);


                    Pokemons.add(pokemon);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PokemonCaptured> call, @NonNull Throwable t) {
                // Manejar error de red
                Toast.makeText(requireContext(), getString(R.string.error_loading_pokemon), Toast.LENGTH_SHORT).show();
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
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_pokedex);
        }
    }
}