package com.example.nice.everywhere.ui.main.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.BanMiBean;
import com.example.nice.everywhere.ui.main.adapter.TabAdapter;
import com.example.nice.everywhere.ui.main.fragment.BanMiDetailFragment;
import com.example.nice.everywhere.ui.main.fragment.BanMiRouteFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class BanMiDetailActivity extends AppCompatActivity {

    private ImageView img_banmi_back;
    private TextView txt_banmi_detal;
    private ImageView img_share;
    private Toolbar toolbar_update;
    private ImageView img_banmi;
    private TextView txt_banmi_name;
    private ImageView img_banmi_heart;
    private TextView txt_guanzhu;
    private ImageView img_navg;
    private TextView txt_banmi_city;
    private TextView txt_banmi_occupation;
    private TabLayout tab;
    private ViewPager vp;
    private ScrollView scoll;
    private ArrayList<Fragment> fragments;
    private TabAdapter adapter;
    private BanMiBean.ResultBean.BanmiBean banmiBean;
    private TextView txt_banmi_desc;
    private TextView txt_banmi_guan;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_mi_detail);
        Intent intent = getIntent();
        banmiBean = (BanMiBean.ResultBean.BanmiBean) intent.getSerializableExtra("banmiBean");
        initView();
        initTab();
    }

    private void initTab() {
        fragments = new ArrayList<>();
        fragments.add(new BanMiDetailFragment());
        fragments.add(new BanMiRouteFragment());

        tab.addTab(tab.newTab().setText("动态"));
        tab.addTab(tab.newTab().setText("路线"));

        adapter = new TabAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);

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
        img_banmi_back = (ImageView) findViewById(R.id.img_banmi_back);
        txt_banmi_detal = (TextView) findViewById(R.id.txt_banmi_detal);
        img_share = (ImageView) findViewById(R.id.img_share);
        toolbar_update = (Toolbar) findViewById(R.id.toolbar_update);
        img_banmi = (ImageView) findViewById(R.id.img_banmi);
        txt_banmi_name = (TextView) findViewById(R.id.txt_banmi_name);
        img_banmi_heart = (ImageView) findViewById(R.id.img_banmi_heart);
        txt_guanzhu = (TextView) findViewById(R.id.txt_guanzhu);
        img_navg = (ImageView) findViewById(R.id.img_navg);
        txt_banmi_city = (TextView) findViewById(R.id.txt_banmi_city);
        txt_banmi_occupation = (TextView) findViewById(R.id.txt_banmi_occupation);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        scoll = (ScrollView) findViewById(R.id.scoll);
        txt_banmi_desc = (TextView) findViewById(R.id.txt_banmi_desc);
        txt_banmi_guan = (TextView) findViewById(R.id.txt_banmi_guan);
        ll = (LinearLayout) findViewById(R.id.ll);

        toolbar_update.setTitle("");
        setSupportActionBar(toolbar_update);

        initData();
        initBack();

    }

    private void initBack() {
        img_banmi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {

        RoundedCorners roundedCorners = new RoundedCorners(15);//数字为圆角度数
        RequestOptions coverRequestOptions = new RequestOptions()
                .transforms(roundedCorners)
                .placeholder(R.drawable.zhanweitu_home_kapian)   //占位图
                .diskCacheStrategy(DiskCacheStrategy.ALL)//不做磁盘缓存
                .skipMemoryCache(true);//不做内存缓存

        Glide.with(this).load(banmiBean.getPhoto()).apply(coverRequestOptions).into(img_banmi);

        txt_banmi_name.setText(banmiBean.getName());
        txt_guanzhu.setText(banmiBean.getFollowing() + "人关注");
        txt_banmi_city.setText(banmiBean.getLocation());
        txt_banmi_occupation.setText(banmiBean.getOccupation());
        txt_banmi_desc.setText(banmiBean.getIntroduction());
        boolean isFollowed = banmiBean.isIsFollowed();
        if (isFollowed) {
            txt_banmi_guan.setText("已关注");
            img_banmi_heart.setImageResource(R.mipmap.follow);
        } else {
            txt_banmi_guan.setText("关注");
            img_banmi_heart.setImageResource(R.mipmap.follow_unselected);
        }

    }

    //宿主activity中的getTitles()方法
    public String getTitles(){
        return banmiBean.getId()+"";
    }
}
