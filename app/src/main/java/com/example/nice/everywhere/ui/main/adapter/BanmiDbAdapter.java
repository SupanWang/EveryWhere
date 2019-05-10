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

import java.util.ArrayList;
import java.util.List;

public class BanmiDbAdapter extends RecyclerView.Adapter<BanmiDbAdapter.ViewHolder> {

    private ArrayList<BanmiDaoBean> list = new ArrayList<>();
    private Context context;
    private ImageView img_banmi_dao;
    private TextView ban_dao_desc;
    private TextView ban_dao_name;
    private TextView txt_dao_money;
    private TextView ban_dao_place;


    public BanmiDbAdapter(Context context) {
        this.context = context;
    }

    public void update(List<BanmiDaoBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.banmi_dao_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        BanmiDaoBean banmiDaoBean = list.get(position);
        viewHolder.ban_dao_name.setText(banmiDaoBean.getName());
        viewHolder.txt_dao_money.setText(banmiDaoBean.getFollowing() + "元");
        viewHolder.ban_dao_desc.setText(banmiDaoBean.getOccupation());
        viewHolder.ban_dao_place.setText(banmiDaoBean.getLocation());

        Glide.with(context).load(banmiDaoBean.getPhoto()).into(viewHolder.img_banmi_dao);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_banmi_dao;
        private TextView ban_dao_desc;
        private TextView ban_dao_name;
        private TextView txt_dao_money;
        private TextView ban_dao_place;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_banmi_dao = itemView.findViewById(R.id.img_banmi_dao);
            ban_dao_desc = itemView.findViewById(R.id.ban_dao_desc);
            ban_dao_name = itemView.findViewById(R.id.ban_dao_name);
            txt_dao_money = itemView.findViewById(R.id.txt_dao_money);
            ban_dao_place = itemView.findViewById(R.id.ban_dao_place);
        }
    }
}
