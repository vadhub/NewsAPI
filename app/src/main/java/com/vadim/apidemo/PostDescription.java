package com.vadim.apidemo;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class PostDescription extends FragmentActivity {

    private ImageView imageView;
    private TextView title;
    private TextView content;
    private TextView reference;
    private Toolbar toolbar;
    private Button reverse;


    private String url;
    private String urlImg;
    private String titleExt;
    private String contentExt;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_description);

        toolbar = (Toolbar) findViewById(R.layout.toolbar);
        setActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.imgPost);
        title = (TextView) findViewById(R.id.title_post);
        content = (TextView) findViewById(R.id.content);
        reference = (TextView) findViewById(R.id.reference);
        reverse = (Button) findViewById(R.id.btn_reverse);

        Intent intent = getIntent();

        url = intent.getStringExtra("url");
        urlImg = intent.getStringExtra("urtImg");
        titleExt = intent.getStringExtra("title");
        contentExt = intent.getStringExtra("content");

        Picasso.get().load(urlImg).into(imageView);

        title.setText(titleExt);
        content.setText(contentExt);

        reference.setText("Source reference");

        reference.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });

        reverse.setOnClickListener(v -> {
            Intent intentMainActivity = new Intent(PostDescription.this, MainActivity.class);
            startActivity(intentMainActivity);

        });


    }
}