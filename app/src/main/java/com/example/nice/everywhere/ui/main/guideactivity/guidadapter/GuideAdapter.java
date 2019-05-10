package com.example.nice.everywhere.ui.main.guideactivity.guidadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class GuideAdapter extends PagerAdapter {

    ArrayList<View> list;
    Context context;

    public GuideAdapter(ArrayList<View> list, Context context) {
        super();
        this.list = list;
        this.context = context;
    }

    //一共可以滑动多少个页面
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    //查看索引与对象是否是同一个对象
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    //加载当前要显示的页面
    public Object instantiateItem(ViewGroup container, int position) {
        View view = list.get(position);
        container.addView(view);
        return view;
    }

    // 滑动看不见的
    // position  要移除的  view 的 下标
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = list.get(position);// 要移除的view
        container.removeView(view);
    }
}


