package dam.pmdm.pokemonappnz;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * PokemonApi es una interfaz para definir las peticiones a la API de Pokémon usando Retrofit.
 * Proporciona métodos para obtener la lista de Pokémon y los detalles de un Pokémon capturado.
 */
public interface PokemonApi {

    /**
     * Realiza una petición GET para obtener la lista de Pokémon con un límite de 150.
     * @return Un objeto Call que devuelve una respuesta de tipo PokemonResponse.
     */
    @GET("pokemon?offset=0&limit=150")
    Call<PokemonResponse> getPokemons();

    /**
     * Realiza una petición GET para obtener los detalles de un Pokémon capturado.
     * @param name El nombre del Pokémon.
     * @return Un objeto Call que devuelve una respuesta de tipo PokemonCaptured.
     */
    @GET("pokemon/{name}")
    Call<PokemonCaptured> getPokemonCaptured(@Path("name") String name);
}
