package com.example.nice.everywhere.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.BanMiRouteBean;

import java.util.ArrayList;
import java.util.List;

public class BanmiRouteAdapter extends RecyclerView.Adapter<BanmiRouteAdapter.ViewHolder> {

    //    CenkLIenter cenkLIenter;
    private ArrayList<BanMiRouteBean.ResultBean.RoutesBean> list = new ArrayList<>();
    private Context context;


    public BanmiRouteAdapter(Context context) {
        this.context = context;
    }

    public void update(List<BanMiRouteBean.ResultBean.RoutesBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.banmi_route__item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        BanMiRouteBean.ResultBean.RoutesBean routesBean = list.get(position);
        viewHolder.txt_name.setText(list.get(position).getTitle());
        viewHolder.txt_shop.setText(list.get(position).getPriceInCents() + "人购买");
        viewHolder.txt_city.setText(list.get(position).getCity());
        viewHolder.btn_price.setText("¥"+list.get(position).getPrice());
        viewHolder.txt_desc.setText(list.get(position).getIntro());


        RoundedCorners cornes = new RoundedCorners(10);//数字为圆角度数
        RequestOptions options = new RequestOptions()
                .transforms(cornes)
                .placeholder(R.drawable.zhanweitu_xianlu_jingdian)   //占位图
                .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                .skipMemoryCache(true);//不做内存缓存
        Glide.with(context).load(routesBean.getCardURL()).apply(options).into(viewHolder.img_big);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_big;
        private TextView txt_name;
        private TextView txt_city;
        private Button btn_price;
        private TextView txt_shop;
        private TextView txt_desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_big = itemView.findViewById(R.id.img_big);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_city = itemView.findViewById(R.id.txt_city);
            btn_price = itemView.findViewById(R.id.btn_price);
            txt_shop = itemView.findViewById(R.id.txt_shop);
            txt_desc = itemView.findViewById(R.id.txt_desc);
        }
    }

}
