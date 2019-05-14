package com.example.nice.everywhere.presenter;

import com.example.nice.everywhere.bean.BanMiRouteBean;
import com.example.nice.everywhere.callback.BanMiRouteCallBack;
import com.example.nice.everywhere.model.BanMiRouteModel;
import com.example.nice.everywhere.view.main.BanMiRouteView;

public class BanMiRoutePresenterImpl implements BanMiRoutePresenter, BanMiRouteCallBack {

    private BanMiRouteModel routeModel;
    private BanMiRouteView routeView;

    public BanMiRoutePresenterImpl(BanMiRouteModel routeModel, BanMiRouteView routeView) {
        this.routeModel = routeModel;
        this.routeView = routeView;
    }

    @Override
    public void getBanRouteList(String banmiId, int page) {
        if (routeModel!=null){

            routeModel.getBanRouteList(banmiId , page , this);
        }
    }

    @Override
    public void onSuccess(BanMiRouteBean routeBean) {
        if (routeView!=null){
            routeView.onSuccess(routeBean);
        }
    }

    @Override
    public void onFailed(String str) {
        if (routeView!=null){
            routeView.onFailed(str);
        }
    }
}
