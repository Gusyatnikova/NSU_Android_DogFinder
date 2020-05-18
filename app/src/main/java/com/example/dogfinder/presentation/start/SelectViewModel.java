package com.example.dogfinder.presentation.start;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dogfinder.presentation.TemplateApplication;

import data.model.BreedImageList;
import data.model.BreedList;
import data.model.BreedNameList;
import data.network.DogApi;
import data.network.DogApiClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SelectViewModel extends ViewModel {
    private DogApi api;

    private BreedList breedList = new BreedList();
    //private BreedNameList breedNameList = new BreedNameList();
    //private BreedImageList breedImageList = new BreedImageList();

    public LiveData<String> observeErrorLiveData() { return errorLiveData; }
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>("");

    public LiveData<BreedNameList> observeBreedNameListLiveData() {return breedNameListLiveData;}
    private MutableLiveData<BreedNameList> breedNameListLiveData = new MutableLiveData<>();

    public LiveData<BreedImageList> observeBreedImageListLiveData() {return breedImageListLiveData; }
    private MutableLiveData<BreedImageList> breedImageListLiveData = new MutableLiveData<>();

    public SelectViewModel() {
        api = DogApiClient.getClient(TemplateApplication.getInstance()).create(DogApi.class);
    }

    public void getBreedsNames(String sub_breed){
        System.out.println("in getBreeds");
        api.getBreedList(sub_breed.toLowerCase())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<BreedNameList>() {
                    @Override
                    public void onSuccess(BreedNameList breedNameList) {
                        System.out.println("inside onSuccess");
                        breedList.setBreedNamesList(breedNameList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorLiveData.setValue(e.getMessage());
                    }
                });
    };

    public void getBreedImages(String subBreed) {
        if(breedList.getNamesListSize() == 0) {
            System.out.println("namesList is empty ");
            return;
        }
        for(int i = 0; i < breedList.getNamesListSize(); i++) {
            String breed = subBreed+breedList.getBreedNameList().get(i);
            api.getBreedImage(breed)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSingleObserver<BreedImageList>() {
                        @Override
                        public void onSuccess(BreedImageList breedImageList) {
                            breedList.setBreedImagesList(breedImageList);
                            //breedImageListLiveData.setValue(breedImageList);
                        }

                        @Override
                        public void onError(Throwable e) {
                            errorLiveData.setValue(e.getMessage());
                        }
                    });

        }
    }

    //List<String> getBreedNamesList() {return breedNameListLiveData.getValue().getItems();}
    public BreedList getBreedList() {
        return breedList;
    }
}

