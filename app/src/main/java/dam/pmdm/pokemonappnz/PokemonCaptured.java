package dam.pmdm.pokemonappnz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Clase que representa un Pokémon capturado.
 */
public class PokemonCaptured {

    // Nombre del Pokémon
    @SerializedName("name")
    private String name;

    // ID del Pokémon
    @SerializedName("id")
    private int id;

    // Imágenes del Pokémon
    @SerializedName("sprites")
    private Sprites sprites;

    // Tipos del Pokémon
    @SerializedName("types")
    private List<Type> types;

    // Peso del Pokémon
    @SerializedName("weight")
    private int weight;

    // Altura del Pokémon
    @SerializedName("height")
    private int height;

    // Estado de captura del Pokémon
    @SerializedName("isCaptured")
    private boolean isCaptured;

    /**
     * Constructor vacío necesario para Firebase.
     */
    public PokemonCaptured() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param name        el nombre del Pokémon
     * @param id          el ID del Pokémon
     * @param sprites     las imágenes del Pokémon
     * @param types       los tipos del Pokémon
     * @param weight      el peso del Pokémon
     * @param height      la altura del Pokémon
     * @param isCaptured  el estado de captura del Pokémon
     */
    public PokemonCaptured(String name, int id, Sprites sprites, List<Type> types, int weight, int height, boolean isCaptured) {
        this.name = name;
        this.id = id;
        this.sprites = sprites;
        this.types = types;
        this.weight = weight;
        this.height = height;
        this.isCaptured = isCaptured;
    }

    // Getters y Setters

    /**
     * Obtiene el nombre del Pokémon.
     *
     * @return el nombre del Pokémon
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del Pokémon.
     *
     * @param name el nombre del Pokémon
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el ID del Pokémon.
     *
     * @return el ID del Pokémon
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del Pokémon.
     *
     * @param id el ID del Pokémon
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene las imágenes del Pokémon.
     *
     * @return las imágenes del Pokémon
     */
    public Sprites getSprites() {
        return sprites;
    }

    /**
     * Obtiene los tipos del Pokémon.
     *
     * @return los tipos del Pokémon
     */
    public List<Type> getTypes() {
        return types;
    }

    /**
     * Obtiene el peso del Pokémon.
     *
     * @return el peso del Pokémon
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Obtiene la altura del Pokémon.
     *
     * @return la altura del Pokémon
     */
    public int getHeight() {
        return height;
    }

    /**
     * Establece la altura del Pokémon.
     *
     * @param height la altura del Pokémon
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Obtiene el estado de captura del Pokémon.
     *
     * @return el estado de captura del Pokémon
     */
    public boolean isCaptured() {
        return isCaptured;
    }

    /**
     * Establece el estado de captura del Pokémon.
     *
     * @param captured el estado de captura del Pokémon
     */
    public void setCaptured(boolean captured) {
        isCaptured = captured;
    }

    /**
     * Obtiene todos los tipos de Pokémon como una cadena.
     *
     * @return una cadena con todos los tipos de Pokémon
     */
    public String getTypesAsString() {
        StringBuilder typesString = new StringBuilder();
        for (Type type : types) {
            if (typesString.length() > 0) {
                typesString.append(", ");
            }
            typesString.append(type.getTypeInfo().getName());
        }
        return typesString.toString();
    }

    /**
     * Clase anidada para las imágenes del Pokémon.
     */
    public static class Sprites {
        // URL de la imagen frontal predeterminada
        @SerializedName("front_default")
        private String frontDefault;

        /**
         * Obtiene la URL de la imagen frontal predeterminada.
         *
         * @return la URL de la imagen frontal predeterminada
         */
        public String getFrontDefault() {
            return frontDefault;
        }
    }

    /**
     * Clase anidada para los tipos del Pokémon.
     */
    public static class Type {
        // Información del tipo
        @SerializedName("type")
        private TypeInfo typeInfo;

        /**
         * Obtiene la información del tipo.
         *
         * @return la información del tipo
         */
        public TypeInfo getTypeInfo() {
            return typeInfo;
        }

        /**
         * Clase anidada para la información del tipo.
         */
        public static class TypeInfo {
            // Nombre del tipo
            @SerializedName("name")
            private String name;

            /**
             * Obtiene el nombre del tipo.
             *
             * @return el nombre del tipo
             */
            public String getName() {
                return name;
            }

            /**
             * Establece el nombre del tipo.
             *
             * @param name el nombre del tipo
             */
            public void setName(String name) {
                this.name = name;
            }
        }
    }
}