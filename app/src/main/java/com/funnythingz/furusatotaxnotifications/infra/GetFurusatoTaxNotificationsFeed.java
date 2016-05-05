package com.funnythingz.furusatotaxnotifications.infra;

import com.funnythingz.furusatotaxnotifications.domain.Entry;
import com.funnythingz.furusatotaxnotifications.domain.Feed;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFurusatoTaxNotificationsFeed {

    @SerializedName("feed")
    Feed feed;

    @SerializedName("items")
    List<Entry> entries;
}
