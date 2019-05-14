package com.example.nice.everywhere.model;

import com.example.nice.everywhere.bean.BanMiDetailBean;
import com.example.nice.everywhere.callback.BanMiDetailCallBack;
import com.example.nice.everywhere.net.BanmiService;
import com.example.nice.everywhere.util.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BanMiDetailModelImpl implements BanMiDetailModel {
    @Override
    public void getBanDetailList(String banmiId, int page, final BanMiDetailCallBack detailCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BanmiService.HomeUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BanmiService banmiService = retrofit.create(BanmiService.class);

        Observable<BanMiDetailBean> dongList = banmiService.getDongList(banmiId, page);

        dongList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BanMiDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BanMiDetailBean banMiDetailBean) {
                        detailCallBack.onSuccess(banMiDetailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort("伴米动态请求失败"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
