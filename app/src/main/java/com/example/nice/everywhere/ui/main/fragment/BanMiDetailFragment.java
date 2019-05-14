package com.example.nice.everywhere.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.BanMiDetailBean;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BanMiDetailFragment extends Fragment {


    private ArrayList<BanMiDetailBean.ResultBean.ActivitiesBean> activitiesBeans;
    private ArrayList<BanMiDetailBean.ResultBean.BanmiBean> banmiBeans;
    private ArrayList<BanMiDetailBean.ResultBean.ShareBean> shareBeans;

    public BanMiDetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_ban_mi_detail, container, false);
        return inflate;
    }

}
