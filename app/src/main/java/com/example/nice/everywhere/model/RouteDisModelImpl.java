package com.example.nice.everywhere.model;

import com.example.nice.everywhere.bean.DiscussBean;
import com.example.nice.everywhere.callback.RouteDisCallBack;
import com.example.nice.everywhere.net.HomeService;
import com.example.nice.everywhere.util.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RouteDisModelImpl implements RouteDisModel {
    @Override
    public void getDiscuss(String routeId, int page, final RouteDisCallBack disCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeService.HomeUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HomeService homeService = retrofit.create(HomeService.class);
        Observable<DiscussBean> discuss = homeService.getDiscuss(routeId, page);

        discuss.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DiscussBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DiscussBean discussBean) {
                        if (discussBean!=null && discussBean.getCode()==0) {
                            disCallBack.onSuccess(discussBean);
                        }
                        Logger.println(discussBean.getCode()+"");
                    }

                    @Override
                    public void onError(Throwable e) {
                        disCallBack.onFailed("路线全部评价请求失败-"+e.getMessage());
                        Logger.println(e.getMessage()+"dsfdbgfdsadsfdgfhgngfdjknfger");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
