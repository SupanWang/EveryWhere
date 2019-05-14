package com.example.nice.everywhere.net;

import com.example.nice.everywhere.bean.CollectQueryBean;
import com.example.nice.everywhere.bean.HomeBean;
import com.example.nice.everywhere.bean.RouteCollectBean;
import com.example.nice.everywhere.bean.RouteDetalBean;
import com.example.nice.everywhere.bean.RouteTypeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HomeService {

    public String HomeUrl = "http://api.banmi.com/api/3.0/";

    //首页列表
    @GET("content/routesbundles?")
    Observable<HomeBean> getHomeList(@Header("banmi-app-token")String  head , @Query("page") int page);


    //路线详情
    @GET("content/routes/{routeId}")
    @Headers("banmi-app-token:VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
    Observable<RouteDetalBean> getRouteList(@Path("routeId") String routeId);


    /*
    ##4.6 收藏线路
	路径:api/3.0/content/routes/{routeId}/like
	请求方式:post
	参数:	routeId,路线id
			banmi-app-token:登录后的token(请求头)
	结果:略

##4.7 取消收藏线路
	路径:api/3.0/content/routes/{routeId}/dislike
	请求方式:post
	参数:	routeId,路线id
			banmi-app-token:登录后的token(请求头)
	结果:略
     */
    //路线收藏
    @POST("content/routes/{routeId}/like")
    @Headers("banmi-app-token:VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
    Observable<RouteCollectBean> getCollectRoute(@Path("routeId") String routeId);


    //路线取消收藏
    @POST("content/routes/{routeId}/dislike")
    @Headers("banmi-app-token:VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
    Observable<RouteCollectBean> getCollectRouteDis(@Path("routeId") String routeId);

    //路线收藏查询
    @GET("account/collectedRoutes")
    @Headers("banmi-app-token:VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
    Observable<CollectQueryBean> getCollectQuery(@Query("page") int page);


    // 获取专题列表
    @GET("content/bundles")
    @Headers("banmi-app-token:VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
    Observable<RouteTypeBean> getRouteType();


}
