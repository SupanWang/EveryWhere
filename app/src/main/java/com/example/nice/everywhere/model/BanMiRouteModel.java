package com.example.nice.everywhere.model;

import com.example.nice.everywhere.callback.BanMiRouteCallBack;

public interface BanMiRouteModel {
    void getBanRouteList(String banmiId , int page , BanMiRouteCallBack callBack);
}
