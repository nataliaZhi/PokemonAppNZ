package dam.pmdm.pokemonappnz;

import java.util.List;

public class Pokemon {

    private final String name;
    private final int index;
    private final String imageUrl;
    private List<String> types;
    private final float weight;
    private final float height;

    public Pokemon(String name, int index, String imageUrl, List<String> types, float weight, float height) {
        this.name = name;
        this.index = index;
        this.imageUrl = imageUrl;
        this.types = types;
        this.weight = weight;
        this.height = height;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getTypes() {
        return types;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }
}
