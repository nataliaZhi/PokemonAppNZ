package dam.pmdm.pokemonappnz;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Cargar el archivo de preferencias
        setPreferencesFromResource(R.xml.preferences, rootKey);
// Cambiar el idioma según la preferencia seleccionada
        ListPreference languagePreference = findPreference("language_preference");
        if (languagePreference != null) {
            languagePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                changeLanguage(newValue.toString());
                return true;
            });
        }

        // Habilitar/deshabilitar eliminación de Pokémon
        SwitchPreferenceCompat deletePokemonSwitch = findPreference("delete_pokemon_enabled");
        if (deletePokemonSwitch != null) {
            deletePokemonSwitch.setOnPreferenceChangeListener((preference, newValue) -> {
                boolean isEnabled = (boolean) newValue;
                updatePokemonDeletionSetting(isEnabled);
                return true;
            });
        }

        // Acciones para "Acerca de"
        Preference aboutPreference = findPreference("about");
        if (aboutPreference != null) {
            aboutPreference.setOnPreferenceClickListener(preference -> {
                showAboutDialog();
                return true;
            });
        }

        // Cerrar sesión
        Preference logoutPreference = findPreference("logout");
        if (logoutPreference != null) {
            logoutPreference.setOnPreferenceClickListener(preference -> {
                logout();
                return true;
            });
        }
    }

    private void changeLanguage(String languageCode) {
        // Cambiar idioma
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        requireActivity().getResources().updateConfiguration(config, requireActivity().getResources().getDisplayMetrics());

        // Reiniciar la actividad para aplicar el nuevo idioma
        requireActivity().recreate();
    }

    private void updatePokemonDeletionSetting(boolean isEnabled) {
        // Guardar el estado de la preferencia en SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("delete_pokemon_enabled", isEnabled);
        editor.apply();
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.about))
                .setMessage(getString(R.string.developer_name) + "\n" + getString(R.string.version))
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    private void logout() {
        // Cerrar sesión de Firebase
//        FirebaseAuth.getInstance().signOut();
//
//        // Redirigir a la pantalla de inicio de sesión
//        Intent intent = new Intent(requireContext(), LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//        requireActivity().finish();
    }
}