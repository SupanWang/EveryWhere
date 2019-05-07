package com.example.nice.everywhere.model;

import com.example.nice.everywhere.bean.BanMiBean;
import com.example.nice.everywhere.bean.HomeBean;
import com.example.nice.everywhere.callback.BanmiCallBack;
import com.example.nice.everywhere.net.BanmiService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BanmiModelImpl implements BanmiModel {
    @Override
    public void getBanmi(String head, int page, final BanmiCallBack banmiCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BanmiService.HomeUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        BanmiService banmiService = retrofit.create(BanmiService.class);

        Observable<BanMiBean> banmiList = banmiService.getBanmiList(head, page);

        banmiList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BanMiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BanMiBean banMiBean) {
                        banmiCallBack.onSuccess(banMiBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        banmiCallBack.onFailed("伴米失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
