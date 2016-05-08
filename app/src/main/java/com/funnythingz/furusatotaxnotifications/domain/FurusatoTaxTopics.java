package com.funnythingz.furusatotaxnotifications.domain;

import java.util.List;

public class FurusatoTaxTopics {

    private Feed feed;

    private List<FurusatoTaxTopicEntry> entries;

    public FurusatoTaxTopics(Feed feed, List<FurusatoTaxTopicEntry> entries) {
        this.feed = feed;
        this.entries = entries;
    }

    public Feed getFeed() {
        return feed;
    }

    public List<FurusatoTaxTopicEntry> getEntries() {
        return entries;
    }
}
