package dam.pmdm.pokemonappnz.data;

import android.content.Context;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.List;

import dam.pmdm.pokemonappnz.R;


public class PokemonService {

    private final FirebaseFirestore db;
    private final Context context;

    /**
     * Constructor que inicializa la instancia de FirebaseFirestore y el contexto.
     *
     * @param context El contexto de la actividad o aplicación.
     */
    public PokemonService(Context context) {
        this.db = FirebaseFirestore.getInstance();
        this.context = context;
    }

    /**
     * Guarda un Pokémon capturado en Firestore y muestra un mensaje de éxito o error.
     *
     * @param pokemon El Pokémon capturado a guardar.
     */
    public void savePokemon(PokemonCaptured pokemon) {
        db.collection("captured_pokemon")
                .document(String.valueOf(pokemon.getId()))
                .set(pokemon, SetOptions.merge())
                .addOnSuccessListener(aVoid -> {
                    // Mostrar mensaje de éxito
                    Toast.makeText(context, context.getString(R.string.pokemon_saved_successfully), Toast.LENGTH_SHORT).show();
                    // Marcar el Pokémon como capturado solo si se guarda exitosamente
                    pokemon.setCaptured(true);
                })
                .addOnFailureListener(e -> {
                    // Mostrar mensaje de error
                    Toast.makeText(context, context.getString(R.string.pokemon_save_error, e.getMessage()), Toast.LENGTH_SHORT).show();});
    }




    // Eliminar un Pokémon capturado
    public void deletePokemon(PokemonCaptured pokemon) {
        db.collection("captured_pokemon")
                .document(String.valueOf(pokemon.getId()))
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(context, context.getString(R.string.pokemon_deleted_successfully), Toast.LENGTH_SHORT).show();
                    // Marcar el Pokémon como capturado solo si se guarda exitosamente
                    pokemon.setCaptured(false);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, context.getString(R.string.pokemon_delete_error, e.getMessage()), Toast.LENGTH_SHORT).show();
                });
    }
    /**
     * Carga la lista de Pokémon capturados desde Firestore y llama al callback con los datos.
     *
     * @param callback El callback que se llamará con la lista de Pokémon capturados.
     */
    public void loadCapturedPokemons(OnPokemonsLoadedCallback callback) {
        db.collection("captured_pokemon")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<PokemonCaptured> pokemons = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            PokemonCaptured pokemon = document.toObject(PokemonCaptured.class);
                            pokemons.add(pokemon);
                        }
                        callback.onSuccess(pokemons);
                    } else {
                        callback.onError(task.getException());
                    }
                });
    }

    /**
     * Interfaz para manejar los callbacks de carga de Pokémon.
     */
    public interface OnPokemonsLoadedCallback {
        void onSuccess(List<PokemonCaptured> pokemons);
        void onError(Exception e);
    }



}



