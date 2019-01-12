package com.wiproassignment.common.contract;

public interface BaseViewPresenterContract {

    interface View {

        //method to set the error state
        void showError();

        //method to show internet error
        void showInternetError();

        //method to set the loading state
        void showLoading();

        //method to stop the loading state
        void stopLoading();

    }

    interface Presenter {

        //method to clear the disposable
        void clearCompositeDisposable();
    }
}
