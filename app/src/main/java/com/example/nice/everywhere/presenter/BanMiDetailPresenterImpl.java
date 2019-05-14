package com.example.nice.everywhere.presenter;

import com.example.nice.everywhere.bean.BanMiDetailBean;
import com.example.nice.everywhere.callback.BanMiDetailCallBack;
import com.example.nice.everywhere.model.BanMiDetailModel;
import com.example.nice.everywhere.view.main.BanMiDetailView;

public class BanMiDetailPresenterImpl implements BanMiDetailPresenter, BanMiDetailCallBack {

    private BanMiDetailModel detailModel;
    private BanMiDetailView detailView;

    public BanMiDetailPresenterImpl(BanMiDetailModel detailModel, BanMiDetailView detailView) {
        this.detailModel = detailModel;
        this.detailView = detailView;
    }

    @Override
    public void getBanDetailList(String banmiId, int page) {
        if (detailModel!=null){
            detailModel.getBanDetailList(banmiId , page , this);
        }
    }

    @Override
    public void onSuccess(BanMiDetailBean banMiDetailBean) {
        if (detailView!=null){
            detailView.onSuccess(banMiDetailBean);
        }
    }

    @Override
    public void onFailed(String str) {
        if (detailView!=null){
            detailView.onFailed(str);
        }
    }
}
