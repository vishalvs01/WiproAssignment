package com.wiproassignment.aboutcanada.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.wiproassignment.common.db.entity.InfoEntity;

import java.util.List;

public class AboutCanadaViewModel extends ViewModel {

    private AboutCanadaRepository repository;
    private MutableLiveData<List<InfoEntity>> info;

    public AboutCanadaViewModel(AboutCanadaRepository repository) {
        this.repository = repository;
    }

    //live data for info list
    public LiveData<List<InfoEntity>> getInfoList() {

        if (info == null) {
            info = repository.getInfoData();
        }

        return info;

    }

    //live data for updated info list
    public void getUpdatedInfoList() {

        repository.getUpdatedInfoData();

    }

    //live data for error state
    public LiveData<String> getErrorMessage() {

        return repository.getErrorData();

    }

    //live data for error state
    public LiveData<Integer> getLoadingState() {

        return repository.getLoadingData();

    }

}
