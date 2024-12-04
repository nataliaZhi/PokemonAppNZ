package dam.pmdm.pokemonappnz;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface PokemonApi {
    @GET("pokemon?offset=0&limit=150")
    Call<PokemonResponse> getPokemons();

    @GET("pokemon/{name}")
    Call<PokemonCaptured> getPokemonCaptured(@Path("name") String name);
}

