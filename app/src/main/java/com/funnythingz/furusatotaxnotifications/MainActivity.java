package com.funnythingz.furusatotaxnotifications;

import android.os.Handler;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.funnythingz.furusatotaxnotifications.helper.DialogHelper;
import com.funnythingz.furusatotaxnotifications.helper.LogHelper;
import com.funnythingz.furusatotaxnotifications.helper.RxBusProvider;
import com.funnythingz.furusatotaxnotifications.infra.FurusatoTaxFeed;
import com.funnythingz.furusatotaxnotifications.infra.GetFurusatoTaxNotificationsFeed;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.refresh)
    SwipeRefreshLayout refreshLayout;

    private CompositeSubscription compositeSubscription;

    @Override
    protected void onResume() {
        super.onResume();
        compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(RxBusProvider.getInstance()
                        .toObservable()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(feed -> {
                            if (feed instanceof GetFurusatoTaxNotificationsFeed) {
                                GetFurusatoTaxNotificationsFeed f = (GetFurusatoTaxNotificationsFeed) feed;
                                LogHelper.d(f + "");
                            }
                        })
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeSubscription.unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        refreshLayout.setOnRefreshListener(onRefreshListener);

        fetchFurusatoTaxNotificationsFeed();
    }

    private void fetchFurusatoTaxNotificationsFeed() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        // TODO: Feed受け取る部分
        // 参考: https://futurestud.io/blog/retrofit-how-to-integrate-xml-converter
        FurusatoTaxFeed furusatoTaxFeed = new Retrofit.Builder()
                .baseUrl("http://www.furusato-tax.jp/")
                .client(httpClient.build())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(FurusatoTaxFeed.class);

        Observable<GetFurusatoTaxNotificationsFeed> observable = furusatoTaxFeed.getNotifications()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        ProgressDialog progressDialog = DialogHelper.progressDialog(this, getString(R.string.loading), false);
        progressDialog.show();

        observable.subscribe(new Observer<GetFurusatoTaxNotificationsFeed>() {
            @Override
            public void onCompleted() {
                Log.d("Completed", "");
                progressDialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                // FIXME: XMLのパース意味不明すぎる。Feedの全部の要素を想定しなきゃいけないのか？
                Log.e("Error: ", "", e);
                progressDialog.dismiss();
                Toast.makeText(getApplication(), getString(R.string.loading_error), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(GetFurusatoTaxNotificationsFeed getFurusatoTaxNotificationsFeed) {
                RxBusProvider.getInstance().send(getFurusatoTaxNotificationsFeed);
            }
        });

        furusatoTaxFeed.getNotifications()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(false);
                    fetchFurusatoTaxNotificationsFeed();
                }
            });
        }
    };
}
