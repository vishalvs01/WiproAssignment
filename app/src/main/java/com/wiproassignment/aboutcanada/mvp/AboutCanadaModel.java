package com.wiproassignment.aboutcanada.mvp;

import android.app.Application;

import com.wiproassignment.aboutcanada.api.AboutCanadaApi;
import com.wiproassignment.aboutcanada.contract.AboutCanadaModelPresenterContract;
import com.wiproassignment.aboutcanada.data.CanadaInfo;
import com.wiproassignment.utils.NetworkUtils;
import com.wiproassignment.utils.NoInternetException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AboutCanadaModel implements AboutCanadaModelPresenterContract.Model {

    private Application context;
    private AboutCanadaApi api;

    public AboutCanadaModel(Application context, AboutCanadaApi api) {
        this.context = context;
        this.api = api;
    }

    @Override
    public String getScreenName() {
        return null;
    }

    @Override
    public Observable<CanadaInfo> getDataObservable() {

        if (!NetworkUtils.isNetworkAvailable(context))
            return Observable.error(new NoInternetException());

        return api.getInfo().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
