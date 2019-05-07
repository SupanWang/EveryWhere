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
import com.example.nice.everywhere.bean.BanMiBean;

import java.util.ArrayList;
import java.util.List;

public class BanmiAdapter extends RecyclerView.Adapter<BanmiAdapter.ViewHolder> {

    private ArrayList<BanMiBean.ResultBean.BanmiBean> list = new ArrayList<>();
    private Context context;


    public BanmiAdapter(Context context) {
        this.context = context;
    }

    public void update(List<BanMiBean.ResultBean.BanmiBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.banmi_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        BanMiBean.ResultBean.BanmiBean banmiBean = list.get(position);
        viewHolder.txt_banmi_name.setText(banmiBean.getName());
        viewHolder.txt_guanzhu.setText(banmiBean.getFollowing()+"人关注");
        viewHolder.txt_banmi_city.setText(banmiBean.getLocation());
        viewHolder.txt_banmi_occupation.setText(banmiBean.getOccupation());

        Glide.with(context).load(banmiBean.getPhoto()).into(viewHolder.img_banmi);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_banmi;
        private TextView txt_banmi_name;
        private TextView txt_guanzhu;
        private TextView txt_banmi_city;
        private TextView txt_banmi_occupation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_banmi = itemView.findViewById(R.id.img_banmi);
            txt_banmi_name = itemView.findViewById(R.id.txt_banmi_name);
            txt_guanzhu = itemView.findViewById(R.id.txt_guanzhu);
            txt_banmi_city = itemView.findViewById(R.id.txt_banmi_city);
            txt_banmi_occupation = itemView.findViewById(R.id.txt_banmi_occupation);
        }
    }
}
