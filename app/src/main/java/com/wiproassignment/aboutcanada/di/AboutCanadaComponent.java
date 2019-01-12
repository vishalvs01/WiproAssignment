package com.wiproassignment.aboutcanada.di;

import com.wiproassignment.aboutcanada.mvp.AboutCanadaActivity;
import com.wiproassignment.common.di.ActivityScope;
import com.wiproassignment.common.di.ApplicationComponent;

import dagger.Component;

@ActivityScope
@Component(modules = AboutCanadaModule.class, dependencies = ApplicationComponent.class)
public interface AboutCanadaComponent {

    void inject(AboutCanadaActivity activity);

}
