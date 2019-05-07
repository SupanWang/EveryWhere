package com.example.nice.everywhere.presenter;

import com.example.nice.everywhere.bean.BanMiBean;
import com.example.nice.everywhere.callback.BanmiCallBack;
import com.example.nice.everywhere.model.BanmiModel;
import com.example.nice.everywhere.view.main.BanmiView;

public class BanmiPresenterImpl implements BanmiPresenter, BanmiCallBack {

    private BanmiModel banmiModel;
    private BanmiView banmiView;

    public BanmiPresenterImpl(BanmiModel banmiModel, BanmiView banmiView) {
        this.banmiModel = banmiModel;
        this.banmiView = banmiView;
    }

    @Override
    public void getBanmit(String head, int page) {
        if (banmiModel!=null){
            banmiModel.getBanmi(head , page , this);
        }
    }

    @Override
    public void onSuccess(BanMiBean banMiBean) {
        if (banmiView!=null){
            banmiView.onSuccess(banMiBean);
        }
    }

    @Override
    public void onFailed(String str) {
        if (banmiView!=null){
            banmiView.onFailed(str);
        }
    }
}
