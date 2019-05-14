package com.example.nice.everywhere.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nice.everywhere.R;

public class HomeTypeDetalActivity extends AppCompatActivity {

    private WebView web;
    private String type;
    private TextView txt_tool_mine;
    private Toolbar toolbar;
    private ImageView img_banmi_back;
    private String webTitle;
    private ProgressBar progressBar1;
    private String them;
    private String themTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_them);
        Intent intent = getIntent();
        //首页展示Web
        type = intent.getStringExtra("type");
        webTitle = intent.getStringExtra("title");
        Log.d("sdddddddddddddddddd", "onItemThem: " + webTitle);

        //主题展示Web
        Intent intent1 = getIntent();
        them = intent1.getStringExtra("them");
        themTitle = intent1.getStringExtra("themTitle");
        initView();
    }

    private void initView() {
        web = (WebView) findViewById(R.id.web);
        txt_tool_mine = (TextView) findViewById(R.id.txt_tool_mine);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        img_banmi_back = (ImageView) findViewById(R.id.img_banmi_back);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);

        img_banmi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setTitle("");

        txt_tool_mine.setText(webTitle);//获取WebView的标题
        if (themTitle != null){
            txt_tool_mine.setText(themTitle);
        }


        setSupportActionBar(toolbar);

        WebSettings settings = web.getSettings();
        //设置自适应屏幕，两者合用
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        settings.setJavaScriptEnabled(true); //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        init();
        web.loadUrl(type);
        web.loadUrl(them);

        //Js调用Android方法1
        //参数1,桥梁类的对象
        //参数2,对象的名字
        RouteDetail android = new RouteDetail(this);
        web.addJavascriptInterface(android, "android");


    }

    //设置WEb进度条
    private void init() {
        web.setWebViewClient(new WebViewClient() {
            //覆写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
        });
        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress == 100) {
                    progressBar1.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar1.setProgress(newProgress);//设置进度值
                }

            }
        });
    }

    //设置返回键动作（防止按返回键直接退出程序)
/*    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (web.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                web.goBack();
                return true;
            } else {//当webview处于第一页面时,直接退出程序
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }*/

}
