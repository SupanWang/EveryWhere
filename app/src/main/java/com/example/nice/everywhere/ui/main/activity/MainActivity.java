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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.ui.main.adapter.TabAdapter;
import com.example.nice.everywhere.ui.main.fragment.BanMiFragment;
import com.example.nice.everywhere.ui.main.fragment.HomeFragment;
import com.example.nice.everywhere.util.ToastUtil;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        photo = intent.getStringExtra("photo");
        initView();
        initTab();
        initCeHua();
    }

    private void initCeHua() {

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

        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        img = (ImageView) findViewById(R.id.img);
        //圆形图片，需要单独写一个实体类，继承extends AppGlideModule，加注解@GlideModule
        RequestOptions options = RequestOptions.circleCropTransform();
        Glide.with(MainActivity.this).load(photo).apply(options).into(img);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dl.openDrawer(Gravity.LEFT);

                //设置头布局图片更换，图片点击处理
                View view = nv.getHeaderView(0);
                ImageView img_header = view.findViewById(R.id.img_header);
                set = (TextView) view.findViewById(R.id.set);

                set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showShort("编辑吗？");
                        startActivity(new Intent(MainActivity.this , MineActivity.class));
                    }
                });

                img_header.setImageResource(R.drawable.icon_me_kaquan_banmi1);

                //监听图片
                /*img_header.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "更换完毕", Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        });
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
