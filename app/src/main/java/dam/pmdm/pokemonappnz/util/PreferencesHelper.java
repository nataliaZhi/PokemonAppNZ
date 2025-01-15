package dam.pmdm.pokemonappnz.util;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    // Claves utilizadas para almacenar y recuperar las preferencias desde SharedPreferences
    private static final String PREF_NAME = "AppPreferences";
    private static final String LANGUAGE_KEY = "language";
    private static final String DELETE_POKEMON_ENABLED_KEY = "delete_pokemon_enabled";

    private static SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;

    // Constructor
    public PreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /**
     * Guarda el idioma seleccionado por el usuario en SharedPreferences.
     * Este método permite almacenar el código del idioma en las preferencias de la aplicación.
     *
     * @param languageCode El código del idioma (por ejemplo, "es" para español, "en" para inglés).
     */
    public void saveLanguage(String languageCode) {
        editor.putString(LANGUAGE_KEY, languageCode); // Guardar el código del idioma
        editor.apply(); // Aplicar los cambios
    }

    /**
     * Obtiene el idioma almacenado en SharedPreferences.
     * Si no se ha guardado ningún idioma, devuelve "es" por defecto.
     *
     * @return El código del idioma almacenado, o "es" si no se ha guardado ningún valor.
     */
    public static String getSavedLanguage() {


        // Devolver el idioma almacenado, "es" (español) como valor por defecto
        return sharedPreferences.getString(LANGUAGE_KEY, "es");
    }

    /**
     * Guarda la preferencia de eliminación de Pokémon en SharedPreferences.
     * Este método permite almacenar si la opción de eliminar Pokémon está habilitada o no.
     *
     * @param isEnabled true si la eliminación de Pokémon está habilitada, false en caso contrario.
     */
    public void saveDeletePokemonEnabled(boolean isEnabled) {
        editor.putBoolean(DELETE_POKEMON_ENABLED_KEY, isEnabled); // Guardar la preferencia
        editor.apply(); // Aplicar los cambios
    }




    /**
     * Método para obtener un valor booleano de SharedPreferences.
     * Si no se ha guardado ningún valor, devuelve el valor por defecto proporcionado.
     *
     * @param key La clave de la preferencia.
     * @param defaultValue El valor por defecto si la clave no existe.
     * @return El valor booleano almacenado, o el valor por defecto si no se ha guardado ninguno.
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }
}
