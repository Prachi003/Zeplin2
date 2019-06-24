package com.mindiii.lasross.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mindiii.lasross.R;
import com.mindiii.lasross.model.NotificationModel;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Holder> {

    private List<NotificationModel> list;
    private Context context;

    public NotificationAdapter(List <NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        NotificationAdapter.Holder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_adapter_layout, viewGroup, false);
        holder = new NotificationAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.Holder holder, int i) {

        NotificationModel itemList = list.get(i);
        holder.tvItemHeader.setText(itemList.getItemHeading());
        holder.tvTime.setText(itemList.getTime());
        holder.tvItemDetail.setText(itemList.getItemDetails());
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder  {

        TextView tvItemHeader, tvTime, tvItemDetail;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvItemHeader = itemView.findViewById(R.id.tvItemHeader);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvItemDetail = itemView.findViewById(R.id.tvItemDetail);
        }
    }
}