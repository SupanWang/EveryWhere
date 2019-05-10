package com.example.nice.everywhere.ui.main.guideactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.ui.main.activity.LoginActivity;
import com.example.nice.everywhere.ui.main.guideactivity.guidadapter.GuideAdapter;
import com.example.nice.everywhere.ui.main.guideactivity.guidepreviewIndicator.PreviewIndicator;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    // 引导图片资源
    private static final int[] pics = {R.drawable.guide_01, R.drawable.guide_02,
            R.drawable.guide_03};
    private ViewPager vp;
    private  Button button;
    private TextView guide_route;
    private TextView guide_music;
    private TextView guide_map;
    private ArrayList<View> list;
    private GuideAdapter adapter;
    private PreviewIndicator parentPanel;

    private final int SPLASH_DISPLAY_LENGHT = 3000; // 延迟3秒
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();

/*        preferences = getSharedPreferences("phone", Context.MODE_PRIVATE);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (preferences.getBoolean("firststart", true)) {
                    editor = preferences.edit();
                    // 将登录标志位设置为false，下次登录时不再显示引导页面
                    editor.putBoolean("firststart", false);
                    editor.commit();
                    Intent intent = new Intent();
                    intent.setClass(GuideActivity.this,
                            LoginActivity.class);
                    GuideActivity.this.startActivity(intent);
                    GuideActivity.this.finish();
                } else {
                    Intent intent = new Intent();
                    intent.setClass(GuideActivity.this, LoginActivity.class);
                    GuideActivity.this.startActivity(intent);
                    GuideActivity.this.finish();
                }
            }
        }, SPLASH_DISPLAY_LENGHT);*/
    }

/*用法:
初始化的时候
mIndicator.initSize(80,32,6);
mIndicator.setNumbers(3);
和ViewPager关联的时候
setSelectedState(int position)*/


    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        guide_route = (TextView) findViewById(R.id.guide_route);
        guide_route.setOnClickListener(this);
        guide_music = (TextView) findViewById(R.id.guide_music);
        guide_music.setOnClickListener(this);
        guide_map = (TextView) findViewById(R.id.guide_map);
        guide_map.setOnClickListener(this);
        parentPanel = (PreviewIndicator) findViewById(R.id.parentPanel);
        parentPanel.setOnClickListener(this);

        //初始化引导页小图标
        parentPanel.initSize(80 , 32 , 6);
        parentPanel.setNumbers(3);


        setText();

        // 为引导图片提供布局参数
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        list = new ArrayList<>();
        for (int i = 0; i < pics.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(mParams);
            imageView.setImageResource(pics[i]);
            list.add(imageView);

        }

        adapter = new GuideAdapter(list, this);
        vp.setAdapter(adapter);


        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //与ViewPager绑定
                parentPanel.setSelected(i);

                if (i == pics.length - 2) {
                    guide_route.setVisibility(View.GONE);
                    guide_music.setVisibility(View.VISIBLE);
                } else {
                    guide_route.setVisibility(View.VISIBLE);
                    guide_music.setVisibility(View.GONE);
                }

                if (i == pics.length - 1) {
                    parentPanel.setVisibility(View.GONE);
                    button.setVisibility(View.VISIBLE);
                    guide_map.setVisibility(View.VISIBLE);
                    guide_route.setVisibility(View.GONE);
                    guide_music.setVisibility(View.GONE);
                } else {
                    button.setVisibility(View.GONE);
                    guide_map.setVisibility(View.GONE);
                    parentPanel.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    private void setText() {
        //图文混排1
        SpannableStringBuilder route = new SpannableStringBuilder(getResources().getString(R.string.guide_route));

        //设置前景色1
        ForegroundColorSpan what1 = new ForegroundColorSpan(getResources().getColor(R.color.c_78dpff));
        ForegroundColorSpan what2 = new ForegroundColorSpan(getResources().getColor(R.color.c_fa6a13));

        route.setSpan(what1, 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        route.setSpan(what2, 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        guide_route.setText(route);


        //图文混排2
        SpannableStringBuilder music = new SpannableStringBuilder(getResources().getString(R.string.guide_music));

        //设置前景色2
        ForegroundColorSpan what3 = new ForegroundColorSpan(getResources().getColor(R.color.c_78dpff));
        ForegroundColorSpan what4 = new ForegroundColorSpan(getResources().getColor(R.color.c_fa6a13));

        music.setSpan(what3, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        music.setSpan(what4, 4, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        guide_music.setText(music);

        //图文混排3
        SpannableStringBuilder map = new SpannableStringBuilder(getResources().getString(R.string.guide_map));

        //设置前景色3
        ForegroundColorSpan what5 = new ForegroundColorSpan(getResources().getColor(R.color.c_78dpff));
        ForegroundColorSpan what6 = new ForegroundColorSpan(getResources().getColor(R.color.c_fa6a13));

        map.setSpan(what5, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        map.setSpan(what6, 4, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        guide_map.setText(map);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
