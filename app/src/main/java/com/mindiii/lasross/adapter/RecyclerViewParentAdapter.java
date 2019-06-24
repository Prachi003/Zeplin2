package com.mindiii.lasross.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mindiii.lasross.R;
import com.mindiii.lasross.model.RecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewParentAdapter extends RecyclerView.Adapter<RecyclerViewParentAdapter.Holder> {

    private List<RecyclerViewModel> list, addList;
    private Context context;
    private RecyclerViewChildAdapter recyclerViewItemAdapter;

    public RecyclerViewParentAdapter(List <RecyclerViewModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewParentAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        RecyclerViewParentAdapter.Holder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_parent_adapter_layout, viewGroup, false);
        holder = new RecyclerViewParentAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewParentAdapter.Holder holder, int i) {

        addList = new ArrayList<>();

        RecyclerViewModel itemList = list.get(i);

        for (int j=1; j<=15; j++) {
            RecyclerViewModel itemList1 = new RecyclerViewModel("item "+j);
            addList.add(itemList1);
        }
        recyclerViewItemAdapter = new RecyclerViewChildAdapter(addList, context);

        holder.rvRecyclerItem.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        holder.rvRecyclerItem.setAdapter(recyclerViewItemAdapter);

        holder.tvItemName.setText(itemList.getName());
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder  {

        TextView tvItemName;
        RecyclerView rvRecyclerItem;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            rvRecyclerItem = itemView.findViewById(R.id.rvRecyclerItem);
        }
    }
}