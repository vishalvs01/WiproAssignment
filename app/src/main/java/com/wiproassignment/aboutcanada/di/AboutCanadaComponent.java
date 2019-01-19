package com.wiproassignment.aboutcanada.di;

import com.wiproassignment.aboutcanada.ui.AboutCanadaActivity;
import com.wiproassignment.common.di.ActivityScope;
import com.wiproassignment.common.di.ApplicationComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface AboutCanadaComponent {

    void inject(AboutCanadaActivity activity);

}
