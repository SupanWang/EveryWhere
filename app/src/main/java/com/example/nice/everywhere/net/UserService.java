package com.example.nice.everywhere.net;

import com.example.nice.everywhere.bean.InfoBean;
import com.example.nice.everywhere.bean.UpDateBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {

    public String UserUrl = "https://api.banmi.com/";

    //获取用户信息
    @GET("api/3.0/account/info")
    @Headers("banmi-app-token:VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
    Observable<InfoBean> getInfo();


    //修改用户信息
    @POST("api/3.0/account/updateInfo")
    @FormUrlEncoded
    @Headers("banmi-app-token:VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
    Observable<UpDateBean> getUpdateName(@Field("userName") String username);

    //修改用户信息签名
    @POST("api/3.0/account/updateInfo")
    @FormUrlEncoded
    @Headers("banmi-app-token:VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
    Observable<UpDateBean> getUpdateQianMing(@Field( "description") String username);

    //修改用户性别
    @POST("api/3.0/account/updateInfo")
    @FormUrlEncoded
    @Headers("banmi-app-token:VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
    Observable<UpDateBean> getUpdateSex(@Field( "description") String sex);
}
