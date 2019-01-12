package com.wiproassignment.aboutcanada.contract;

import com.wiproassignment.aboutcanada.data.Info;
import com.wiproassignment.common.contract.BaseViewPresenterContract;

import java.util.ArrayList;

public interface AboutCanadaViewPresenterContract {

    interface View extends BaseViewPresenterContract.View {

        void setToolBarTitle(String title);

        void setListData(ArrayList<Info> listData);

    }

    interface Presenter extends BaseViewPresenterContract.Presenter {
        void loadData();

        void reloadData();
    }

}
