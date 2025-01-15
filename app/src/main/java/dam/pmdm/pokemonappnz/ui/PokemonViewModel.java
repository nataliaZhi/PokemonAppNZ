package dam.pmdm.pokemonappnz.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import dam.pmdm.pokemonappnz.data.PokemonCaptured;

/**
 * PokemonViewModel es una clase ViewModel que gestiona la lista de Pokémon capturados.
 * Utiliza MutableLiveData para almacenar y observar la lista de Pokémon capturados.
 */
public class PokemonViewModel extends ViewModel {
    private final MutableLiveData<List<PokemonCaptured>> capturedPokemons = new MutableLiveData<>(new ArrayList<>());

    /**
     * Obtiene la lista de Pokémon capturados como LiveData.
     * @return LiveData que contiene la lista de Pokémon capturados.
     */
    public LiveData<List<PokemonCaptured>> getCapturedPokemons() {
        return capturedPokemons;
    }

    /**
     * Añade un Pokémon capturado a la lista.
     * @param pokemon El Pokémon que se ha capturado.
     */
    public void addCapturedPokemon(PokemonCaptured pokemon) {
        // Obtiene la lista actual de Pokémon capturados
        List<PokemonCaptured> currentList = new ArrayList<>(capturedPokemons.getValue());
        // Añade el nuevo Pokémon a la lista
        currentList.add(pokemon);
        // Actualiza el valor de LiveData con la nueva lista
        capturedPokemons.setValue(currentList);
    }

    /**
     * Elimina un Pokémon capturado de la lista.
     * @param pokemon El Pokémon que se desea eliminar.
     */
    public void removeCapturedPokemon(PokemonCaptured pokemon) {
        // Obtiene la lista actual de Pokémon capturados
        List<PokemonCaptured> currentList = new ArrayList<>(capturedPokemons.getValue());
        // Elimina el Pokémon de la lista
        currentList.remove(pokemon);
        // Actualiza el valor de LiveData con la nueva lista
        capturedPokemons.setValue(currentList);
    }

    /**
     * Verifica si un Pokémon está capturado comparando nombres.
     * @param pokemonName El nombre del Pokémon.
     * @return true si el Pokémon está capturado, false de lo contrario.
     */
    public boolean isPokemonCaptured(String pokemonName) {
        List<PokemonCaptured> currentList = capturedPokemons.getValue();
        for (PokemonCaptured pokemon : currentList) {
            if (pokemon.getName().equals(pokemonName)) {
                return true;
            }
        }
        return false;
    }
}