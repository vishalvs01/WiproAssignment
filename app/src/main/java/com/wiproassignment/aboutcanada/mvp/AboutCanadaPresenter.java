package com.wiproassignment.aboutcanada.mvp;

import com.wiproassignment.aboutcanada.contract.AboutCanadaModelPresenterContract;
import com.wiproassignment.aboutcanada.contract.AboutCanadaViewPresenterContract;
import com.wiproassignment.aboutcanada.data.CanadaInfo;
import com.wiproassignment.common.BasePresenter;
import com.wiproassignment.utils.NoInternetException;

import io.reactivex.observers.DisposableObserver;

public class AboutCanadaPresenter extends BasePresenter<AboutCanadaViewPresenterContract.View, AboutCanadaModelPresenterContract.Model> implements AboutCanadaViewPresenterContract.Presenter {


    public AboutCanadaPresenter(AboutCanadaViewPresenterContract.View view, AboutCanadaModelPresenterContract.Model model) {
        super(view, model);
        createCompositeDisposable();
    }

    @Override
    public void loadData() {

        view.showLoading();

        compositeDisposable.add(model.getDataObservable().subscribeWith(new DisposableObserver<CanadaInfo>() {

            @Override
            public void onNext(CanadaInfo value) {
                view.stopLoading();
                view.setListData(value.getRows());
                view.setToolBarTitle(value.getTitle());
            }

            @Override
            public void onError(Throwable e) {
                clearCompositeDisposable();

                view.stopLoading();

                if (e instanceof NoInternetException)
                    view.showInternetError();
                else
                    view.showError();
            }

            @Override
            public void onComplete() {
                clearCompositeDisposable();
            }
        }));

    }

    @Override
    public void reloadData() {

        if (compositeDisposable.size() != 0)
            return;

        compositeDisposable.add(model.getDataObservable().subscribeWith(new DisposableObserver<CanadaInfo>() {

            @Override
            public void onNext(CanadaInfo value) {
                view.setListData(value.getRows());
            }

            @Override
            public void onError(Throwable e) {
                clearCompositeDisposable();
                view.stopLoading();

                if (e instanceof NoInternetException)
                    view.showInternetError();
                else
                    view.showError();
            }

            @Override
            public void onComplete() {
                clearCompositeDisposable();
            }
        }));

    }
}
