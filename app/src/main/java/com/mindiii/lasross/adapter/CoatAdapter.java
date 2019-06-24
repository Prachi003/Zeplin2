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
import com.mindiii.lasross.addtocart.model.DescriptionModel;
import com.mindiii.lasross.helper.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoatAdapter extends RecyclerView.Adapter<CoatAdapter.Holder> {

    private List<DescriptionModel.HeaderListModel> list;
    private Context context;
    public CoatAdapter(List <DescriptionModel.HeaderListModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CoatAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        CoatAdapter.Holder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_list_layout, viewGroup, false);
        holder = new CoatAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoatAdapter.Holder holder, int i) {

        DescriptionModel.HeaderListModel itemList = list.get(i);
        holder.tvItemNameVariety.setText(itemList.getItemNameVariety());
        holder.tvItemName.setText(itemList.getItemName());
        holder.tvItemPrice.setText(itemList.getItemPrice());
        Picasso.with(context)
                .load(R.drawable.dress2)
                .resize(600, 600)
                .transform(new RoundedTransformation(50, 0))
                .into(holder.ivImage);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder  {

        ImageView ivImage;
        TextView tvItemNameVariety, tvItemName, tvItemPrice;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvItemNameVariety = itemView.findViewById(R.id.tvItemNameVariety);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
        }

    }
}

