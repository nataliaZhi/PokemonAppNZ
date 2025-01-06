package dam.pmdm.pokemonappnz;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import dam.pmdm.pokemonappnz.databinding.FragmentCapturedBinding;
import java.util.ArrayList;



/**
 * CapturedFragment es la clase que maneja la pestaña de Pokémon capturados de la aplicación.
 * Muestra una lista de Pokémon que han sido capturados y almacenados en Firebase Firestore.
 */
public class CapturedFragment extends Fragment {

    // ViewBinding para FragmentCapturedBinding
    private FragmentCapturedBinding binding;

    // Lista de Pokémon capturados
    private ArrayList<PokemonCaptured> capturedPokemons;

    // Adaptador para el RecyclerView
    private CapturedPokemonAdapter adapter;

    /**
     * Método que se llama cuando se crea la vista del fragmento.
     * @param inflater Inflater para inflar la vista del fragmento.
     * @param container Contenedor padre del fragmento.
     * @param savedInstanceState Estado guardado del fragmento.
     * @return La vista raíz del fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout utilizando el binding
        binding = FragmentCapturedBinding.inflate(inflater, container, false);
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
        capturedPokemons = new ArrayList<>();

        // Cargar los datos desde Firebase
        loadPokemonsFirebase();

        // Configurar el RecyclerView
        adapter = new CapturedPokemonAdapter(capturedPokemons, requireContext());
        binding.recyclerViewCaptured.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewCaptured.setAdapter(adapter);
    }

    /**
     * Carga la lista de Pokémon capturados desde Firebase Firestore.
     * Los datos se obtienen de la colección "captured_pokemon" y se añaden a la lista capturedPokemons.
     */
    @SuppressLint("NotifyDataSetChanged")
    private void loadPokemonsFirebase() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("captured_pokemon")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            PokemonCaptured pokemon = document.toObject(PokemonCaptured.class);
                            capturedPokemons.add(pokemon);
                        }
                        // Notifica al adaptador que los datos han cambiado
                        adapter.notifyDataSetChanged();
                    } else {
                        // Manejar el error
                        Toast.makeText(getContext(), "Error al cargar los Pokémon: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_captured);
        }
    }
}