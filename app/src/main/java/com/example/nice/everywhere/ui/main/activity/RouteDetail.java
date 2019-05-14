package com.example.nice.everywhere.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.example.nice.everywhere.util.ToastUtil;

public class RouteDetail extends Object{

    private Context context;

    public RouteDetail(Context context) {
        this.context = context;
    }

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void callAndroid(String type) {
        ToastUtil.showShort("查看主题");
        Intent intent = new Intent(context, HomeTypeActivity.class);
        context.startActivity(intent);
    }
    @JavascriptInterface
    public void callAndroid(String type,String id) {
        ToastUtil.showShort("查看线路详情");

        Intent intent = new Intent(context, HomeRouteActivity.class);
        intent.putExtra("id" , id);
        context.startActivity(intent);
    }

}
