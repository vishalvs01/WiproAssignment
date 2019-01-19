package com.wiproassignment.aboutcanada.ui;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.view.View;

import com.wiproassignment.R;
import com.wiproassignment.aboutcanada.data.CanadaInfo;
import com.wiproassignment.common.ApiService;
import com.wiproassignment.common.db.DatabaseManager;
import com.wiproassignment.common.db.entity.InfoEntity;
import com.wiproassignment.utils.ConstantUtils;
import com.wiproassignment.utils.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AboutCanadaRepository {

    private ApiService apiService;
    private DatabaseManager databaseManager;
    private MediatorLiveData<List<InfoEntity>> infoListLive = new MediatorLiveData<>();
    private SharedPreferences.Editor editor;
    private Application context;
    private MutableLiveData<String> errorData;
    private MutableLiveData<Integer> loadingData;

    @Inject
    public AboutCanadaRepository(Application context, ApiService apiService, DatabaseManager databaseManager, SharedPreferences.Editor editor) {
        this.context = context;
        this.apiService = apiService;
        this.databaseManager = databaseManager;
        this.editor = editor;
    }

    public LiveData<String> getErrorData() {

        errorData = new MediatorLiveData<>();
        return errorData;

    }

    public LiveData<Integer> getLoadingData() {

        loadingData = new MediatorLiveData<>();
        return loadingData;

    }

    public MutableLiveData<List<InfoEntity>> getInfoData() {

        final LiveData<List<InfoEntity>> infoList = databaseManager.getInfoDao().getInfoList();

        infoListLive.addSource(infoList, new Observer<List<InfoEntity>>() {
            @Override
            public void onChanged(@Nullable List<InfoEntity> infoEntities) {

                loadingData.setValue(View.VISIBLE);

                //check for db data
                if (infoEntities == null || infoEntities.isEmpty()) {

                    //check for internet connectivity
                    if (!NetworkUtils.isNetworkAvailable(context)) {
                        loadingData.setValue(View.GONE);
                        errorData.setValue(context.getResources().getString(R.string.no_internet_found));
                        return;
                    }

                    //get data from server
                    apiService.getInfo().subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<CanadaInfo>() {
                        @Override
                        public void onNext(CanadaInfo value) {

                            //hide loader
                            loadingData.setValue(View.GONE);

                            //insert value of title in pref
                            editor.putString(ConstantUtils.TITLE, value.getTitle()).apply();

                            //insert list data in db
                            databaseManager.getInfoDao().insertInfoList(value.getRows());

                            infoListLive.removeSource(infoList);
                            infoListLive.setValue(value.getRows());

                        }

                        @Override
                        public void onError(Throwable e) {
                            //hide loader
                            loadingData.setValue(View.GONE);

                            errorData.setValue(context.getResources().getString(R.string.error_occurred));
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                } else {
                    //hide loader
                    loadingData.setValue(View.GONE);

                    infoListLive.removeSource(infoList);
                    infoListLive.setValue(infoEntities);
                }

            }
        });

        return infoListLive;

    }
}
