package com.mindiii.lasross.addtocart.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mindiii.lasross.R;
import com.mindiii.lasross.addtocart.AddToCartLongActivity17;
import com.mindiii.lasross.addtocart.model.DescriptionModel;
import com.mindiii.lasross.helper.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.Holder> {

    private List<DescriptionModel.HeaderListModel> list;
    private Context context;

    public RelatedProductAdapter(List <DescriptionModel.HeaderListModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RelatedProductAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        RelatedProductAdapter.Holder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.realted_product_adapter_layout, viewGroup, false);
        holder = new RelatedProductAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedProductAdapter.Holder holder, int i) {

        DescriptionModel.HeaderListModel itemList = list.get(i);
        holder.tvItemNameVariety.setText(itemList.getItemNameVariety());
        holder.tvItemName.setText(itemList.getItemName());
        holder.tvItemPrice.setText(itemList.getItemPrice());
        Picasso.with(context)
                .load(R.drawable.dress2)
                .resize(500, 500)
                .transform(new RoundedTransformation(30, 0))
                .into(holder.ivImage);
        holder.fullLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AddToCartLongActivity17.class));
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder  {

        ImageView ivImage;
        TextView tvItemNameVariety, tvItemName, tvItemPrice;
        LinearLayout fullLayout;

        TextView tvUpTo50, tvMessage;
        Button btnPick;
        ImageView ivDiscount;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvItemNameVariety = itemView.findViewById(R.id.tvItemNameVariety);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            fullLayout = itemView.findViewById(R.id.fullLayout);

        }
    }
}