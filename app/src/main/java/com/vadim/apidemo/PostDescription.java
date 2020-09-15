package com.vadim.apidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PostDescription extends AppCompatActivity {

    private ImageView imageView;
    private TextView title;
    private TextView content;
    private TextView reference;


    private String url;
    private String urlImg;
    private String titleExt;
    private String contentExt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_description);

        imageView = (ImageView) findViewById(R.id.imgPost);
        title = (TextView) findViewById(R.id.title_post);
        content = (TextView) findViewById(R.id.content);
        reference = (TextView) findViewById(R.id.reference);

        Intent intent = getIntent();

        url = intent.getStringExtra("url");
        urlImg = intent.getStringExtra("urtImg");
        titleExt = intent.getStringExtra("title");
        contentExt = intent.getStringExtra("content");

        Picasso.get().load(url).into(imageView);

        title.setText(titleExt);
        content.setText(contentExt+"Reference:"+url);

        reference.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });

    }
}