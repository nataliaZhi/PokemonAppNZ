package dam.pmdm.pokemonappnz.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;
import com.google.firebase.auth.FirebaseAuth;
import dam.pmdm.pokemonappnz.util.PreferencesHelper;
import dam.pmdm.pokemonappnz.R;
import dam.pmdm.pokemonappnz.auth.LoginActivity;

/**
 * Fragmento que muestra las configuraciones de la aplicación.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    // Helper para manejar las preferencias
    private PreferencesHelper preferencesHelper;

    /**
     * Método que se llama cuando se crean las preferencias del fragmento.
     * @param savedInstanceState Estado guardado del fragmento.
     * @param rootKey Clave raíz para las preferencias.
     */
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Cargar el archivo de preferencias desde recursos XML
        setPreferencesFromResource(R.xml.preferences, rootKey);

        // Inicializar el PreferencesHelper
        preferencesHelper = new PreferencesHelper(requireContext());

        // Configurar la preferencia de idioma
        ListPreference languagePreference = findPreference("language_preference");
        if (languagePreference != null) {
            languagePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                // Guardar el idioma seleccionado y cambiar el idioma de la aplicación
                preferencesHelper.saveLanguage(newValue.toString());
                changeLanguage(newValue.toString());
                return true;
            });
        }

        // Configurar la preferencia para habilitar/deshabilitar la eliminación de Pokémon
        SwitchPreferenceCompat deletePokemonSwitch = findPreference("delete_pokemon_enabled");
        if (deletePokemonSwitch != null) {
            deletePokemonSwitch.setOnPreferenceChangeListener((preference, newValue) -> {
                // Guardar la preferencia de eliminación de Pokémon
                preferencesHelper.saveDeletePokemonEnabled((boolean) newValue);
                return true;
            });
        }

        // Configurar la acción para "Acerca de"
        Preference aboutPreference = findPreference("about");
        if (aboutPreference != null) {
            aboutPreference.setOnPreferenceClickListener(preference -> {
                showAboutDialog();
                return true;
            });
        }

        // Configurar la acción para cerrar sesión
        Preference logoutPreference = findPreference("logout");
        if (logoutPreference != null) {
            logoutPreference.setOnPreferenceClickListener(preference -> {
                confirmLogout();
                return true;
            });
        }
    }

    /**
     * Cambia el idioma de la aplicación.
     * @param languageCode Código del idioma seleccionado.
     */
    private void changeLanguage(String languageCode) {

        MainActivity mainActivity = (MainActivity) requireActivity();
        mainActivity.setLocale(languageCode);

    }



    /**
     * Muestra un diálogo "Acerca de".
     */
    private void showAboutDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.about))
                .setMessage(getString(R.string.developer_name) + "\n" + getString(R.string.version))
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    /**
     * Muestra un diálogo de confirmación para cerrar sesión.
     */
    private void confirmLogout() {
        new AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.logout_title))
                .setMessage(getString(R.string.logout_message))
                .setPositiveButton(getString(R.string.yes), (dialog, which) -> logout())
                .setNegativeButton(getString(R.string.no), null)
                .show();
    }

    /**
     * Cierra la sesión del usuario y redirige a la pantalla de inicio de sesión.
     */
    private void logout() {
        // Cerrar sesión de Firebase
        FirebaseAuth.getInstance().signOut();

        // Redirigir a la pantalla de inicio de sesión
        Intent intent = new Intent(requireContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        requireActivity().finish();
    }

    /**
     * Método que se llama cuando el fragmento se vuelve visible para el usuario.
     */
    @Override
    public void onStart() {
        super.onStart();
        // Cambiar el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_settings);
        }
    }
}
