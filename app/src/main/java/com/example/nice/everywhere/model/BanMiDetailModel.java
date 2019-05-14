package com.example.nice.everywhere.model;

import com.example.nice.everywhere.callback.BanMiDetailCallBack;

public interface BanMiDetailModel {

    void getBanDetailList(String banmiId , int page , BanMiDetailCallBack detailCallBack);
}
