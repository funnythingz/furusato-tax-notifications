package com.funnythingz.furusatotaxnotifications.infra;

import com.funnythingz.furusatotaxnotifications.domain.Entry;
import com.funnythingz.furusatotaxnotifications.domain.Feed;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFurusatoTaxNotificationsFeed {

    @SerializedName("feed")
    private Feed feed;

    @SerializedName("items")
    private List<Entry> entries;

    public Feed getFeed() {
        return feed;
    }

    public List<Entry> getEntries() {
        return entries;
    }
}
