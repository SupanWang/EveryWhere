package com.example.nice.everywhere.model;

import com.example.nice.everywhere.callback.BanmiCallBack;

public interface BanmiModel {

    void getBanmi(String head , int page , BanmiCallBack banmiCallBack);
}
