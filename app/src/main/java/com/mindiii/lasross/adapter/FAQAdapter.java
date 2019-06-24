package com.mindiii.lasross.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mindiii.lasross.R;
import com.mindiii.lasross.model.FAQModel;

import java.util.List;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.Holder> {

    private List<FAQModel> list;
    private Context context;

    public FAQAdapter(List <FAQModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FAQAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        FAQAdapter.Holder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.faq_adapter_layout, viewGroup, false);
        holder = new FAQAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FAQAdapter.Holder holder, int i) {

        FAQModel itemList = list.get(i);
        holder.tvQuestion.setText(itemList.getQuestion());
        holder.tvAnswer.setText(itemList.getAnswer());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.tvAnswer.getVisibility()==View.VISIBLE)
                {
                    holder.ivUpIcon.setImageResource(R.drawable.down_black_arrow_icon);
                    holder.tvAnswer.setVisibility(View.GONE);
                }
                else
                {
                    holder.ivUpIcon.setImageResource(R.drawable.up_black_arrow_icon);
                    holder.tvAnswer.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder  {

        TextView tvQuestion, tvAnswer;
        ImageView ivUpIcon;
        RelativeLayout relativeLayout;

        public Holder(@NonNull View itemView) {
            super(itemView);

            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            tvAnswer = itemView.findViewById(R.id.tvAnswer);
            ivUpIcon = itemView.findViewById(R.id.ivUpIcon);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);

        }

    }
}

