package com.example.nice.everywhere.callback;

import com.example.nice.everywhere.bean.BanMiRouteBean;

public interface BanMiRouteCallBack {
    void onSuccess(BanMiRouteBean routeBean);
    void onFailed(String str);
}
