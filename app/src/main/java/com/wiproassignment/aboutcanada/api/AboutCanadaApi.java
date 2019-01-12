package com.wiproassignment.aboutcanada.api;

import com.wiproassignment.aboutcanada.data.CanadaInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AboutCanadaApi {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Observable<CanadaInfo> getInfo();

}
