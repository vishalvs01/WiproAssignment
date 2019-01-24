package com.wiproassignment.aboutcanada.ui;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.view.View;

import com.wiproassignment.R;
import com.wiproassignment.aboutcanada.data.CanadaInfo;
import com.wiproassignment.common.ApiService;
import com.wiproassignment.common.db.DatabaseManager;
import com.wiproassignment.common.db.entity.InfoEntity;
import com.wiproassignment.common.di.SharedPrefHelper;
import com.wiproassignment.common.schedulerprovider.BaseSchedulerProvider;
import com.wiproassignment.utils.ConstantUtils;
import com.wiproassignment.utils.NetworkUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class AboutCanadaRepository {

    private ApiService apiService;
    private DatabaseManager databaseManager;
    private MediatorLiveData<List<InfoEntity>> infoListLive = new MediatorLiveData<>();
    private SharedPrefHelper sharedPrefHelper;
    private Application context;
    private MutableLiveData<String> errorData;
    private MutableLiveData<Integer> loadingData;
    private NetworkUtil networkUtil;
    private BaseSchedulerProvider schedulerProvider;

    @Inject
    public AboutCanadaRepository(Application context, ApiService apiService,
                                 DatabaseManager databaseManager, SharedPrefHelper sharedPrefHelper,
                                 NetworkUtil networkUtil, BaseSchedulerProvider schedulerProvider) {
        this.context = context;
        this.apiService = apiService;
        this.databaseManager = databaseManager;
        this.sharedPrefHelper = sharedPrefHelper;
        this.networkUtil = networkUtil;
        this.schedulerProvider = schedulerProvider;
    }

    public LiveData<String> getErrorData() {

        errorData = new MutableLiveData<>();
        return errorData;

    }

    public LiveData<Integer> getLoadingData() {

        loadingData = new MutableLiveData<>();
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
                    if (!networkUtil.isNetworkAvailable()) {
                        loadingData.setValue(View.GONE);
                        errorData.setValue(context.getResources().getString(R.string.no_internet_found));
                        return;
                    }

                    //get data from server
                    apiService.getInfo().subscribeOn(schedulerProvider.io())
                            .observeOn(schedulerProvider.mainThread()).subscribeWith(new DisposableObserver<CanadaInfo>() {
                        @Override
                        public void onNext(CanadaInfo value) {

                            //hide loader
                            loadingData.setValue(View.GONE);

                            //insert value of title in pref
                            sharedPrefHelper.putString(ConstantUtils.TITLE, value.getTitle());

                            //insert list data in db
                            databaseManager.getInfoDao().insertInfoList(value.getRows());

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

                    infoListLive.setValue(infoEntities);
                }

            }
        });

        return infoListLive;

    }

    public void getUpdatedInfoData() {

        //check for internet connectivity
        if (!networkUtil.isNetworkAvailable()) {
            loadingData.setValue(View.GONE);
            errorData.setValue(context.getResources().getString(R.string.no_internet_found));
            return;
        }

        //get data from server
        apiService.getInfo().subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread()).subscribeWith(new DisposableObserver<CanadaInfo>() {
            @Override
            public void onNext(CanadaInfo value) {

                //insert value of title in pref
                sharedPrefHelper.putString(ConstantUtils.TITLE, value.getTitle());

                //delete old data
                databaseManager.getInfoDao().clearInfoList();

                //insert list data in db
                databaseManager.getInfoDao().insertInfoList(value.getRows());


            }

            @Override
            public void onError(Throwable e) {
                errorData.setValue(context.getResources().getString(R.string.error_occurred));
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
