package com.example.nice.everywhere.view.main;

import com.example.nice.everywhere.bean.RouteDetalBean;

public interface RouteView {
    void onSuccess(RouteDetalBean routeDetalBean);
    void onFalied(String str);
}
