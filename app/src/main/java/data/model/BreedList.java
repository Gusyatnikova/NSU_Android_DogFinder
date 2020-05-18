package data.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class BreedList implements Serializable {

    private BreedNameList namesList;
    private BreedImageList imagesList;
    private List<Breed> items;

    public BreedList() {
        items = Collections.emptyList();
    }
    public BreedList(BreedNameList names, BreedImageList images) {this.namesList = names; this.imagesList = images;}

    public void setLists(BreedNameList names, BreedImageList images) {
        for (int i = 0; i < images.size(); i++) {
            items.add(new Breed(names.get(i), images.get(i)));
        }
    }

    public List<Breed> getItems() {return items;}
    public void setItems(List<Breed> items) {
        this.items = items;
    }
    public void setBreedNamesList(BreedNameList list) {this.namesList = list;}
    public void setBreedImagesList(BreedImageList list) {this.imagesList = list;}
    public int getNamesListSize() {return namesList.size();}
    public BreedNameList getBreedNameList() {return this.namesList;}
}
