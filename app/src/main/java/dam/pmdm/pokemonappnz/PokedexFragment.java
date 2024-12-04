package dam.pmdm.pokemonappnz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dam.pmdm.pokemonappnz.databinding.FragmentPokedexBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class PokedexFragment extends Fragment {

    private FragmentPokedexBinding binding;
    private ArrayList<PokemonCaptured> Pokemons;
    private PokedexAdapter adapter;
    private PokemonRepository pokemonRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPokedexBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Inicializa la lista de pokémons capturados (antes de cargar datos)
        Pokemons = new ArrayList<>();
        pokemonRepository = new PokemonRepository();


        // Configurar el RecyclerView
        adapter = new PokedexAdapter(Pokemons, requireContext());
        binding.recyclerViewPokedex.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewPokedex.setAdapter(adapter);

        // Cargar los datos desde la API
        loadPokemonsFromApi();

    }

    private void loadPokemonsFromApi() {

//        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
//        PokemonApi pokemonApi = retrofit.create(PokemonApi.class);
//
//        pokemonApi.getPokemons().enqueue(new Callback<PokemonResponse>() {
//            @Override
//            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    // Obtener los Pokémon desde la respuesta de la API
//                    List<PokemonResponse.PokemonResult> pokemons = response.body().getResults();
//
//                    // Limpiar la lista de Pokémon y agregar los nuevos
//                    Pokemons.clear();
//                    Pokemons.addAll(pokemons);
//
//                    // Notificar al adaptador que los datos han cambiado
//                    adapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PokemonResponse> call, Throwable t) {
//                // Manejar error de red
//                Log.e("PokedexFragment", "Error al obtener datos", t);
//            }
//        });
//
//
//
//        private void loadPokemonsFromApi() {


        pokemonRepository.getPokemons(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call,
                                   Response<PokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PokemonResponse.PokemonResult> results = response.body().getResults();
                    for (PokemonResponse.PokemonResult result : results) {
                        loadPokemonDetails(result.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                // Manejar error de red
                Log.e("PokedexFragment", "Error al obtener datos", t);
            }
        });
    }

    private void loadPokemonDetails(String name) {
        pokemonRepository.getPokemonCaptured(name, new Callback<PokemonCaptured>() {
            @Override
            public void onResponse(Call<PokemonCaptured> call, Response<PokemonCaptured> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonCaptured pokemon = response.body();
                    Pokemons.add(pokemon);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PokemonCaptured> call, Throwable t) {
                // Manejar error de red
                Log.e("PokedexFragment", "Error al obtener detalles del Pokémon", t);
            }
        });
    }


}

