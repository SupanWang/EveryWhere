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
import com.example.nice.everywhere.bean.CollectQueryBean;
import com.example.nice.everywhere.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

public class RouteCollectAdapter extends RecyclerView.Adapter<RouteCollectAdapter.ViewHolder> {

    //    CenkLIenter cenkLIenter;
    private ArrayList<CollectQueryBean.ResultBean.CollectedRoutesBean> list = new ArrayList<>();
    private Context context;


    public RouteCollectAdapter(Context context) {
        this.context = context;
    }

    public void update(List<CollectQueryBean.ResultBean.CollectedRoutesBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.collect_query_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        viewHolder.txt_collect_name.setText(list.get(position).getTitle());
        viewHolder.txt_collect_intro.setText(list.get(position).getIntro());
        Glide.with(context).load(list.get(position).getCardURL()).into(viewHolder.img_collect);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_collect;
        private TextView txt_collect_name;
        private TextView txt_collect_intro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_collect = itemView.findViewById(R.id.img_collect);
            txt_collect_name = itemView.findViewById(R.id.txt_collect_name);
            txt_collect_intro = itemView.findViewById(R.id.txt_collect_intro);
        }
    }
}
