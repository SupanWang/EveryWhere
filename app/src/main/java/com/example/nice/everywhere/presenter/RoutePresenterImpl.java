package com.example.nice.everywhere.presenter;

import com.example.nice.everywhere.bean.RouteDetalBean;
import com.example.nice.everywhere.callback.RouteCallBack;
import com.example.nice.everywhere.model.RouteModel;
import com.example.nice.everywhere.view.main.RouteView;

public class RoutePresenterImpl implements RoutePresenter, RouteCallBack {

    private RouteModel routeModel;
    private RouteView routeView;

    public RoutePresenterImpl(RouteModel routeModel, RouteView routeView) {
        this.routeModel = routeModel;
        this.routeView = routeView;
    }

    @Override
    public void getRouteList(String routeId) {
        if (routeModel!=null){
            routeModel.getRouteList(routeId , this);
        }
    }

    @Override
    public void onSuccess(RouteDetalBean routeDetalBean) {
        if (routeView!=null){
            routeView.onSuccess(routeDetalBean);
        }
    }

    @Override
    public void onFalied(String str) {
        if (routeView!=null){
            routeView.onFalied(str);
        }
    }
}
