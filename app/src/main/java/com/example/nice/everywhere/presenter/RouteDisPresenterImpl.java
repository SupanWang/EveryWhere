package com.example.nice.everywhere.presenter;

import com.example.nice.everywhere.bean.DiscussBean;
import com.example.nice.everywhere.callback.RouteDisCallBack;
import com.example.nice.everywhere.model.RouteDisModel;
import com.example.nice.everywhere.view.main.RouteDisView;

public class RouteDisPresenterImpl implements RouteDisPresenter, RouteDisCallBack {

    private RouteDisModel routeDisModel;
    private RouteDisView routeDisView;

    public RouteDisPresenterImpl(RouteDisModel routeDisModel, RouteDisView routeDisView) {
        this.routeDisModel = routeDisModel;
        this.routeDisView = routeDisView;
    }

    @Override
    public void getDiscuss(String routeId, int page) {
        if (routeDisModel!=null){
            routeDisModel.getDiscuss(routeId , page , this);
        }
    }

    @Override
    public void onSuccess(DiscussBean discussBean) {
        if (routeDisView!=null){
            routeDisView.onSuccess(discussBean);
        }
    }

    @Override
    public void onFailed(String str) {
        if (routeDisView!=null){
            routeDisView.onFailed(str);
        }
    }
}
