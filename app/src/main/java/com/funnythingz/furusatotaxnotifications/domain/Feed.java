package com.funnythingz.furusatotaxnotifications.domain;

import com.google.gson.annotations.SerializedName;

public class Feed {

    @SerializedName("title")
    private String title;

    @SerializedName("link")
    private String link;

    @SerializedName("author")
    private String author;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String image;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
