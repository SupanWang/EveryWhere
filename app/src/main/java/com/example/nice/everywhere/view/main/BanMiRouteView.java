package com.example.nice.everywhere.view.main;

import com.example.nice.everywhere.bean.BanMiRouteBean;

public interface BanMiRouteView {
    void onSuccess(BanMiRouteBean routeBean);
    void onFailed(String str);
}
