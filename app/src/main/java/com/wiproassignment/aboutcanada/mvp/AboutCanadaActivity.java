package com.wiproassignment.aboutcanada.mvp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.wiproassignment.R;
import com.wiproassignment.aboutcanada.adapter.AboutCanadaAdapter;
import com.wiproassignment.aboutcanada.contract.AboutCanadaViewPresenterContract;
import com.wiproassignment.aboutcanada.data.Info;
import com.wiproassignment.aboutcanada.di.AboutCanadaModule;
import com.wiproassignment.aboutcanada.di.DaggerAboutCanadaComponent;
import com.wiproassignment.application.App;
import com.wiproassignment.databinding.ActivityAboutCanadaBinding;

import java.util.ArrayList;

import javax.inject.Inject;

public class AboutCanadaActivity extends AppCompatActivity implements AboutCanadaViewPresenterContract.View {

    @Inject
    AboutCanadaViewPresenterContract.Presenter presenter;

    private ActivityAboutCanadaBinding binding;
    private AboutCanadaAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpMvp();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_canada);

        Toolbar toolbar=findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.bg_recycler_divider));
        binding.rvCanadaList.addItemDecoration(dividerItemDecoration);
        binding.rvCanadaList.setAdapter(adapter = new AboutCanadaAdapter());

        presenter.loadData();

    }

    public void setUpMvp() {
        DaggerAboutCanadaComponent.builder().applicationComponent(App.getApplicationComponent())
                .aboutCanadaModule(new AboutCanadaModule(this)).build().inject(this);

    }

    @Override
    public void setToolBarTitle(String title) {
        if (title != null)
            getSupportActionBar().setTitle(title);
    }

    @Override
    public void setListData(ArrayList<Info> listData) {
        adapter.addAll(listData);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getResources().getString(R.string.error_occurred), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInternetError() {
        Toast.makeText(this, getResources().getString(R.string.no_internet_found), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        binding.tvLoadingData.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        binding.tvLoadingData.setVisibility(View.GONE);
    }
}
