package com.example.nice.everywhere.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nice.everywhere.R;
import com.jaeger.library.StatusBarUtil;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;


public class WebActivity extends AppCompatActivity {

    private TextView tv_title;
    private Toolbar toolBar;
    private LinearLayout container;
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
    }

    public static void startAct(Context context) {
        Intent intent = new Intent(context, WebActivity.class);
        context.startActivity(intent);
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        container = (LinearLayout) findViewById(R.id.container);

        //亮色的模式 , 会将状态栏文字修改为黑色
        StatusBarUtil.setLightMode(this);
        toolBar.setTitle("");
        toolBar.setNavigationIcon(R.drawable.back_white);
        setSupportActionBar(toolBar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mAgentWeb.back()) {
                    finish();
                }
            }
        });

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(container, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()   //关闭进度条
                .createAgentWeb()
                .ready()
                .go("https://api.banmi.com/app2017/agreement.html");

        //WebView   获取文本标题
         /*new WebView(this).setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                //获取网页标题
                super.onReceivedTitle(view, title);
            }
        });*/

        mAgentWeb.getWebCreator().getWebView().setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (!TextUtils.isEmpty(title)){
                    tv_title.setText(title);
                }
                super.onReceivedTitle(view, title);
            }
        });

    }


    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
