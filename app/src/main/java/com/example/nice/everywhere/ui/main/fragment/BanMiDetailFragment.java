package com.example.nice.everywhere.ui.main.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.BanMiDetailBean;
import com.example.nice.everywhere.model.BanMiDetailModelImpl;
import com.example.nice.everywhere.presenter.BanMiDetailPresenter;
import com.example.nice.everywhere.presenter.BanMiDetailPresenterImpl;
import com.example.nice.everywhere.ui.main.activity.BanMiDetailActivity;
import com.example.nice.everywhere.ui.main.adapter.BanmiDetsilAdapter;
import com.example.nice.everywhere.util.Logger;
import com.example.nice.everywhere.view.main.BanMiDetailView;

import java.util.ArrayList;

import javax.xml.transform.sax.TemplatesHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class BanMiDetailFragment extends Fragment implements BanMiDetailView {


    private ArrayList<BanMiDetailBean.ResultBean.ActivitiesBean> activitiesBeans;
    private BanmiDetsilAdapter adapter;
    private RecyclerView rlv_dong;
    private String id = "";


    public BanMiDetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_ban_mi_detail, container, false);
        initView(inflate);
        initData();
        return inflate;
    }
    //TODO  要记忆 Activity传过来的id
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        id = ((BanMiDetailActivity) getActivity()).getTitles();
    }


    private void initData() {
        BanMiDetailPresenter banMiDetailPresenter = new BanMiDetailPresenterImpl(new BanMiDetailModelImpl() , this);
        banMiDetailPresenter.getBanDetailList( id, 1);
    }

    private void initView(View inflate) {
        rlv_dong = (RecyclerView) inflate.findViewById(R.id.rlv_dong);

        activitiesBeans = new ArrayList<>();
        adapter = new BanmiDetsilAdapter(getActivity());
        rlv_dong.setLayoutManager(new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rlv_dong.setAdapter(adapter);
    }

    @Override
    public void onSuccess(BanMiDetailBean banMiDetailBean) {
        activitiesBeans.addAll(banMiDetailBean.getResult().getActivities());
        adapter.update(activitiesBeans);
    }

    @Override
    public void onFailed(String str) {
        Logger.println(str);
    }
}
