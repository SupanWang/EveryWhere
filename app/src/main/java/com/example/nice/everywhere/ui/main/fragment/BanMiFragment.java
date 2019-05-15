package com.example.nice.everywhere.ui.main.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.BanMiBean;
import com.example.nice.everywhere.model.BanmiModelImpl;
import com.example.nice.everywhere.presenter.BanmiPresenter;
import com.example.nice.everywhere.presenter.BanmiPresenterImpl;
import com.example.nice.everywhere.ui.main.activity.BanMiDetailActivity;
import com.example.nice.everywhere.ui.main.adapter.BanmiAdapter;
import com.example.nice.everywhere.util.Logger;
import com.example.nice.everywhere.view.main.BanmiView;
import com.example.nice.everywhere.widget.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BanMiFragment extends Fragment implements BanmiView, BanmiAdapter.OnItemClickListener {


    private RecyclerView rv;
    private SmartRefreshLayout srfl;
    private ArrayList<BanMiBean.ResultBean.BanmiBean> list;
    private String head = "VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ";
    private int page = 1;
    private BanmiAdapter adapter;
    private LoadingDialog mLoadingDialog;

    public BanMiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_ban_mi, container, false);
        initView(inflate);
        initData();
        return inflate;
    }


    private void initData() {
        BanmiPresenter banmiPresenter = new BanmiPresenterImpl(new BanmiModelImpl(), this);
        banmiPresenter.getBanmit(head, page);
    }

    private void initView(View inflate) {
        rv = (RecyclerView) inflate.findViewById(R.id.rv);
        srfl = (SmartRefreshLayout) inflate.findViewById(R.id.srfl);
        list = new ArrayList<>();
        adapter = new BanmiAdapter(getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);


        adapter.setOnItemClickListener(this);

        srfl.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                initData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                list.clear();
                initData();
            }
        });
    }

    @Override
    public void onSuccess(BanMiBean banMiBean) {
        List<BanMiBean.ResultBean.BanmiBean> banmi = banMiBean.getResult().getBanmi();
        list.addAll(banmi);
        adapter.update(list);
        srfl.finishLoadmore();
        srfl.finishRefresh();

    }

    @Override
    public void onFailed(String str) {
        Logger.println(str);
    }

    @Override
    public void onItemClick(BanMiBean.ResultBean.BanmiBean banmiBean) {
        Intent intent = new Intent(getActivity(), BanMiDetailActivity.class);
        intent.putExtra("banmiBean" ,banmiBean);
        startActivity(intent);
    }
}
