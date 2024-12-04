package dam.pmdm.pokemonappnz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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

        // Constructor vacío necesario para Firebase
        public PokemonCaptured() {
        }

        // Constructor con parámetros
        public PokemonCaptured(String name, int id, Sprites sprites, List<Type> types, int weight, int height) {
            this.name = name;
            this.id = id;
            this.sprites = sprites;
            this.types = types;
            this.weight = weight;
            this.height = height;
        }

        // Getters y Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    public Sprites getSprites() {
        return sprites;
    }

        public void setSprites(Sprites sprites) {
            this.sprites = sprites;
        }

        public List<Type> getTypes() {
            return types;
        }

        public void setTypes(List<Type> types) {
            this.types = types;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        public String toString() {
            return "PokemonCaptured{" + "name='" + name + '\'' + ", id=" + id + ", sprites=" + sprites + ", types=" + types + ", weight=" + weight + ", height=" + height + '}';
        }

        // Clase anidada para las imágenes del Pokémon
        public static class Sprites {
            // URL de la imagen frontal predeterminada
            @SerializedName("front_default")
            private String frontDefault;

            // Getters y Setters
            public String getFrontDefault() {
                return frontDefault;
            }

            public void setFrontDefault(String frontDefault) {
                this.frontDefault = frontDefault;
            }
        }

        // Clase anidada para los tipos del Pokémon
        public static class Type {
            // Información del tipo
            @SerializedName("type")
            private Type.TypeInfo typeInfo;

            // Getters y Setters
            public TypeInfo getTypeInfo() {
                return typeInfo;
            }

            public void setTypeInfo(TypeInfo typeInfo) {
                this.typeInfo = typeInfo;
            }

            // Clase anidada para la información del tipo
            public static class TypeInfo {
                // Nombre del tipo
                @SerializedName("name")
                private String name;

                // Getters y Setters
                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }

//
//    private final String name;
//    private final int index;
//    private final String imageUrl;
//    private List<String> types;
//    private final float weight;
//    private final float height;
//
//    public PokemonCaptured(String name, int index, String imageUrl, List<String> types, float weight, float height) {
//        this.name = name;
//        this.index = index;
//        this.imageUrl = imageUrl;
//        this.types = types;
//        this.weight = weight;
//        this.height = height;
//    }
//
//    // Getters y Setters
//    public String getName() {
//        return name;
//    }
//
//    public int getIndex() {
//        return index;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public List<String> getTypes() {
//        return types;
//    }
//
//    public float getWeight() {
//        return weight;
//    }
//
//    public float getHeight() {
//        return height;
//    }
//}
