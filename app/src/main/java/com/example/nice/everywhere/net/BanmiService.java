package com.example.nice.everywhere.net;

import com.example.nice.everywhere.bean.BanMiBean;
import com.example.nice.everywhere.bean.BanMiDetailBean;
import com.example.nice.everywhere.bean.BanMiGuanBean;
import com.example.nice.everywhere.bean.BanMiRouteBean;
import com.example.nice.everywhere.bean.BanQueryGuan;
import com.example.nice.everywhere.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BanmiService {

    public String HomeUrl = "http://api.banmi.com/api/3.0/";

    //伴米页面
    @GET("banmi?")
    Observable<BanMiBean> getBanmiList(@Header("banmi-app-token") String head, @Query("page") int page);

    //伴米关注
    @POST("banmi/{banmiId}/follow")
    @Headers("banmi-app-token:VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
    Observable<BanMiGuanBean> getBanMiGuan(@Path("banmiId") String id);

    //伴米取消关注
    @POST("banmi/{id}/unfollow")
    @Headers("banmi-app-token:VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
    Observable<BanMiGuanBean> getBanMiCancel(@Path("id") String id);


    //伴米关注查看
    @GET("account/followedBanmi")
    @Headers("banmi-app-token:VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
    Observable<BanMiBean> getBanQuery(@Query("page") int page);


    // 伴米详情页面--伴米动态
    @GET("banmi/{banmiId}")
    @Headers("banmi-app-token:VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
    Observable<BanMiDetailBean> getDongList(@Path("banmiId") String banmiId ,@Query("page") int page);

    //伴米详情页面--伴米发布的线路
    @GET("banmi/{banmiId}/routes")
    @Headers("banmi-app-token:VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
    Observable<BanMiRouteBean> getBanMiRouteList(@Path("banmiId") String banmiId ,@Query("page") int page);



}
