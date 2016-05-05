package com.funnythingz.furusatotaxnotifications.domain;

import java.util.List;

public class FurusatoTaxTopics {

    private Feed feed;

    private List<Entry> entries;

    public FurusatoTaxTopics(Feed feed, List<Entry> entries) {
        this.feed = feed;
        this.entries = entries;
    }

    public Feed getFeed() {
        return feed;
    }

    public List<Entry> getEntries() {
        return entries;
    }
}
