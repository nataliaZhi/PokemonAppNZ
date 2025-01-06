package dam.pmdm.pokemonappnz;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;


public class PokemonService {

    private FirebaseFirestore db;
    private Context context;

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
                    Toast.makeText(context, "Pokémon guardado exitosamente.", Toast.LENGTH_SHORT).show();
                    // Marcar el Pokémon como capturado solo si se guarda exitosamente
                    pokemon.setCaptured(true);
                })
                .addOnFailureListener(e -> {
                    // Mostrar mensaje de error
                    Toast.makeText(context, "Error al guardar el Pokémon: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }




    // Eliminar un Pokémon capturado
    public void deletePokemon(PokemonCaptured pokemon) {
        db.collection("captured_pokemon")
                .document(String.valueOf(pokemon.getId()))
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(context, "Pokémon eliminado exitosamente.", Toast.LENGTH_SHORT).show();
                    // Marcar el Pokémon como capturado solo si se guarda exitosamente
                    pokemon.setCaptured(false);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Error al eliminar el Pokémon: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }


    // Método para obtener los detalles de un Pokémon por ID
    public Task<PokemonCaptured> getPokemonById(int pokemonId) {
        return db.collection("captured_pokemon").document(String.valueOf(pokemonId))
                .get()
                .continueWith(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        DocumentSnapshot document = task.getResult();
                        return document.toObject(PokemonCaptured.class);
                    } else {
                        throw task.getException() != null ? task.getException() : new Exception("Error al cargar los detalles del Pokémon");
                    }
                });
    }
}



