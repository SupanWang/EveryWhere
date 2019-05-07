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
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private ArrayList<HomeBean.ResultBean.RoutesBean> list = new ArrayList<>();
    private Context context;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void update(List<HomeBean.ResultBean.RoutesBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        HomeBean.ResultBean.RoutesBean routesBean = list.get(position);
        viewHolder.txt_name.setText(routesBean.getType());
        viewHolder.txt_city.setText(routesBean.getCity());
        viewHolder.btn_price.setText("￥"+routesBean.getPrice());
        viewHolder.txt_desc.setText(routesBean.getIntro());
        viewHolder.txt_num.setText(routesBean.getPrice());

        Glide.with(context).load(routesBean.getCardURL()).into(viewHolder.img_big);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_name;
        private TextView txt_city;
        private Button btn_price;
        private TextView txt_desc;
        private TextView txt_num;
        private ImageView img_big;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_big = itemView.findViewById(R.id.img_big);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_city = itemView.findViewById(R.id.txt_city);
            btn_price = itemView.findViewById(R.id.btn_price);
            txt_desc = itemView.findViewById(R.id.txt_desc);
            txt_num = itemView.findViewById(R.id.txt_num);
        }
    }
}
