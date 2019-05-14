package com.example.nice.everywhere.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.RouteTypeBean;
import com.example.nice.everywhere.net.HomeService;
import com.example.nice.everywhere.ui.main.adapter.RouteTypeAdapter;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeTypeActivity extends AppCompatActivity implements RouteTypeAdapter.OnItemTypeListener {

    private ImageView img_banmi_back;
    private Toolbar toolbar;
    private View view;
    private RecyclerView mRlv_trip;
    private ArrayList<RouteTypeBean.ResultBean.BundlesBean> list;
    private RouteTypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_type);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeService.HomeUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        HomeService homeService = retrofit.create(HomeService.class);

        Observable<RouteTypeBean> routeType = homeService.getRouteType();

        routeType.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RouteTypeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RouteTypeBean routeTypeBean) {
                        list.addAll(routeTypeBean.getResult().getBundles());
                        adapter.update(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        img_banmi_back = (ImageView) findViewById(R.id.img_banmi_back);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        view = (View) findViewById(R.id.view);
        mRlv_trip = (RecyclerView) findViewById(R.id.mRlv_trip);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        img_banmi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list = new ArrayList<>();
        adapter = new RouteTypeAdapter(this);
        mRlv_trip.setLayoutManager(new LinearLayoutManager(this));
        mRlv_trip.setAdapter(adapter);

        adapter.setOnItemTypeListener(this);
    }


    @Override
    public void onItemType(RouteTypeBean.ResultBean.BundlesBean bundlesBean) {
        Intent intent = new Intent(HomeTypeActivity.this, HomeTypeDetalActivity.class);
        intent.putExtra("them" , bundlesBean.getContentURL());
        intent.putExtra("themTitle" , bundlesBean.getTitle());
        startActivity(intent);
    }
}
