package com.mindiii.lasross.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.mindiii.lasross.R;
import com.mindiii.lasross.adapter.CoatAdapter;
import com.mindiii.lasross.addtocart.model.DescriptionModel;
import com.mindiii.lasross.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class CoatsActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    List<DescriptionModel.HeaderListModel> headerLists;
    CoatAdapter coatAdapter;
    TextView tvWishList;
    ImageView ivFilterIcon, ivBackButton;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coat_activity_layout);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        headerLists = new ArrayList<>();

        String name = getIntent().getStringExtra("name");

        tvWishList = findViewById(R.id.tvWishList);

        ivFilterIcon = findViewById(R.id.ivFilterIcon);
        ivBackButton = findViewById(R.id.ivBackButton);

        tvWishList.setText(name);

        ivFilterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CoatsActivity.this, FiltersActivity.class));
            }
        });

        ivBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CoatsActivity.this, HomeActivity.class));
            }
        });

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.home_header_bg1));

        for(int i=1;i<=5;i++)
        {
            DescriptionModel.HeaderListModel itemList = new DescriptionModel.HeaderListModel("COATS AND JACKETS "+i,"Jacket Shinny Fit "+i,"$ 39.00");
            headerLists.add(itemList);
        }
        coatAdapter = new CoatAdapter(headerLists,this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(coatAdapter);
    }
}
