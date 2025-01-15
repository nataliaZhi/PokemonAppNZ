package dam.pmdm.pokemonappnz.data;

import dam.pmdm.pokemonappnz.util.RetrofitHelper;
import retrofit2.Callback;
/**
 * PokemonRepository es una clase de repositorio que maneja las llamadas a la API de Pokémon.
 * Utiliza Retrofit para realizar las peticiones a la API y obtener los datos de los Pokémon.
 */
public class PokemonRepository {

    // Interfaz de la API de Pokémon
    private final PokemonApi pokemonApi;

    /**
     * Constructor que inicializa la instancia de PokemonApi utilizando RetrofitHelper.
     */
    public PokemonRepository() {
        pokemonApi = RetrofitHelper.getRetrofitInstance().create(PokemonApi.class);
    }

    /**
     * Método para obtener la lista de Pokémon.
     * @param callback Callback para manejar la respuesta de la API.
     */
    public void getPokemons(Callback<PokemonResponse> callback) {
        // Realiza la petición a la API para obtener la lista de Pokémon y encola la respuesta
        pokemonApi.getPokemons().enqueue(callback);
    }

    /**
     * Método para obtener los detalles de un Pokémon capturado.
     * @param name El nombre del Pokémon.
     * @param callback Callback para manejar la respuesta de la API.
     */
    public void getPokemonCaptured(String name, Callback<PokemonCaptured> callback) {
        // Realiza la petición a la API para obtener los detalles del Pokémon capturado y encola la respuesta
        pokemonApi.getPokemonCaptured(name).enqueue(callback);
    }
}