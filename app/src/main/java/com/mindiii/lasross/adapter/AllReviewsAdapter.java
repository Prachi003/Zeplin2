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
import com.mindiii.lasross.model.AllReviewsModel;

import java.util.List;

public class AllReviewsAdapter extends RecyclerView.Adapter<AllReviewsAdapter.Holder> {

    private List<AllReviewsModel> list;
    private Context context;

    public AllReviewsAdapter(List <AllReviewsModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AllReviewsAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        AllReviewsAdapter.Holder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_reviews_adapter_layout, viewGroup, false);
        holder = new AllReviewsAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllReviewsAdapter.Holder holder, int i) {

        AllReviewsModel itemList = list.get(i);
        holder.tvUserName.setText(itemList.getUserName());
        holder.tvUserComment.setText(itemList.getUserComment());
        holder.tvDate.setText(itemList.getDate());
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder  {

        ImageView ivUserImage;
        TextView tvUserName, tvUserComment, tvDate;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ivUserImage = itemView.findViewById(R.id.ivUserImage);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvUserComment = itemView.findViewById(R.id.tvUserComment);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}