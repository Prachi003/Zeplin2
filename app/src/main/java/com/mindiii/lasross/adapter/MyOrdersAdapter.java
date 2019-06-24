package com.mindiii.lasross.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindiii.lasross.R;
import com.mindiii.lasross.model.MyOrdersModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.Holder> {

    private List<MyOrdersModel> list;
    private Context context;

    public MyOrdersAdapter(List <MyOrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyOrdersAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        MyOrdersAdapter.Holder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_order_adapter_layout, viewGroup, false);
        holder = new MyOrdersAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrdersAdapter.Holder holder, int i) {

        MyOrdersModel itemList = list.get(i);
        holder.tvItemNameVariety.setText(itemList.getItemNameVariety());
        holder.tvItemName.setText(itemList.getItemName());
        holder.tvItemDeliverDate.setText(itemList.getItemDeliverDate());
        holder.btnReview.setText("REVIEW");
        Picasso.with(context)
                .load(R.drawable.dress1)
                .resize(500, 600)
                .into(holder.ivImage);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder  {

        ImageView ivImage;
        TextView tvItemNameVariety, tvItemName, tvItemDeliverDate;
        Button btnReview;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);

            tvItemNameVariety = itemView.findViewById(R.id.tvItemNameVariety);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemDeliverDate = itemView.findViewById(R.id.tvItemDeliverDate);

            btnReview = itemView.findViewById(R.id.btnReview);
        }

    }
}
