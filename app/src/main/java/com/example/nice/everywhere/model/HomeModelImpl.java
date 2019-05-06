package com.example.nice.everywhere.model;

import com.example.nice.everywhere.bean.HomeBean;
import com.example.nice.everywhere.callback.HomeCallBack;
import com.example.nice.everywhere.net.HomeService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeModelImpl implements HomeModel {
    @Override
    public void getHomeList(String head, int page, final HomeCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeService.HomeUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        HomeService homeService = retrofit.create(HomeService.class);
        Observable<HomeBean> homeList = homeService.getHomeList(head, page);

        homeList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        callBack.onSuccess(homeBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed("失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}