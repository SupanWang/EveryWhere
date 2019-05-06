package com.example.nice.everywhere.ui.main.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.HomeBean;
import com.example.nice.everywhere.model.HomeModelImpl;
import com.example.nice.everywhere.net.HomeService;
import com.example.nice.everywhere.presenter.HomePresenter;
import com.example.nice.everywhere.presenter.HomePresenterImpl;
import com.example.nice.everywhere.ui.main.adapter.HomeAdapter;
import com.example.nice.everywhere.view.main.HomeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView {


    private static final String TAG = "HomeFragment";
    private RecyclerView rv;
    private ArrayList<HomeBean.ResultBean.BannersBean> bannersBeans;
    private ArrayList<HomeBean.ResultBean.RoutesBean> routesBeans;
    private Banner banner;
    private HomeAdapter adapter;

    private String head = "JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g";
    private int page = 1;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        initBanner();
        initData();
        return inflate;
    }

    private void initBanner() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeService.HomeUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
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
                        bannersBeans.addAll(homeBean.getResult().getBanners());
                        banner.setImages(bannersBeans);
                        banner.setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                HomeBean.ResultBean.BannersBean bannerB = (HomeBean.ResultBean.BannersBean) path;
                                Glide.with(getActivity()).load(bannerB.getImageURL()).into(imageView);
                            }
                        });
                        banner.start();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initData() {
        HomePresenter homePresenter = new HomePresenterImpl(new HomeModelImpl(), this);
        homePresenter.getHomeList(head, page);
    }

    private void initView(View inflate) {
        rv = (RecyclerView) inflate.findViewById(R.id.rv);
        banner = (Banner) inflate.findViewById(R.id.banner);

        bannersBeans = new ArrayList<>();
        routesBeans = new ArrayList<>();
        adapter = new HomeAdapter(getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        routesBeans.addAll(homeBean.getResult().getRoutes());
        adapter.update(routesBeans);
    }

    @Override
    public void onFailed(String str) {
        Log.d(TAG, "onFailed: " + str);
    }
}
