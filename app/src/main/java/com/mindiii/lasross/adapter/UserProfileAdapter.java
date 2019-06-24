package com.mindiii.lasross.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mindiii.lasross.R;
import com.mindiii.lasross.model.UserProfileModel;

import java.util.List;

public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.Holder> {

    private List<UserProfileModel> list;
    private Context context;

    public UserProfileAdapter(List <UserProfileModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public UserProfileAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        UserProfileAdapter.Holder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_profile_adapter_layout, viewGroup, false);
        holder = new UserProfileAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserProfileAdapter.Holder holder, int i) {

        UserProfileModel model = list.get(i);
        holder.tvHeader.setText(model.getUserDetailHeader());
        holder.tvDetail.setText(model.getUserDetailInfo());
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder  {

        TextView tvHeader, tvDetail;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tvHeader);
            tvDetail = itemView.findViewById(R.id.tvDetail);
        }
    }
}

