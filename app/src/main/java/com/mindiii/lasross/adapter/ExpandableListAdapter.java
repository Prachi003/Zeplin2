package com.mindiii.lasross.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindiii.lasross.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHashMap;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String,List<String>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group_api_layout,null);
        }

        TextView tvHeaderName ;
        View tvLine;//, group_devider_layout;
        ImageView ivHeaderImage, ivUpAndDown;

        tvHeaderName = convertView.findViewById(R.id.tvHeaderName);

        tvLine = convertView.findViewById(R.id.tvLine);
        //group_devider_layout = convertView.findViewById(R.id.group_devider_layout);

        ivHeaderImage = convertView.findViewById(R.id.ivHeaderImage);
        ivUpAndDown = convertView.findViewById(R.id.ivUpAndDown);

        tvHeaderName.setText(headerTitle);

        /*if (groupPosition==3 || groupPosition==7 ){
            group_devider_layout.setVisibility(View.VISIBLE);
        }
        else{
            group_devider_layout.setVisibility(View.GONE);
        }*/


        if(headerTitle.equals("Home")) {
            ivHeaderImage.setImageResource(R.drawable.shop_icon);
            tvLine.setVisibility(View.VISIBLE);
            //group_devider_layout.setVisibility(View.GONE);
            /*if(isExpanded){
                tvHeaderName.setTextColor(Color.BLACK);
                ivUpAndDown.setImageResource(R.drawable.down_arrow);
                tvLine.setVisibility(View.GONE);
                //group_devider_layout.setVisibility(View.GONE);
            }
            else {
                tvHeaderName.setTextColor(Color.GRAY);
                ivUpAndDown.setImageResource(R.drawable.right_arrow);
            }*/
        }


        if(headerTitle.equals("Shop")) {
            ivHeaderImage.setImageResource(R.drawable.shop_icon);
            tvLine.setVisibility(View.VISIBLE);
            //group_devider_layout.setVisibility(View.GONE);
            /*if(isExpanded){
                tvHeaderName.setTextColor(Color.BLACK);
                ivUpAndDown.setImageResource(R.drawable.down_arrow);
                tvLine.setVisibility(View.GONE);
                //group_devider_layout.setVisibility(View.GONE);
            }
            else {
                tvHeaderName.setTextColor(Color.GRAY);
                ivUpAndDown.setImageResource(R.drawable.right_arrow);
            }*/
        }


        if(headerTitle.equals("Men")) {
            ivHeaderImage.setImageResource(R.drawable.men_icon);
            tvLine.setVisibility(View.VISIBLE);
            //group_devider_layout.setVisibility(View.GONE);
            if(isExpanded){
                tvHeaderName.setTextColor(Color.BLACK);
                ivUpAndDown.setImageResource(R.drawable.down_arrow);
                tvLine.setVisibility(View.GONE);
                //group_devider_layout.setVisibility(View.GONE);
            }
            else {
                tvHeaderName.setTextColor(Color.GRAY);
                ivUpAndDown.setImageResource(R.drawable.right_arrow);
            }
        }


        if(headerTitle.equals("Women")) {
            ivHeaderImage.setImageResource(R.drawable.women_icon);
            tvLine.setVisibility(View.GONE);
            if(isExpanded){
                tvHeaderName.setTextColor(Color.BLACK);
                ivUpAndDown.setImageResource(R.drawable.down_arrow);
                //tvLine.setVisibility(View.GONE);
                //group_devider_layout.setVisibility(View.GONE);
            }
            else {
                tvHeaderName.setTextColor(Color.GRAY);
                ivUpAndDown.setImageResource(R.drawable.right_arrow);
                //group_devider_layout.setVisibility(View.VISIBLE);
            }
        }


       /* if(headerTitle.equals("Kids")) {
            ivHeaderImage.setImageResource(R.drawable.kids_icon);
            tvLine.setVisibility(View.GONE);
            //group_devider_layout.setVisibility(View.VISIBLE);
            if(isExpanded){
                tvHeaderName.setTextColor(Color.BLACK);
                ivUpAndDown.setImageResource(R.drawable.down_arrow);
                group_devider_layout.setVisibility(View.GONE);
            }
            else {
                tvHeaderName.setTextColor(Color.GRAY);
                ivUpAndDown.setImageResource(R.drawable.right_arrow);
                group_devider_layout.setVisibility(View.VISIBLE);
            }
        }*/


        return  convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childText = (String) getChild(groupPosition,childPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_api_layout,null);
        }

        TextView tvHeaderName = convertView.findViewById(R.id.tvHeaderName);
        View tvChildLine = convertView.findViewById(R.id.tvChildLine);
       // View child_devider_layout = convertView.findViewById(R.id.child_devider_layout);

        tvHeaderName.setText(childText);

       /* if(groupPosition==3 || groupPosition==7)
        {
            if(childPosition==3){
                child_devider_layout.setVisibility(View.VISIBLE);
                tvChildLine.setVisibility(View.GONE);
            }
            else
                child_devider_layout.setVisibility(View.GONE);
        }
        else
            child_devider_layout.setVisibility(View.GONE);*/

        if(isLastChild) {
            tvChildLine.setVisibility(View.VISIBLE);
        }

        else {
            tvChildLine.setVisibility(View.GONE);
        }

        return  convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

