package com.mindiii.lasross.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindiii.lasross.R;
import com.mindiii.lasross.model.MyCartModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.Holder> {

    private List<MyCartModel> list;
    private Context context;

    public MyCartAdapter(List <MyCartModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyCartAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        MyCartAdapter.Holder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_cart_adapter_layout, viewGroup, false);
        holder = new MyCartAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.Holder holder, int i) {

        MyCartModel itemList = list.get(i);
        holder.tvItemNameVariety.setText(itemList.getItemNameVariety());
        holder.tvItemName.setText(itemList.getItemName());
        holder.tvItemPrice.setText(itemList.getItemPrice());
        holder.tvItemSize.setText(itemList.getSize());
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
        TextView tvItemNameVariety, tvItemName, tvItemSize, tvItemPrice;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvItemNameVariety = itemView.findViewById(R.id.tvItemNameVariety);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            tvItemSize = itemView.findViewById(R.id.tvItemSize);
        }

    }
}

