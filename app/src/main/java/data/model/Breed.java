package data.model;

public class Breed{
    private String name;
    private String image;

    public Breed(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getBreedName() {
        return name;
    }
    public String getImageUrl() {
        return image;
    }
}
