package com.example.nice.everywhere.net;

import com.example.nice.everywhere.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HomeService {

    public String HomeUrl = "http://api.banmi.com/api/3.0/content/";

    @GET("routesbundles?")
    Observable<HomeBean> getHomeList(@Header("banmi-app-token")String  head , @Query("page") int page);
}
