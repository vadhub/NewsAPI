package com.vadim.apidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView textView;

    private final String URL = "http://newsapi.org/v2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);

        btn.setOnClickListener(v -> getPosts());

    }

    private void getPosts(){
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build();

        JsonPlaceHolderAPI inter = retrofit.create(JsonPlaceHolderAPI.class);
        Call<News> call = inter.geNews("us", "514fbafd2e864aa99c6c034edfee8799");

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(!response.isSuccessful()){
                    textView.setText("responseCode"+response.code());
                    return;
                }


                List<Article> articles = response.body().getArticles();
                for(Article article : articles){
                    String content = "";
                    content += "title:" + article.getTitle()+"\n";
                    content += "description:" + article.getDescription()+"\n";
                    content += "urlToImage:" + article.getUrlToImage()+"\n";

                    textView.append(content);

                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}