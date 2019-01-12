package com.wiproassignment.aboutcanada.di;

import android.app.Application;

import com.wiproassignment.aboutcanada.api.AboutCanadaApi;
import com.wiproassignment.aboutcanada.contract.AboutCanadaModelPresenterContract;
import com.wiproassignment.aboutcanada.contract.AboutCanadaViewPresenterContract;
import com.wiproassignment.aboutcanada.mvp.AboutCanadaModel;
import com.wiproassignment.aboutcanada.mvp.AboutCanadaPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AboutCanadaModule {

    private AboutCanadaViewPresenterContract.View view;

    public AboutCanadaModule(AboutCanadaViewPresenterContract.View view) {
        this.view = view;
    }

    @Provides
    public AboutCanadaViewPresenterContract.View providesView() {
        return view;
    }

    @Provides
    public AboutCanadaApi providesApi(Retrofit retrofit) {
        return retrofit.create(AboutCanadaApi.class);
    }

    @Provides
    public AboutCanadaModelPresenterContract.Model providesModel(Application application, AboutCanadaApi api) {
        return new AboutCanadaModel(application, api);
    }

    @Provides
    public AboutCanadaViewPresenterContract.Presenter providesPresenter(AboutCanadaViewPresenterContract.View view, AboutCanadaModelPresenterContract.Model model) {

        return new AboutCanadaPresenter(view, model);
    }
}
