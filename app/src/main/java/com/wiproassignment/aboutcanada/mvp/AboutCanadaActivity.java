package com.wiproassignment.aboutcanada.mvp;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wiproassignment.R;
import com.wiproassignment.aboutcanada.contract.AboutCanadaViewPresenterContract;
import com.wiproassignment.aboutcanada.data.Info;

import java.util.ArrayList;

public class AboutCanadaActivity extends AppCompatActivity implements AboutCanadaViewPresenterContract.View {

    private ViewDataBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_canada);

    }

    @Override
    public void setToolBarTitle(String title) {

    }

    @Override
    public void setListData(ArrayList<Info> listData) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showInternetError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }
}
