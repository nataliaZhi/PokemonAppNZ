package dam.pmdm.pokemonappnz;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dam.pmdm.pokemonappnz.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    // ViewBinding para ActivityMainBinding
    private ActivityMainBinding binding;

    // Controlador de navegación
    private NavController navController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializar ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // Configurar contenido de la actividad con el binding
        setContentView(binding.getRoot());


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






    // Método que se llama cuando un Pokémon es clickeado
    public void onPokemonClicked(Pokemon pokemon, View view) {
        //  cuando el usuario hace clic en un Pokémon
        // Crear un Bundle para pasar los datos al PokemonDetailFragment
        Bundle bundle = new Bundle();
        bundle.putString("name", pokemon.getName()); // Pasa el nombre del Pokémon
        bundle.putInt("index", pokemon.getIndex()); // Pasa el índice del Pokémon (por ejemplo, #001)
        bundle.putString("types", TextUtils.join(", ", pokemon.getTypes())); // Pasa los tipos del Pokémon
        bundle.putString("image", pokemon.getImageUrl()); // Pasa la URL de la imagen del Pokémon
        bundle.putFloat("weight", pokemon.getWeight()); // Pasa el peso del Pokémon
        bundle.putFloat("height", pokemon.getHeight()); // Pasa la altura del Pokémon


        // Navegar al PokemonDetailFragment con el Bundle
        Navigation.findNavController(view).navigate(R.id.pokemonDetailFragment, bundle);

        //  mostrar detalles del Pokémon
        Toast.makeText(this, "Clicked on: " + pokemon.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Maneja la navegación hacia arriba
        return super.onSupportNavigateUp() || navController.navigateUp();
    }



    }







