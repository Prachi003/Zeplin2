package com.mindiii.lasross.adapter;

import android.content.Context;
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
import com.mindiii.lasross.helper.RoundRectCornerImageView;
import com.mindiii.lasross.home.interfc.HeaderInterface;
import com.mindiii.lasross.model.HeaderListModel1;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HeaderListAdapter extends RecyclerView.Adapter<HeaderListAdapter.Holder> {

    private List<HeaderListModel1> list;
    private Context context;
    public static final int TYPE_HEAD = 0;
    public static final int TYPE_LIST = 1;
    int layoutPosition;
    private HeaderInterface headerInterface;

    public HeaderListAdapter(List <HeaderListModel1> list, Context context, HeaderInterface headerInterface) {
        this.list = list;
        this.context = context;
        this.layoutPosition = layoutPosition;
        this.headerInterface = headerInterface;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        Holder holder;

        if (i == TYPE_LIST)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_list_layout, viewGroup, false);
            holder = new Holder(view,i);
            return holder;
        }

        else if(i == TYPE_HEAD)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_main_layout, viewGroup, false);
            holder = new Holder(view,i);
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {

        if(holder.view_type == TYPE_LIST)
        {
            HeaderListModel1 itemList = list.get(i-1);

            holder.tvItemNameVariety.setText(itemList.getItemNameVariety());
            holder.tvItemName.setText(itemList.getItemName());
            holder.tvItemPrice.setText(itemList.getItemPrice());
            holder.fullLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    headerInterface.onClickListener(i);
                }
            });
            if (itemList.getPicURL().isEmpty()) {
                holder.roundRectCornerImageView.setImageResource(R.drawable.dress2);
            }
            else {
                Picasso.with(context)
                        .load(itemList.getPicURL())
                        //.transform(new RoundedTransformation(50, 0))
                        .into(holder.roundRectCornerImageView);
            }
        }

        else if(holder.view_type == TYPE_HEAD)
        {
            holder.tvUpTo50.setText("Up to 50% OFF !");
            holder.tvMessage.setText("Don't miss out on some very special items at extraordinary sale prices. For a limited time!");
            holder.btnPick.setText("Pick up a Bargain");
            holder.ivDiscount.setBackgroundResource(R.drawable.ic_logo);
        }
    }

    @Override
    public int getItemCount() {

        return list.size()+1;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RoundRectCornerImageView roundRectCornerImageView;
        TextView tvItemNameVariety, tvItemName, tvItemPrice;
        LinearLayout fullLayout;

        TextView tvUpTo50, tvMessage;
        Button btnPick;
        ImageView ivDiscount;

        int view_type;

        public Holder(@NonNull View itemView, int viewType) {
            super(itemView);

            if(viewType == TYPE_LIST)
            {
                tvItemNameVariety = itemView.findViewById(R.id.tvItemNameVariety);
                tvItemName = itemView.findViewById(R.id.tvItemName);
                tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
                fullLayout = itemView.findViewById(R.id.fullLayout);
                roundRectCornerImageView = itemView.findViewById(R.id.ivImage);
                view_type = 1;
            }
            else if (viewType == TYPE_HEAD)
            {
                tvUpTo50 = itemView.findViewById(R.id.tvUpTo50);
                tvMessage = itemView.findViewById(R.id.tvMessage);
                btnPick = itemView.findViewById(R.id.btnPick);
                ivDiscount = itemView.findViewById(R.id.ivDiscount);
                view_type = 0;
            }
        }

        @Override
        public void onClick(View v) {
            layoutPosition = getAdapterPosition();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEAD;

        return TYPE_LIST;
    }
}