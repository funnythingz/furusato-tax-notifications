package com.funnythingz.furusatotaxnotifications;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.funnythingz.furusatotaxnotifications.infra.FurusatoTaxFeed;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Feed受け取る部分
        // 参考: https://futurestud.io/blog/retrofit-how-to-integrate-xml-converter
        FurusatoTaxFeed furusatoTaxFeed = new Retrofit.Builder()
                .baseUrl("http://www.furusato-tax.jp/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
                .create(FurusatoTaxFeed.class);

        furusatoTaxFeed.getNotifications()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        setContentView(R.layout.activity_main);
    }
}
