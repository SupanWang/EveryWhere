package com.example.nice.everywhere.model;

import com.example.nice.everywhere.callback.RouteCallBack;

public interface RouteModel {

    void getRouteList(String routeId , RouteCallBack routeCallBack);
}
