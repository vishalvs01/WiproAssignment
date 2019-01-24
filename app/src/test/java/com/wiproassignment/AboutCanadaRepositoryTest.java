package com.wiproassignment;

import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.content.res.Resources;

import com.wiproassignment.aboutcanada.data.CanadaInfo;
import com.wiproassignment.aboutcanada.ui.AboutCanadaRepository;
import com.wiproassignment.common.ApiService;
import com.wiproassignment.common.db.DatabaseManager;
import com.wiproassignment.common.db.dao.InfoDao;
import com.wiproassignment.common.db.entity.InfoEntity;
import com.wiproassignment.common.di.SharedPrefHelper;
import com.wiproassignment.common.schedulerprovider.BaseSchedulerProvider;
import com.wiproassignment.common.schedulerprovider.TestingSchedulerProvider;
import com.wiproassignment.utils.NetworkUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Observable;

public class AboutCanadaRepositoryTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    private DatabaseManager databaseManager;
    @Mock
    private ApiService apiService;
    @Mock
    private Application context;
    @Mock
    private SharedPrefHelper sharedPrefHelper;
    @Mock
    private NetworkUtil networkUtil;

    private AboutCanadaRepository repository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        BaseSchedulerProvider schedulerProvider = new TestingSchedulerProvider();


        repository = Mockito.spy(new AboutCanadaRepository(context, apiService, databaseManager,
                sharedPrefHelper, networkUtil, schedulerProvider));

    }

    @Test
    public void getSuccessfulInfoData() {

        Mockito.doReturn(true).when(networkUtil).isNetworkAvailable();

        CanadaInfo value = Mockito.mock(CanadaInfo.class);

        ArrayList<InfoEntity> mockArrayList = Mockito.mock(ArrayList.class);

        InfoDao infoDao = Mockito.mock(InfoDao.class);

        Mockito.doReturn("title").when(value).getTitle();
        Mockito.doReturn(mockArrayList).when(value).getRows();
        Mockito.doReturn(infoDao).when(databaseManager).getInfoDao();
        Mockito.doReturn(Observable.just(value)).when(apiService).getInfo();

        repository.getUpdatedInfoData();

    }

    @Test
    public void getErrorInfoData() {

        Mockito.doReturn(true).when(networkUtil).isNetworkAvailable();

        Mockito.doReturn(Observable.error(new Exception())).when(apiService).getInfo();

        Resources resources = Mockito.mock(Resources.class);

        Mockito.doReturn(resources).when(context).getResources();

        Mockito.doReturn("error").when(resources).getString(R.string.error_occurred);

        repository.getUpdatedInfoData();
    }

    @Test
    public void getInternetError() {

        Mockito.doReturn(false).when(networkUtil).isNetworkAvailable();

        Resources resources = Mockito.mock(Resources.class);

        Mockito.doReturn(resources).when(context).getResources();

        Mockito.doReturn("no internet error").when(resources).getString(R.string.error_occurred);


        repository.getUpdatedInfoData();

    }


}
