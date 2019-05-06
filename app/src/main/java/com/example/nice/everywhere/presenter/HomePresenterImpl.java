package com.example.nice.everywhere.presenter;

import com.example.nice.everywhere.bean.HomeBean;
import com.example.nice.everywhere.callback.HomeCallBack;
import com.example.nice.everywhere.model.HomeModel;
import com.example.nice.everywhere.view.main.HomeView;

public class HomePresenterImpl implements HomePresenter, HomeCallBack {

    private HomeModel homeModel;
    private HomeView homeView;

    public HomePresenterImpl(HomeModel homeModel, HomeView homeView) {
        this.homeModel = homeModel;
        this.homeView = homeView;
    }

    @Override
    public void getHomeList(String head, int page) {
        if (homeModel!=null){
            homeModel.getHomeList(head , page , this);
        }
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        if (homeView!=null){
            homeView.onSuccess(homeBean);
        }
    }

    @Override
    public void onFailed(String str) {
        if (homeView!=null){
            homeView.onFailed(str);
        }
    }
}
