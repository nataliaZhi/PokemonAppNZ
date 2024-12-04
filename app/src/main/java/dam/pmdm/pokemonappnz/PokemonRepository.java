package dam.pmdm.pokemonappnz;

import retrofit2.Callback;

public class PokemonRepository {
    private PokemonApi pokemonApi;

    // Constructor que inicializa la instancia de PokemonApi utilizando RetrofitHelper
    public PokemonRepository() {
        pokemonApi = RetrofitHelper.getRetrofitInstance().create(PokemonApi.class);
    }

    // Método para obtener la lista de Pokémon
    public void getPokemons(Callback<PokemonResponse> callback) {
        // Realiza la petición a la API para obtener la lista de Pokémon y encola la respuesta
        pokemonApi.getPokemons().enqueue(callback);

    }
    // Método para obtener Pokémon Capturado
    public void getPokemonCaptured(String name, Callback<PokemonCaptured> callback) {
        pokemonApi.getPokemonCaptured(name).enqueue(callback);
    }
}
