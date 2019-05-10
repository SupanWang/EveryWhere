package com.example.nice.everywhere.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.base.BaseActivity;
import com.example.nice.everywhere.base.BasePresenter;
import com.example.nice.everywhere.bean.BanMiBean;
import com.example.nice.everywhere.model.BanmiModelImpl;
import com.example.nice.everywhere.presenter.BanmiPresenter;
import com.example.nice.everywhere.presenter.BanmiPresenterImpl;
import com.example.nice.everywhere.ui.main.adapter.BanmiAdapter;
import com.example.nice.everywhere.util.Logger;
import com.example.nice.everywhere.view.main.BanmiView;
import com.example.nice.everywhere.widget.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BanMiFragment extends Fragment implements BanmiView {


    private RecyclerView rv;
    private SmartRefreshLayout srfl;
    private ArrayList<BanMiBean.ResultBean.BanmiBean> list;
    private String head = "JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g";
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
        showLoading();
        initData();
        hideLoading();
        return inflate;
    }


    public void showLoading() {
        if (mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(getActivity());
        }
        mLoadingDialog.show();
    }

    public void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }

    private void initData() {
        BanmiPresenter banmiPresenter = new BanmiPresenterImpl(new BanmiModelImpl() , this);
        banmiPresenter.getBanmit(head , page);
    }

    private void initView(View inflate) {
        rv = (RecyclerView) inflate.findViewById(R.id.rv);
        srfl = (SmartRefreshLayout) inflate.findViewById(R.id.srfl);
        list = new ArrayList<>();
        adapter = new BanmiAdapter(getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);

    }

    @Override
    public void onSuccess(BanMiBean banMiBean) {
        List<BanMiBean.ResultBean.BanmiBean> banmi = banMiBean.getResult().getBanmi();
        list.addAll(banmi);
        adapter.update(list);
    }

    @Override
    public void onFailed(String str) {
        Logger.println(str);
    }
}
