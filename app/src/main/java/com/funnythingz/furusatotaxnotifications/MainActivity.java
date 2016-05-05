package com.funnythingz.furusatotaxnotifications;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.funnythingz.furusatotaxnotifications.domain.FurusatoTaxTopics;
import com.funnythingz.furusatotaxnotifications.domain.FurusatoTaxTopicsRepository;
import com.funnythingz.furusatotaxnotifications.helper.DialogHelper;
import com.funnythingz.furusatotaxnotifications.helper.LogHelper;
import com.funnythingz.furusatotaxnotifications.helper.RxBusProvider;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
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
                            if (feed instanceof FurusatoTaxTopics) {
                                FurusatoTaxTopics topics = (FurusatoTaxTopics) feed;
                                LogHelper.d(topics + "");

                                // TODO: FeedView

                                // TODO: EntriesAdapter
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

        fetchFurusatoTaxTopics();
    }

    private void fetchFurusatoTaxTopics() {

        ProgressDialog progressDialog = DialogHelper.progressDialog(this, getString(R.string.loading), false);
        progressDialog.show();

        Observable<FurusatoTaxTopics> observable = FurusatoTaxTopicsRepository.getInstance().fetchFurusatoTaxTopics();
        observable.subscribe(new Observer<FurusatoTaxTopics>() {
            @Override
            public void onCompleted() {
                Log.d("Completed", "");
                progressDialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Error: ", "", e);
                progressDialog.dismiss();
                Toast.makeText(getApplication(), getString(R.string.loading_error), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(FurusatoTaxTopics furusatoTaxTopics) {
                RxBusProvider.getInstance().send(furusatoTaxTopics);
            }
        });
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(false);
                    fetchFurusatoTaxTopics();
                }
            });
        }
    };
}
