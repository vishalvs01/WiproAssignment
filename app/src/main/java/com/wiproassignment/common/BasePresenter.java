package com.wiproassignment.common;

import com.wiproassignment.common.contract.BaseModelPresenterContract;
import com.wiproassignment.common.contract.BaseViewPresenterContract;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<View extends BaseViewPresenterContract.View, Model extends BaseModelPresenterContract.Model> implements BaseViewPresenterContract.Presenter {

    protected View view;
    protected Model model;
    protected CompositeDisposable compositeDisposable;

    public BasePresenter(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    //method to create new composite disposable in which observable will be added
    protected void createCompositeDisposable() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    /*method to clear composite disposable in which observable
    will be cleared and hence will not be called if view not visible*/
    @Override
    public void clearCompositeDisposable() {
        if (compositeDisposable != null && compositeDisposable.size() > 0) {
            compositeDisposable.clear();
        }
    }
}
