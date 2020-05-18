package data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class BreedNameList implements Serializable {
    public BreedNameList() {
        items = Collections.emptyList();
    }
    public BreedNameList(List<String> items) {this.items = items;}

    @SerializedName("message")
    private List<String> items;

    public List<String> getItems() {return items;}
    public int size() {return items.size();}
    public String get(int num) {return items.get(num);}
    public void setItems(List<String> items) {
        this.items = items;
    }
}
