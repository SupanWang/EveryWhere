package com.example.nice.everywhere.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.InfoBean;
import com.example.nice.everywhere.ui.main.adapter.TabAdapter;
import com.example.nice.everywhere.ui.main.fragment.BanMiFragment;
import com.example.nice.everywhere.ui.main.fragment.HomeFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tab;
    private MenuItem menuItem;
    private ArrayList<Fragment> fragments;
    private TabAdapter tabAdapter;
    private Toolbar toolbar;
    private ImageView img;
    private String photo;
    private DrawerLayout dl;
    private NavigationView nv;
    private String imgUrl = "http://tvax4.sinaimg.cn/crop.0.0.664.664.50/006rTk8Wly8fofptfjs0oj30ig0igt9k.jpg";
    private TextView set;
    private TextView myCollect;
    private TextView header_name;
    private TextView header_sex;

    private ArrayList<InfoBean.ResultBean> infoList;
    private String userName;
    private String gender;
    private String description;
    private ImageView img_header;
    private RelativeLayout rlv_header;
    private RelativeLayout rlv_head_kaquan;
    private RelativeLayout rlv_head_xingcheng;
    private RelativeLayout rlv_head_collect;
    private RelativeLayout rlv_head_guanzhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        photo = intent.getStringExtra("photo");
        initView();
        initData();
        initTab();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .addHeader("banmi-app-token", "VVj1CrFBgv1MMe7GaHcjlU6VENB6yi3C9JGGX3uitHIjOe098" +
                        "XWwsaJDPr33S3lRn8J4nxh68LKq3zggTAzqCvRuwXeKFM0boMRDknAexjfp5s7xYCiipkYP0QPh7WQQ")
                .url("https://api.banmi.com/api/3.0/account/info?username=2&descripition=3&gender=4")
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        InfoBean infoBean = gson.fromJson(string, InfoBean.class);
                        InfoBean.ResultBean result = infoBean.getResult();
                        userName = result.getUserName();
                        description = result.getDescription();
                        gender = result.getGender();

                        header_name.setText(userName);
                        header_sex.setText(description);
                    }
                });
            }
        });
    }


    private void initTab() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new BanMiFragment());

        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.tab_select));
        tab.addTab(tab.newTab().setText("伴米").setIcon(R.drawable.tab_select2));

        tabAdapter = new TabAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(tabAdapter);


        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        dl = (DrawerLayout) findViewById(R.id.dl);
        nv = (NavigationView) findViewById(R.id.nv);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        img = (ImageView) findViewById(R.id.img);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //圆形图片，需要单独写一个实体类，继承extends AppGlideModule，加注解@GlideModule
        RequestOptions options = RequestOptions.circleCropTransform();
        Glide.with(MainActivity.this).load(imgUrl).apply(options).into(img);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启侧滑
                dl.openDrawer(Gravity.LEFT);
            }
        });

        //设置头布局图片更换，图片点击处理
        View view = nv.getHeaderView(0);
        img_header = view.findViewById(R.id.img_header);
        set = (TextView) view.findViewById(R.id.set);
        rlv_header = (RelativeLayout)view.findViewById(R.id.rlv_header);
        rlv_head_kaquan = (RelativeLayout)view.findViewById(R.id.rlv_head_kaquan);
        rlv_head_xingcheng = (RelativeLayout)view.findViewById(R.id.rlv_head_xingcheng);
        rlv_head_collect = (RelativeLayout)view.findViewById(R.id.rlv_head_collect);
        rlv_head_guanzhu = (RelativeLayout)view.findViewById(R.id.rlv_head_guanzhu);
        header_sex = (TextView)view.findViewById(R.id.header_sex);
        header_name = (TextView)view.findViewById(R.id.header_name);


        header_name.setText(userName);
        header_sex.setText(description);
        rlv_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        ToastUtil.showShort("编辑吗？");
                Intent intent = new Intent(MainActivity.this, MineActivity.class);
                intent.putExtra("username" , userName);
                intent.putExtra("description" , description+"...");
                intent.putExtra("gender",gender);
//                intent.putExtra("photo" , photo);
                startActivity(intent);
            }
        });

        myCollect = (TextView) view.findViewById(R.id.txt_collect);

        rlv_head_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , RouteCollectActivity.class));
            }
        });

        rlv_head_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , BanmiGuanZhuActivity.class));
            }
        });

        RequestOptions options2 = RequestOptions.circleCropTransform();
        Glide.with(MainActivity.this).load(imgUrl).apply(options2).into(img_header);
//        img_header.setImageResource(R.drawable.icon_me_kaquan_banmi1);
    }


    //选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);

        menuItem = menu.findItem(R.id.action_msg);

        return super.onCreateOptionsMenu(menu);
    }

    //选项菜单
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        }
        return super.onOptionsItemSelected(item);
    }
}
