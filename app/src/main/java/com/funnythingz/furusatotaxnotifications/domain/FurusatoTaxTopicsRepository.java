package com.funnythingz.furusatotaxnotifications.domain;

import com.funnythingz.furusatotaxnotifications.infra.FurusatoTaxFeed;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FurusatoTaxTopicsRepository {

    final String ENDPOINT = "http://rss2json.com/";

    private static final FurusatoTaxTopicsRepository furusatoTaxTopicsRepository = new FurusatoTaxTopicsRepository();

    private FurusatoTaxTopicsRepository() {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
    }

    public static FurusatoTaxTopicsRepository getInstance() {
        return furusatoTaxTopicsRepository;
    }

    private HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private FurusatoTaxFeed furusatoTaxFeed = new Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
            .create(FurusatoTaxFeed.class);

    public Observable<FurusatoTaxTopics> fetchFurusatoTaxTopics() {
        return furusatoTaxFeed.getNotifications()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(FurusatoTaxTopicsFactory::createFurusatoTaxTopics);
    }
}
