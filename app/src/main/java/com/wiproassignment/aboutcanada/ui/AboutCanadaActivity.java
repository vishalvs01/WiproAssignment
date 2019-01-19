package com.wiproassignment.aboutcanada.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.wiproassignment.R;
import com.wiproassignment.aboutcanada.adapter.AboutCanadaAdapter;
import com.wiproassignment.aboutcanada.di.DaggerAboutCanadaComponent;
import com.wiproassignment.common.ViewModelFactory;
import com.wiproassignment.application.App;
import com.wiproassignment.common.db.entity.InfoEntity;
import com.wiproassignment.databinding.ActivityAboutCanadaBinding;
import com.wiproassignment.utils.ConstantUtils;

import java.util.List;

import javax.inject.Inject;

public class AboutCanadaActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory factory;

    @Inject
    SharedPreferences sharedPreferences;

    private ActivityAboutCanadaBinding binding;
    private AboutCanadaAdapter adapter;
    private AboutCanadaViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpDi();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_canada);

        setupToolbar();
        setUpRecyclerView();

        viewModel = ViewModelProviders.of(this, factory).get(AboutCanadaViewModel.class);

        observeData();

        setSwipeToRefresh();

    }

    private void setSwipeToRefresh() {

        binding.srLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark));

        binding.srLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                viewModel.getUpdatedInfoList();

            }
        });

    }

    private void observeData() {

        viewModel.getInfoList().observe(this, new Observer<List<InfoEntity>>() {
            @Override
            public void onChanged(@Nullable List<InfoEntity> infoEntities) {
                binding.srLayout.setRefreshing(false);
                setListData(infoEntities);
                setToolBarTitle(sharedPreferences.getString(ConstantUtils.TITLE, ""));
            }
        });


        viewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                binding.srLayout.setRefreshing(false);
                showError(error);
            }
        });

        viewModel.getLoadingState().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer sate) {
                binding.tvLoadingData.setVisibility(sate);
            }
        });


    }

    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    public void setListData(List<InfoEntity> listData) {
        adapter.addAll(listData);
    }

    public void setToolBarTitle(String title) {
        if (title != null)
            getSupportActionBar().setTitle(title);
    }


    public void setUpDi() {
        DaggerAboutCanadaComponent.builder().applicationComponent(App.getApplicationComponent()).build().inject(this);
    }

    public void setupToolbar() {

        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

    }

    public void setUpRecyclerView() {

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.bg_recycler_divider));
        binding.rvCanadaList.addItemDecoration(dividerItemDecoration);
        binding.rvCanadaList.setAdapter(adapter = new AboutCanadaAdapter());

    }

}
