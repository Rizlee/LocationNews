package com.lnews.evgen.data.network;

import com.lnews.evgen.domain.entities.RootObject;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {
    @GET("top-headlines?apiKey=9e518d17f86848e0aa74cb94ae44af2b")
    Single<RootObject> getNewsByCategory(@Query("country") String country, @Query("category") String category);
}
