package com.example.nice.everywhere.net;

import com.example.nice.everywhere.bean.BanMiBean;
import com.example.nice.everywhere.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface BanmiService {

    public String HomeUrl = "http://api.banmi.com/api/3.0/";

    @GET("banmi?")
    Observable<BanMiBean> getBanmiList(@Header("banmi-app-token") String head, @Query("page") int page);
}
