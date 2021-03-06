package com.funnythingz.furusatotaxnotifications.domain;

import com.funnythingz.furusatotaxnotifications.helper.DateHelper;
import com.google.gson.annotations.SerializedName;

public class FurusatoTaxTopicEntry {

    @SerializedName("title")
    private String title;

    @SerializedName("pubDate")
    private String pubDate;

    @SerializedName("author")
    private String author;

    @SerializedName("description")
    private String description;

    @SerializedName("link")
    private String link;

    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return DateHelper.convertGeneralDateFormatString(pubDate);
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }
}
