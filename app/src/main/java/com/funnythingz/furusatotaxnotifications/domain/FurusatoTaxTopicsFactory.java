package com.funnythingz.furusatotaxnotifications.domain;

import com.funnythingz.furusatotaxnotifications.infra.GetFurusatoTaxNotificationsFeed;

public class FurusatoTaxTopicsFactory {

    public static FurusatoTaxTopics createFurusatoTaxTopics(GetFurusatoTaxNotificationsFeed getFurusatoTaxNotificationsFeed) {

        return new FurusatoTaxTopics(getFurusatoTaxNotificationsFeed.getFeed(),
                getFurusatoTaxNotificationsFeed.getEntries());
    }
}
