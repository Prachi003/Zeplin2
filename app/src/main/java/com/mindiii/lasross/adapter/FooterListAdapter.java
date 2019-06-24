package com.mindiii.lasross.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindiii.lasross.R;
import com.mindiii.lasross.helper.RoundRectCornerImageView;
import com.mindiii.lasross.helper.RoundedTransformation;
import com.mindiii.lasross.model.FooterListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FooterListAdapter extends RecyclerView.Adapter<FooterListAdapter.Holder> {

    private List<FooterListModel> list;
    private Context context;
    public static final int TYPE_HEAD = 0;
    public static final int TYPE_LIST = 1;
    int layoutPosition;

    public FooterListAdapter(List <FooterListModel> list, Context context) {
        this.list = list;
        this.context = context;
        this.layoutPosition = layoutPosition;
    }

    @NonNull
    @Override
    public FooterListAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        FooterListAdapter.Holder holder;

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footer_list_layout, viewGroup, false);
            holder = new FooterListAdapter.Holder(view);
            return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FooterListAdapter.Holder holder, int i) {

            FooterListModel itemList = list.get(i);
            holder.tvItemName.setText(itemList.getItemName());
            holder.tvItemPrice.setText(itemList.getItemPrice());
            holder.btnShopNow.setText("Shop Now");

        if (itemList.getPicURL().isEmpty()) {
            holder.imageView.setImageResource(R.drawable.dress2);
        }
        else {
            Picasso.with(context)
                    .load(itemList.getPicURL())
                    .resize(800,500)
                    .transform(new RoundedTransformation(50, 0))
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder  {

        ImageView imageView;
        TextView tvItemName, tvItemPrice;
        Button btnShopNow;


        public Holder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivBgImage);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            btnShopNow = itemView.findViewById(R.id.btnShopNow);
        }

    }
}
