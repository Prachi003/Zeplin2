package com.mindiii.lasross.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mindiii.lasross.R;
import com.mindiii.lasross.model.MyAddressModel;

import java.util.List;

public class MyAddressAdapter extends RecyclerView.Adapter<MyAddressAdapter.Holder> {

    private List<MyAddressModel> list;
    private Context context;

    public MyAddressAdapter(List <MyAddressModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAddressAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        MyAddressAdapter.Holder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_address_adapter_layout, viewGroup, false);
        holder = new MyAddressAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAddressAdapter.Holder holder, int i) {

        MyAddressModel itemList = list.get(i);
        holder.tvName.setText(itemList.getUserName());
        holder.tvAddress.setText(itemList.getUserAddress());
        holder.tvPinCode.setText(itemList.getPinCode());
        holder.tvNumber.setText(itemList.getMobile());
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder  {

        TextView tvName, tvAddress, tvPinCode, tvNumber;

        public Holder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvPinCode = itemView.findViewById(R.id.tvPinCode);
            tvNumber = itemView.findViewById(R.id.tvNumber);

        }

    }
}
