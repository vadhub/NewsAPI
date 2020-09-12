package com.vadim.newsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Gson gson = new GsonBuilder().create();

    private final String URL = "http://newsapi.org/v2/top-headlines?" +
            "country=us&" +
            "apiKey=514fbafd2e864aa99c6c034edfee8799";

    Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).baseUrl(URL).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}