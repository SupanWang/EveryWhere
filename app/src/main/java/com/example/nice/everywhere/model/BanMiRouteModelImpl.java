package com.example.nice.everywhere.model;

import com.example.nice.everywhere.bean.BanMiRouteBean;
import com.example.nice.everywhere.callback.BanMiRouteCallBack;
import com.example.nice.everywhere.net.BanmiService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BanMiRouteModelImpl implements BanMiRouteModel {
    @Override
    public void getBanRouteList(String banmiId, int page, final BanMiRouteCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BanmiService.HomeUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        BanmiService banmiService = retrofit.create(BanmiService.class);

        Observable<BanMiRouteBean> banMiRouteList = banmiService.getBanMiRouteList(banmiId, page);

        banMiRouteList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BanMiRouteBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BanMiRouteBean routeBean) {
                        callBack.onSuccess(routeBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed("伴米路线请求失败"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
