package com.example.nice.everywhere.model;

import com.example.nice.everywhere.callback.RouteDisCallBack;

public interface RouteDisModel {

    void getDiscuss(String routeId , int page , RouteDisCallBack disCallBack);
}
