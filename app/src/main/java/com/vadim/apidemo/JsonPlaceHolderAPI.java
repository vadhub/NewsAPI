package com.vadim.apidemo;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderAPI {

    @GET("top-headlines")
    Call<News> geNews(@Query("country") String country, @Query("apiKey") String apiKey);
}
