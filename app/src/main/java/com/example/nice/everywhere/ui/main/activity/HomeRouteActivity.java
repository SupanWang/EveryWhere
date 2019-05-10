package com.example.nice.everywhere.ui.main.activity;

import android.content.Intent;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.RouteDetalBean;

import java.util.ArrayList;

public class HomeRouteActivity extends AppCompatActivity {

    private int ID;
    private RecyclerView rv_route;
    private ArrayList<RouteDetalBean.ResultBean.RouteBean> routeBeans;
    private ArrayList<RouteDetalBean.ResultBean.BanmiBean> banmiBeans;
    private ArrayList<RouteDetalBean.ResultBean.ReviewsBean> reviewsBeans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_route);
        Intent intent = getIntent();
        intent.getIntExtra("id", ID);
        initView();
    }

    private void initView() {
        rv_route = (RecyclerView) findViewById(R.id.rv_route);

    }
}
