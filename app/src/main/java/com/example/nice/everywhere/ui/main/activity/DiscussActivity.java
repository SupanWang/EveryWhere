package com.example.nice.everywhere.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.DiscussBean;
import com.example.nice.everywhere.model.RouteDisModelImpl;
import com.example.nice.everywhere.presenter.RouteDisPresenter;
import com.example.nice.everywhere.presenter.RouteDisPresenterImpl;
import com.example.nice.everywhere.ui.main.adapter.DisCussAdapter;
import com.example.nice.everywhere.util.Logger;
import com.example.nice.everywhere.view.main.RouteDisView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

public class DiscussActivity extends AppCompatActivity implements RouteDisView {

    private ImageView img_banmi_back;
    private TextView txt_tool_trip;
    private Toolbar toolbar;
    private View view;
    private RecyclerView mRlv_dis;
    private String routeId;
    private int page = 1;
    private ArrayList<DiscussBean.ResultBean.ReviewsBean> reviewsBeans;
    private DisCussAdapter adapter;
    private SmartRefreshLayout smRl;

    //  TODO      这是我的分支区分标记
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        Intent intent = getIntent();
        routeId = intent.getStringExtra("routeId");
        initView();
        initData();
    }

    private void initData() {
        RouteDisPresenter disPresenter = new RouteDisPresenterImpl(new RouteDisModelImpl(), this);
        disPresenter.getDiscuss(routeId, page);
    }

    private void initView() {
        img_banmi_back = (ImageView) findViewById(R.id.img_banmi_back);
        txt_tool_trip = (TextView) findViewById(R.id.txt_tool_trip);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        view = (View) findViewById(R.id.view);
        mRlv_dis = (RecyclerView) findViewById(R.id.mRlv_dis);
        smRl = (SmartRefreshLayout) findViewById(R.id.smRl);


        String a = "";
        String b = "";
        String c = "";

        int a = 2;
        int b = 3;
        
        img_banmi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        reviewsBeans = new ArrayList<>();
        adapter = new DisCussAdapter(this);
        mRlv_dis.setLayoutManager(new LinearLayoutManager(this));

        mRlv_dis.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //设置分割线
        mRlv_dis.setAdapter(adapter);

        //加载更多
        smRl.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                initData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                reviewsBeans.clear();
                initData();
            }
        });
    }

    @Override
    public void onSuccess(DiscussBean discussBean) {
        Logger.println(discussBean.getCode() + "MainSuccess");
        reviewsBeans.addAll(discussBean.getResult().getReviews());
        adapter.update(reviewsBeans);
        smRl.finishRefresh();
        smRl.finishLoadmore();
    }

    @Override
    public void onFailed(String str) {
        Logger.println(str + "MainFailed");
    }
}
package com.example.nice.everywhere.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.DiscussBean;
import com.example.nice.everywhere.model.RouteDisModelImpl;
import com.example.nice.everywhere.presenter.RouteDisPresenter;
import com.example.nice.everywhere.presenter.RouteDisPresenterImpl;
import com.example.nice.everywhere.ui.main.adapter.DisCussAdapter;
import com.example.nice.everywhere.util.Logger;
import com.example.nice.everywhere.view.main.RouteDisView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

public class DiscussActivity extends AppCompatActivity implements RouteDisView {

    private ImageView img_banmi_back;
    private TextView txt_tool_trip;
    private Toolbar toolbar;
    private View view;
    private RecyclerView mRlv_dis;
    private String routeId;
    private int page = 1;
    private ArrayList<DiscussBean.ResultBean.ReviewsBean> reviewsBeans;
    private DisCussAdapter adapter;
    private SmartRefreshLayout smRl;

    //  TODO      这是我的分支区分标记
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        Intent intent = getIntent();
        routeId = intent.getStringExtra("routeId");
        initView();
        initData();
    }

    private void initData() {
        RouteDisPresenter disPresenter = new RouteDisPresenterImpl(new RouteDisModelImpl(), this);
        disPresenter.getDiscuss(routeId, page);
    }

    private void initView() {
        img_banmi_back = (ImageView) findViewById(R.id.img_banmi_back);
        txt_tool_trip = (TextView) findViewById(R.id.txt_tool_trip);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        view = (View) findViewById(R.id.view);
        mRlv_dis = (RecyclerView) findViewById(R.id.mRlv_dis);
        smRl = (SmartRefreshLayout) findViewById(R.id.smRl);


        String a = "";
        String b = "";
        String c = "";

        int a = 2;
        int b = 3;


        img_banmi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        reviewsBeans = new ArrayList<>();
        adapter = new DisCussAdapter(this);
        mRlv_dis.setLayoutManager(new LinearLayoutManager(this));

        mRlv_dis.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //设置分割线
        mRlv_dis.setAdapter(adapter);

        //加载更多
        smRl.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                initData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                reviewsBeans.clear();
                initData();
            }
        });
    }

    @Override
    public void onSuccess(DiscussBean discussBean) {
        Logger.println(discussBean.getCode() + "MainSuccess");
        reviewsBeans.addAll(discussBean.getResult().getReviews());
        adapter.update(reviewsBeans);
        smRl.finishRefresh();
        smRl.finishLoadmore();
    }

    @Override
    public void onFailed(String str) {
        Logger.println(str + "MainFailed");
    }
}
