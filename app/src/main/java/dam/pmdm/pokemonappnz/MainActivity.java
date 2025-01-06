package dam.pmdm.pokemonappnz;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import dam.pmdm.pokemonappnz.databinding.ActivityMainBinding;

/**
 * MainActivity es la actividad principal de la aplicación que maneja la interfaz de usuario
 * y la navegación entre las diferentes pestañas (Pokémon Capturados, Pokédex, Ajustes).
 * Utiliza Firebase para la autenticación y el almacenamiento de los Pokémon capturados.
 */
public class MainActivity extends AppCompatActivity {
    // ViewBinding para ActivityMainBinding
    private ActivityMainBinding binding;

    // Controlador de navegación
    private NavController navController;

    // Instancia de PokemonService para manejar operaciones con Pokémon
    private PokemonService pokemonService;

    /**
     * Método que se llama cuando la actividad es creada.
     * @param savedInstanceState Estado guardado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializar ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // Configurar contenido de la actividad con el binding
        setContentView(binding.getRoot());

        // Inicializar PokemonService
        pokemonService = new PokemonService(this);

        // Configurar Toolbar como ActionBar
        setSupportActionBar(binding.toolbar);

        // Obtener NavHostFragment desde el layout
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            // Obtener el NavController asociado al NavHostFragment
            navController = NavHostFragment.findNavController(navHostFragment);

            // Configurar el BottomNavigationView con el NavController
            NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

            // Configurar el ActionBar con el NavController
            NavigationUI.setupActionBarWithNavController(this, navController);
        }

        // Configurar manualmente el listener del BottomNavigationView
        binding.bottomNavigationView.setOnItemSelectedListener(this::onMenuSelected);
    }

    /**
     * Método que maneja la selección de ítems en el menú de navegación.
     * @param menuItem El ítem seleccionado.
     * @return true si la navegación fue exitosa, false en caso contrario.
     */
    private boolean onMenuSelected(MenuItem menuItem) {
        // Navegar a los fragmentos según el ítem seleccionado
        if (menuItem.getItemId() == R.id.navigation_captured) {
            navController.navigate(R.id.capturedFragment);
        } else if (menuItem.getItemId() == R.id.navigation_pokedex) {
            navController.navigate(R.id.pokedexFragment);
        } else if (menuItem.getItemId() == R.id.navigation_settings) {
            navController.navigate(R.id.settingsFragment);
        }
        return true;
    }

    /**
     * Método que se llama cuando un Pokémon de la Pokédex es clickeado.
     * @param pokemon El Pokémon capturado.
     * @param view La vista que fue clickeada.
     */
    public void onPokemonClicked(PokemonCaptured pokemon, View view) {
        // Guardar el Pokémon en Firestore usando PokemonService
        pokemonService.savePokemon(pokemon);
    }

    /**
     * Método que maneja la navegación hacia arriba en el ActionBar.
     * @return true si la navegación fue exitosa, false en caso contrario.
     */
    @Override
    public boolean onSupportNavigateUp() {
        // Maneja la navegación hacia arriba
        return super.onSupportNavigateUp() || navController.navigateUp();
    }

    /**
     * Método que se llama cuando un Pokémon capturado es clickeado.
     * @param pokemon El Pokémon capturado.
     * @param view La vista que fue clickeada.
     */
    public void onCapturedPokemonClicked(PokemonCaptured pokemon, View view) {
        // Crear un Bundle para pasar los datos al PokemonDetailFragment
        Bundle bundle = new Bundle();
        bundle.putString("name", pokemon.getName()); // Pasa el nombre del Pokémon
        bundle.putInt("index", pokemon.getId()); // Pasa el índice del Pokémon
        bundle.putString("types", pokemon.getTypesAsString()); // Pasa los tipos del Pokémon
        bundle.putString("image", pokemon.getSprites().getFrontDefault()); // Pasa la URL de la imagen del Pokémon
        bundle.putInt("weight", pokemon.getWeight()); // Pasa el peso del Pokémon
        bundle.putInt("height", pokemon.getHeight()); // Pasa la altura del Pokémon

        // Navegar al PokemonDetailFragment con el Bundle
        Navigation.findNavController(view).navigate(R.id.pokemonDetailFragment, bundle);

        // Mostrar detalles del Pokémon
        Toast.makeText(this, "Clicked on: " + pokemon.getName(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Método que se llama cuando se hace clic en el botón de eliminar un Pokémon capturado.
     * @param currentPokemon El Pokémon capturado actual.
     * @param position La posición del Pokémon en la lista.
     */
    public void onDeleteClicked(PokemonCaptured currentPokemon, int position) {
        // Manejar el evento de clic en el botón de eliminar
        pokemonService.deletePokemon(currentPokemon);
    }
}





