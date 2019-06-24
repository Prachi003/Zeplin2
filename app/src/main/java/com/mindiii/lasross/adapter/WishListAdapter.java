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
import com.mindiii.lasross.interfc.WishlistListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.Holder> {

    private List<DescriptionModel.HeaderListModel> list;
    private Context context;
    private WishlistListener wishlistListener;

    public WishListAdapter(WishlistListener wishlistListener , List <DescriptionModel.HeaderListModel> list, Context context) {
        this.list = list;
        this.context = context;
        this.wishlistListener = wishlistListener;
    }

    @NonNull
    @Override
    public WishListAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        WishListAdapter.Holder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wish_list_adapter_layout, viewGroup, false);
        holder = new WishListAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.Holder holder, int i) {

        DescriptionModel.HeaderListModel itemList = list.get(i);
        holder.tvItemNameVariety.setText(itemList.getItemNameVariety());
        holder.tvItemName.setText(itemList.getItemName());
        holder.tvItemPrice.setText(itemList.getItemPrice());
        holder.tvMoveToBag.setText("MOVE TO BAG");
        Picasso.with(context)
                .load(R.drawable.dress1)
                .resize(500, 600)
                .into(holder.ivImage);


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        ImageView ivImage;
        TextView tvItemNameVariety, tvItemName, tvItemPrice,tvMoveToBag;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvItemNameVariety = itemView.findViewById(R.id.tvItemNameVariety);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            tvMoveToBag= itemView.findViewById(R.id.tvMoveToBag);
            tvMoveToBag.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            wishlistListener.onClick();
        }

        /*@Override
        public void onClick(View view) {
            //wishlistListener.onClick();
        }*/
    }
}

