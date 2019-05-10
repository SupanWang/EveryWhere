package com.example.nice.everywhere.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.BanmiDaoBean;
import com.example.nice.everywhere.ui.main.adapter.BanmiDbAdapter;
import com.example.nice.everywhere.util.DbUtils;

import java.util.ArrayList;
import java.util.List;

public class BanmiGuanZhuActivity extends AppCompatActivity {

    private TextView txt_tool_mine;
    private Toolbar toolbar;
    private RecyclerView rv_ban_dao;
    private ArrayList<BanmiDaoBean> list;
    private BanmiDbAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banmi_collect);
        initView();
        initData();
    }

    private void initData() {
        List<BanmiDaoBean> query = DbUtils.getDbUtils().query();
        list.addAll(query);
        adapter.update(list);
    }

    private void initView() {
        txt_tool_mine = (TextView) findViewById(R.id.txt_tool_mine);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rv_ban_dao = (RecyclerView) findViewById(R.id.rv_ban_dao);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = new ArrayList<>();
        adapter = new BanmiDbAdapter(this);
        rv_ban_dao.setLayoutManager(new GridLayoutManager(this , 2));
        rv_ban_dao.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
