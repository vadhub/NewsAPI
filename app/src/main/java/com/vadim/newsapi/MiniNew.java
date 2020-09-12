package com.vadim.newsapi;

import com.google.gson.annotations.SerializedName;

import java.net.URL;

public class MiniNew {
    @SerializedName("urlToImage")
    private URL urlImage;
    private String title;
    private String description;

    public URL getUrlImage() {
        return urlImage;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
