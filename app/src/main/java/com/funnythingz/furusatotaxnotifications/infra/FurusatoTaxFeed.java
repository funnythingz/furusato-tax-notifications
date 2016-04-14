package com.funnythingz.furusatotaxnotifications.infra;

import retrofit2.http.GET;
import rx.Observable;


public interface FurusatoTaxFeed {

    @GET("feed/notifications.xml")
    Observable<GetFurusatoTaxNotificationsFeed> getNotifications();
}
