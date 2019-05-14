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
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.RouteDetalBean;

import java.util.ArrayList;
import java.util.List;

public class HomeRouteAdapter extends RecyclerView.Adapter<HomeRouteAdapter.ViewHolder> {

    private ArrayList<RouteDetalBean.ResultBean.ReviewsBean> list = new ArrayList<>();
    private Context context;


    public HomeRouteAdapter(Context context) {
        this.context = context;
    }

    public void update(List<RouteDetalBean.ResultBean.ReviewsBean> list) {
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
        RouteDetalBean.ResultBean.ReviewsBean reviewsBean = list.get(position);
        viewHolder.txt_route_name.setText(reviewsBean.getUserName());
        viewHolder.txt_route_time.setText(reviewsBean.getCreatedAt());
        viewHolder.txt_route_desc.setText(reviewsBean.getContent());

        //圆形图片，需要单独写一个实体类，继承extends AppGlideModule，加注解@GlideModule
        RequestOptions options = RequestOptions.circleCropTransform();
        Glide.with(context).load(reviewsBean.getUserPhoto()).apply(options).into(viewHolder.img_route);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_route;
        private TextView txt_route_name;
        private TextView txt_route_time;
        private TextView txt_route_desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_route = itemView.findViewById(R.id.img_route);
            txt_route_name = itemView.findViewById(R.id.txt_route_name);
            txt_route_time = itemView.findViewById(R.id.txt_route_time);
            txt_route_desc = itemView.findViewById(R.id.txt_route_desc);
        }
    }
}
