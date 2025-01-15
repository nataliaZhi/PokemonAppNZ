package dam.pmdm.pokemonappnz.data;

import java.util.List;

/**
 * PokemonResponse es una clase que representa la respuesta de la API de Pokémon
 * al obtener una lista de Pokémon. Contiene una lista de objetos PokemonResult.
 */
public class PokemonResponse {

    // Lista de resultados de Pokémon
    private List<PokemonResult> results;

    /**
     * Obtiene la lista de resultados de Pokémon.
     * @return La lista de resultados de Pokémon.
     */
    public List<PokemonResult> getResults() {
        return results;
    }

    /**
     * Establece la lista de resultados de Pokémon.
     * @param results La lista de resultados de Pokémon.
     */
    public void setResults(List<PokemonResult> results) {
        this.results = results;
    }

    /**
     * PokemonResult es una clase estática interna que representa un Pokémon individual
     * en la respuesta de la API. Contiene el nombre y la URL del Pokémon.
     */
    public static class PokemonResult {

        // Nombre del Pokémon
        private String name;

        // URL del Pokémon
        private String url;

        /**
         * Obtiene el nombre del Pokémon.
         * @return El nombre del Pokémon.
         */
        public String getName() {
            return name;
        }

        /**
         * Establece el nombre del Pokémon.
         * @param name El nombre del Pokémon.
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Obtiene la URL del Pokémon.
         * @return La URL del Pokémon.
         */
        public String getUrl() {
            return url;
        }

        /**
         * Establece la URL del Pokémon.
         * @param url La URL del Pokémon.
         */
        public void setUrl(String url) {
            this.url = url;
        }
    }
}