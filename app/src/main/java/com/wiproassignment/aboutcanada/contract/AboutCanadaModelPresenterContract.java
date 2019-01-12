package com.wiproassignment.aboutcanada.contract;

import com.wiproassignment.aboutcanada.data.CanadaInfo;
import com.wiproassignment.common.contract.BaseModelPresenterContract;

import io.reactivex.Observable;

public interface AboutCanadaModelPresenterContract {

    interface Model extends BaseModelPresenterContract.Model {

        Observable<CanadaInfo> getDataObservable();

    }

}
