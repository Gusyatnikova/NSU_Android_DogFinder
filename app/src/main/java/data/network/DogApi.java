package data.network;

import data.model.BreedImageList;
import data.model.BreedNameList;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogApi {
    // https://dog.ceo/api/breed/{breed}/list
    // https://dog.ceo/api/breed/{breed}/images/random
    @GET("breed/{subBreed}/list")
    Single<BreedNameList> getBreedList(@Path("subBreed") String subBreed);

    @GET("/breed/{breedName}/images/random")
    Single<BreedImageList> getBreedImage(@Path("breedName") String breedName);

    //Return multiple random dog images from a sub-breed, e.g. Afghan Hound
    //https://dog.ceo/api/breed/hound/afghan/images/random/3

}
