package com.example.nice.everywhere.ui.main.fragment;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.HomeBean;
import com.example.nice.everywhere.model.HomeModelImpl;
import com.example.nice.everywhere.presenter.HomePresenter;
import com.example.nice.everywhere.presenter.HomePresenterImpl;
import com.example.nice.everywhere.ui.main.activity.DiscussActivity;
import com.example.nice.everywhere.ui.main.activity.HomeRouteActivity;
import com.example.nice.everywhere.ui.main.activity.HomeTypeDetalActivity;
import com.example.nice.everywhere.ui.main.adapter.HomeMoreAdapter;
import com.example.nice.everywhere.view.main.HomeView;
import com.example.nice.everywhere.widget.LoadingDialog;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView, HomeMoreAdapter.OnItemClcikListener, HomeMoreAdapter.OnItemThemListener {


    private static final String TAG = "HomeFragment";
    private RecyclerView rv;
    private ArrayList<HomeBean.ResultBean.BannersBean> bannersBeans;
    private ArrayList<HomeBean.ResultBean.RoutesBean> routesBeans;
    private HomeMoreAdapter adapter;
    private String head = "VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lR" +
            "n8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ";
    private int page = 1;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        initData();
        return inflate;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initData() {
        LoadingDialog loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.create();
        HomePresenter homePresenter = new HomePresenterImpl(new HomeModelImpl(), this);
        homePresenter.getHomeList(head, page);
    }

    private void initView(View inflate) {
        rv = (RecyclerView) inflate.findViewById(R.id.rv);

        bannersBeans = new ArrayList<>();
        routesBeans = new ArrayList<>();
        adapter = new HomeMoreAdapter(getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);

        adapter.setOnItemClcikListener(this);

        adapter.setOnItemThemListener(this);
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        routesBeans.addAll(homeBean.getResult().getRoutes());
        bannersBeans.addAll(homeBean.getResult().getBanners());
        adapter.update(routesBeans, bannersBeans);
    }

    @Override
    public void onFailed(String str) {
        Log.d(TAG, "onFailed: " + str);
    }

    @Override
    public void onItemClcik(HomeBean.ResultBean.RoutesBean routesBean) {
        Intent intent = new Intent(getActivity(), HomeRouteActivity.class);
        intent.putExtra("id" , routesBean.getId()+"");
        startActivity(intent);
    }

    @Override
    public void onItemThem(HomeBean.ResultBean.RoutesBean routesBean) {
        Intent intent = new Intent(getActivity(), HomeTypeDetalActivity.class);
        intent.putExtra("type" , routesBean.getContentURL()+"?os=android");
        intent.putExtra("title" , routesBean.getTitle());
        Log.d(TAG, "onItemThem: "+routesBean.getTitle());
        startActivity(intent);
    }
}
