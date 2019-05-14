package com.example.nice.everywhere.model;

import com.example.nice.everywhere.bean.RouteDetalBean;
import com.example.nice.everywhere.callback.RouteCallBack;
import com.example.nice.everywhere.net.HomeService;

import org.greenrobot.greendao.annotation.Id;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RouteModelImpl implements RouteModel {
    @Override
    public void getRouteList(String routeId, final RouteCallBack routeCallBack) {

        Retrofit build = new Retrofit.Builder()
                .baseUrl(HomeService.HomeUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        HomeService homeService = build.create(HomeService.class);

        Observable<RouteDetalBean> routeList = homeService.getRouteList(routeId);

        routeList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RouteDetalBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RouteDetalBean routeDetalBean) {
                            routeCallBack.onSuccess(routeDetalBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        routeCallBack.onFalied("路线详情请求失败"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
