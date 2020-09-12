package com.vadim.newsapi;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderAPI {
    @FormUrlEncoded
    @POST("/v2/top-headlines")
    Call<Object> getMiniNew(@FieldMap Map<String, String> map);
}
