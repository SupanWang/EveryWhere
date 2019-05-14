package com.example.nice.everywhere.callback;

import com.example.nice.everywhere.bean.RouteDetalBean;

public interface RouteCallBack {
    void onSuccess(RouteDetalBean routeDetalBean);
    void onFalied(String str);
}
