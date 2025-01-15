package dam.pmdm.pokemonappnz.util;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitHelper es una clase de utilidad para crear y gestionar la instancia de Retrofit.
 * Utiliza un interceptor para registrar las peticiones y respuestas HTTP.
 */
public class RetrofitHelper {

    // URL base de la API de Pokémon
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

    // Instancia de Retrofit
    private static Retrofit retrofit;

    /**
     * Obtiene la instancia de Retrofit.
     * Si la instancia no ha sido creada, se configura y se crea.
     * @return La instancia de Retrofit.
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            // Creamos un interceptor y le indicamos el log level a usar
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Asociamos el interceptor a las peticiones
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            // Creamos la instancia de Retrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()) // <-- set log level
                    .build();
        }
        return retrofit;
    }
}
