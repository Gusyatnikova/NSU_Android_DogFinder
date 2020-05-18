package com.example.dogfinder.presentation.BreedList;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import data.model.BreedList;

public class BreedListViewModelFactory implements ViewModelProvider.Factory {
    private String query;
    private BreedList breedList;

    public BreedListViewModelFactory(String query, BreedList breedList) {
        this.query = query;
        this.breedList = breedList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BreedListViewModel(query, breedList);
    }
}
