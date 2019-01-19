package com.wiproassignment.common;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.wiproassignment.aboutcanada.ui.AboutCanadaRepository;
import com.wiproassignment.aboutcanada.ui.AboutCanadaViewModel;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private AboutCanadaRepository repository;

    @Inject
    public ViewModelFactory(AboutCanadaRepository repository) {

        this.repository = repository;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (AboutCanadaViewModel.class.isAssignableFrom(modelClass)) {
            return (T) new AboutCanadaViewModel(repository);

        }

        return null;
    }
}
