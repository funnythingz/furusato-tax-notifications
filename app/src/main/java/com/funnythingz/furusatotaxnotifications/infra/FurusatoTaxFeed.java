package com.funnythingz.furusatotaxnotifications.infra;

import retrofit2.http.GET;
import rx.Observable;


public interface FurusatoTaxFeed {

    @GET("api.json?rss_url=http://www.furusato-tax.jp/feed/notifications.xml")
    Observable<GetFurusatoTaxNotificationsFeed> getNotifications();
}
