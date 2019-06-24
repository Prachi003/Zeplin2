package com.mindiii.lasross.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mindiii.lasross.R;
import com.mindiii.lasross.model.RecyclerViewModel;

import java.util.List;

public class RecyclerViewChildAdapter extends RecyclerView.Adapter<RecyclerViewChildAdapter.Holder> {

    private List<RecyclerViewModel> list;
    private Context context;

    public RecyclerViewChildAdapter(List <RecyclerViewModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewChildAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        RecyclerViewChildAdapter.Holder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_child_adapter_layout, viewGroup, false);
        holder = new RecyclerViewChildAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewChildAdapter.Holder holder, int i) {

        RecyclerViewModel itemList = list.get(i);
        holder.tvItemName.setText(itemList.getName());
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder  {

        TextView tvItemName;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemName);
        }
    }
}