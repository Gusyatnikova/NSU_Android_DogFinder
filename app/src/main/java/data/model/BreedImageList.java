package data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class BreedImageList implements Serializable {
    public BreedImageList() {
        items = Collections.emptyList();
    }
    public BreedImageList(List<String> items) {this.items = items;}

    @SerializedName("message")
    private List<String> items;

    public List<String> getItems() {return items;}
    public String get(int num) {return items.get(num);}
    public int size(){return items.size();}
    public void setItems(List<String> items) {
        this.items = items;
    }
}
