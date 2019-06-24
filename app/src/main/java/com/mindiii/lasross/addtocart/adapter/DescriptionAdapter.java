package com.mindiii.lasross.addtocart.adapter;

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
import com.squareup.picasso.Picasso;

import java.util.List;

public class DescriptionAdapter  extends RecyclerView.Adapter<DescriptionAdapter.Holder> {

    private List<DescriptionModel> list;
    private Context context;

    public DescriptionAdapter(List <DescriptionModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public DescriptionAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        DescriptionAdapter.Holder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.description_adapter_layout, viewGroup, false);
        holder = new DescriptionAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DescriptionAdapter.Holder holder, int i) {

        DescriptionModel itemList = list.get(i);
        holder.tvDescriptionHeading.setText(itemList.getHeader());
        holder.tvDescriptionDetail.setText(itemList.getDetail());
        Picasso.with(context)
                .load(R.drawable.dress2)
                .resize(530, 400)
                .into(holder.ivDescriptionImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder  {

        ImageView ivDescriptionImage;
        TextView tvDescriptionHeading, tvDescriptionDetail;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ivDescriptionImage = itemView.findViewById(R.id.ivDescriptionImage);
            tvDescriptionHeading = itemView.findViewById(R.id.tvDescriptionHeading);
            tvDescriptionDetail = itemView.findViewById(R.id.tvDescriptionDetail);
        }
    }
}
