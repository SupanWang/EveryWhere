package com.example.nice.everywhere.model;

import com.example.nice.everywhere.callback.HomeCallBack;

public interface HomeModel {

    void getHomeList(String head , int page , HomeCallBack callBack);
}
