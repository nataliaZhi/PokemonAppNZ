package dam.pmdm.pokemonappnz.auth;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import dam.pmdm.pokemonappnz.ui.MainActivity;
import dam.pmdm.pokemonappnz.R;

/**
 * LoginActivity maneja el inicio de sesión de los usuarios utilizando FirebaseUI.
 * Extiende AppCompatActivity.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Método llamado cuando se crea la actividad.
     *
     * @param savedInstanceState Si la actividad se está re-inicializando después de haber
     * sido previamente terminada, este Bundle contiene los datos más recientes suministrados
     * en onSaveInstanceState(Bundle). De lo contrario, está nulo.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Inicia el proceso de inicio de sesión utilizando FirebaseUI.
     * Configura los proveedores de autenticación disponibles (Email y Google) y lanza
     * el intent de inicio de sesión.
     */
    private void startSignIn() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.logo_pokemon)
                .setTheme(R.style.Theme_PokemonAppNZ)
                .build();
        signInLauncher.launch(signInIntent);
    }

    /**
     * Lanzador de resultado de actividad para manejar el resultado del inicio de sesión.
     */
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),

            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

    /**
     * Maneja el resultado del inicio de sesión.
     *
     * @param result El resultado de la autenticación de FirebaseUI.
     */
    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // El usuario ha iniciado sesión correctamente
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            goToMainActivity();
        } else {
            // Error en el inicio de sesión
            Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método llamado cuando la actividad se vuelve visible para el usuario.
     * Verifica si el usuario ya está autenticado y, de ser así, lo redirige a
     * la actividad principal.
     */
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            goToMainActivity();
        } else {
            startSignIn();
        }
    }

    /**
     * Redirige a la actividad principal.
     */
    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}