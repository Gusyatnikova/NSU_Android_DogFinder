package com.example.dogfinder.presentation.BreedList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import data.model.Breed;
import data.model.BreedList;

public class BreedListViewModel extends ViewModel {
    private BreedList breedList;
    private String query;

    public LiveData<String> observeHeaderLiveData(){return headerLiveData;}
    private MutableLiveData<String> headerLiveData = new MutableLiveData<>();

    public LiveData<List<Breed>> observeBreedListLiveData() {return breedListLiveData;}
    private MutableLiveData<List<Breed>> breedListLiveData = new MutableLiveData<>();

    BreedListViewModel(String query, BreedList breedList) {
        this.query = query;
        this.breedList = breedList;
        if (this.breedList != null) {
            breedListLiveData.setValue(breedList.getItems());
        }
    }
}
