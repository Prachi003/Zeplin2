package com.mindiii.lasross.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mindiii.lasross.R;
import com.mindiii.lasross.adapter.RecyclerViewParentAdapter;
import com.mindiii.lasross.model.RecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView rvHeading;
    RecyclerViewParentAdapter recyclerViewAdapter;
    List<RecyclerViewModel> list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity_layout);

        rvHeading = findViewById(R.id.rvHeading);

        list = new ArrayList<>();
        for (int i=1; i<=5; i++) {
            RecyclerViewModel itemList = new RecyclerViewModel("Item "+i);
            list.add(itemList);
        }
        recyclerViewAdapter = new RecyclerViewParentAdapter(list, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(RecyclerViewActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvHeading.setLayoutManager(layoutManager);

        rvHeading.setAdapter(recyclerViewAdapter);
    }

}
