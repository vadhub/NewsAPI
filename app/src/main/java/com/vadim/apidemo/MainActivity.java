package com.vadim.apidemo;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends FragmentActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private  List<Article> articles = new ArrayList<>();

    private final String URL = "http://newsapi.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        getPosts();
    }

    private void getPosts(){

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build();

        JsonPlaceHolderAPI inter = retrofit.create(JsonPlaceHolderAPI.class);
        Call<News> call = inter.geNews("us", "514fbafd2e864aa99c6c034edfee8799");

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful()&&response.body().getArticles()!=null){

                    if(!articles.isEmpty()){
                        articles.clear();
                    }
                    adapter = new Adapter();
                    articles = response.body().getArticles();
                    adapter.setArticles(articles);
                    recyclerView.setAdapter(adapter);

                    initListener();

                }else {
                    Toast.makeText(MainActivity.this,"Request code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListener(){
        adapter.setOnItemClickListenerNews((view, position) -> {
            Intent intent = new Intent(MainActivity.this, PostDescription.class);
            Article article = articles.get(position);
            intent.putExtra("url", article.getUrl());
            intent.putExtra("urtImg", article.getUrlToImage());
            intent.putExtra("title", article.getTitle());
            intent.putExtra("description", article.getDescription());

            startActivity(intent);

           // Toast.makeText(MainActivity.this, position, Toast.LENGTH_SHORT).show();
        });
    }
}