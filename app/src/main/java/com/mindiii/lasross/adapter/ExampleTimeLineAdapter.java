package com.mindiii.lasross.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mindiii.lasross.R;
//import com.example.zeplin.helper.VectorDrawableUtils;
import com.mindiii.lasross.model.TimeLineModel;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

public class ExampleTimeLineAdapter extends RecyclerView.Adapter<ExampleTimeLineAdapter.Holder> {

    private List<TimeLineModel> list;
    private Context context;

    public ExampleTimeLineAdapter(List<TimeLineModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ExampleTimeLineAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.item_timeline, null);
        return new Holder(view, i);
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleTimeLineAdapter.Holder holder, int i) {

    }
    /*
         private fun setMarker(holder: TimeLineViewHolder, drawableResId: Int, colorFilter: Int) {
         holder.timeline.marker = VectorDrawableUtils.getDrawable(holder.itemView.context, drawableResId, ContextCompat.getColor(holder.itemView.context, colorFilter))
    }*/

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder  {

       /* public Holder(@NonNull View itemView) {
            super(itemView);
        }*/

       TimelineView timeline, text_timeline_date, text_timeline_title;
        public Holder(View itemView, int viewType) {
            super(itemView);
            timeline = itemView.findViewById(R.id.timeline);
            text_timeline_date = itemView.findViewById(R.id.text_timeline_date);
            text_timeline_title = itemView.findViewById(R.id.text_timeline_title);


            timeline.initLine(viewType);
        }
    }
}
