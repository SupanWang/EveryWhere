package com.example.nice.everywhere.ui.main.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.BanMiRouteBean;
import com.example.nice.everywhere.model.BanMiRouteModelImpl;
import com.example.nice.everywhere.presenter.BanMiRoutePresenter;
import com.example.nice.everywhere.presenter.BanMiRoutePresenterImpl;
import com.example.nice.everywhere.ui.main.activity.BanMiDetailActivity;
import com.example.nice.everywhere.ui.main.activity.MainActivity;
import com.example.nice.everywhere.ui.main.adapter.BanmiRouteAdapter;
import com.example.nice.everywhere.util.ToastUtil;
import com.example.nice.everywhere.view.main.BanMiRouteView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BanMiRouteFragment extends Fragment implements BanMiRouteView {


    private RecyclerView mRlv_route;
    private ArrayList<BanMiRouteBean.ResultBean.RoutesBean> routesBeans;
    private BanmiRouteAdapter routeAdapter;
    private String id = "";

    public BanMiRouteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_ban_mi_route, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    //Activity传过来的id
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        id = ((BanMiDetailActivity) getActivity()).getTitles();
    }

    private void initData() {
        BanMiRoutePresenter banMiRoutePresenter = new BanMiRoutePresenterImpl(new BanMiRouteModelImpl() , this);
        banMiRoutePresenter.getBanRouteList(id , 1);
    }

    private void initView(View inflate) {
        mRlv_route = (RecyclerView) inflate.findViewById(R.id.mRlv_route);

        routesBeans = new ArrayList<>();

        routeAdapter = new BanmiRouteAdapter(getActivity());
        mRlv_route.setLayoutManager(new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mRlv_route.setAdapter(routeAdapter);
    }

    @Override
    public void onSuccess(BanMiRouteBean routeBean) {
        routesBeans.addAll(routeBean.getResult().getRoutes());
        routeAdapter.update(routesBeans);
    }

    @Override
    public void onFailed(String str) {
        ToastUtil.showShort(str);
    }
}
