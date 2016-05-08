package com.funnythingz.furusatotaxnotifications.infra;

import com.funnythingz.furusatotaxnotifications.domain.FurusatoTaxTopicEntry;
import com.funnythingz.furusatotaxnotifications.domain.Feed;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFurusatoTaxNotificationsFeed {

    @SerializedName("feed")
    private Feed feed;

    @SerializedName("items")
    private List<FurusatoTaxTopicEntry> entries;

    public Feed getFeed() {
        return feed;
    }

    public List<FurusatoTaxTopicEntry> getEntries() {
        return entries;
    }
}
