package com.example.nice.everywhere.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.everywhere.R;

public class MineActivity extends AppCompatActivity {

    private TextView txt_tool_mine;
    private Toolbar toolbar;
    private TextView txt_mine_photo;
    private ImageView img_kaquan;
    private TextView txt_mine_name;
    private TextView txt_user_name;
    private TextView txt_mine_sex;
    private TextView txt_user_sex;
    private TextView txt_mine_qianming;
    private TextView txt_user_qianming;
    private TextView txt_mine_setpsw;
    private TextView txt_mine_bind;
    private String imgUrl = "http://tvax4.sinaimg.cn/crop.0.0.664.664.50/006rTk8Wly8fofptfjs0oj30ig0igt9k.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        initView();
    }

    private void initView() {
        txt_tool_mine = (TextView) findViewById(R.id.txt_tool_mine);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_mine_photo = (TextView) findViewById(R.id.txt_mine_photo);
        img_kaquan = (ImageView) findViewById(R.id.img_kaquan);
        txt_mine_name = (TextView) findViewById(R.id.txt_mine_name);
        txt_user_name = (TextView) findViewById(R.id.txt_user_name);
        txt_mine_sex = (TextView) findViewById(R.id.txt_mine_sex);
        txt_user_sex = (TextView) findViewById(R.id.txt_user_sex);
        txt_mine_qianming = (TextView) findViewById(R.id.txt_mine_qianming);
        txt_user_qianming = (TextView) findViewById(R.id.txt_user_qianming);
        txt_mine_setpsw = (TextView) findViewById(R.id.txt_mine_setpsw);
        txt_mine_bind = (TextView) findViewById(R.id.txt_mine_bind);


        //圆形图片，需要单独写一个实体类，继承extends AppGlideModule，加注解@GlideModule
        RequestOptions options = RequestOptions.circleCropTransform();
        Glide.with(MineActivity.this).load(imgUrl).apply(options).into(img_kaquan);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
