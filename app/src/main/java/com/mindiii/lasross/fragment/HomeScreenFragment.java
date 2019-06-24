package com.mindiii.lasross.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mindiii.lasross.R;
import com.mindiii.lasross.adapter.FooterListAdapter;
import com.mindiii.lasross.adapter.HeaderListAdapter;
import com.mindiii.lasross.addtocart.model.DescriptionModel;
import com.mindiii.lasross.model.FooterListModel;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenFragment extends Fragment {

    RecyclerView rvListUp, rvListDown;
    List<DescriptionModel.HeaderListModel> headerLists;
    List<FooterListModel> footerLists;
    HeaderListAdapter headerListAdapter;
    FooterListAdapter footerListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        rvListUp = view.findViewById(R.id.rvListUp);
        rvListDown = view.findViewById(R.id.rvListDown);
        rvListUp.setHasFixedSize(true);
        rvListDown.setHasFixedSize(true);

        headerLists = new ArrayList<>();
        footerLists = new ArrayList<>();

        ///////////////////HEADER ADAPTER/////////////////////////
        for(int i=1;i<=5;i++)
        {
            DescriptionModel.HeaderListModel itemList = new DescriptionModel.HeaderListModel("COATS AND JACKETS "+i,"Jackets Shinny Fit "+i,"$ 39.00");
            headerLists.add(itemList);
        }
        //headerListAdapter = new HeaderListAdapter(headerLists,getContext());

        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(headerListAdapter.getItemViewType(position)){
                    case HeaderListAdapter.TYPE_HEAD:
                        return 2;

                    case HeaderListAdapter.TYPE_LIST:
                        return 1;

                    default:
                        return 1;
                }
            }
        });
        rvListUp.setLayoutManager(mLayoutManager);
        rvListUp.setAdapter(headerListAdapter);


        ///////////////////FOOTER ADAPTER/////////////////////////

        for(int i=1;i<=5;i++)
        {
            FooterListModel itemList = new FooterListModel("Holiday Style "+i,"from $50","");
            footerLists.add(itemList);
        }
        footerListAdapter = new FooterListAdapter(footerLists,getContext());
        rvListDown.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        rvListDown.setAdapter(footerListAdapter);


        return  view;
    }

    }
