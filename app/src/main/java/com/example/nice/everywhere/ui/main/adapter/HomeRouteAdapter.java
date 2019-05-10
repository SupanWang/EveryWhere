package com.example.nice.everywhere.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.BanmiDaoBean;
import com.example.nice.everywhere.bean.HomeBean;
import com.example.nice.everywhere.util.DbUtils;
import com.example.nice.everywhere.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeRouteAdapter extends RecyclerView.Adapter<HomeRouteAdapter.ViewHolder> {

    private ArrayList<HomeBean.ResultBean.RoutesBean> list = new ArrayList<>();
    private Context context;


    public HomeRouteAdapter(Context context) {
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_route__item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        HomeBean.ResultBean.RoutesBean routesBean = list.get(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView route_city;
        private TextView route_name;
        private TextView route_desc;
        private ImageView route_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            route_city = itemView.findViewById(R.id._route_city);
            route_name = itemView.findViewById(R.id.route_name);
            route_desc = itemView.findViewById(R.id.route_desc);
            route_img = itemView.findViewById(R.id.route_img);
        }
    }
}
