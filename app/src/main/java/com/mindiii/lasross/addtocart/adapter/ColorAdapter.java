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
import com.mindiii.lasross.addtocart.model.ColorModel;
import com.mindiii.lasross.addtocart.model.DescriptionModel;
import com.mindiii.lasross.helper.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.Holder> {

    private List<ColorModel> list;
    private Context context;

    public ColorAdapter(List <ColorModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ColorAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        ColorAdapter.Holder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.color_adapter_layout, viewGroup, false);
        holder = new ColorAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ColorAdapter.Holder holder, int i) {

        ColorModel itemList = list.get(i);
        holder.tvColor.setBackgroundColor(itemList.getColorCode());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder  {
        TextView tvColor ;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvColor = itemView.findViewById(R.id.tvColor);
        }
    }
}
