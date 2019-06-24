package com.mindiii.lasross.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.mindiii.lasross.R;
import com.mindiii.lasross.adapter.MyOrdersAdapter;
import com.mindiii.lasross.model.MyOrdersModel;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersActivity21 extends AppCompatActivity {

    RecyclerView rvWishList;
    List<MyOrdersModel> myOrdersModels;
    MyOrdersAdapter myOrdersAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_order_activity_layout_21);

        rvWishList = findViewById(R.id.rvWishList);
        rvWishList.setHasFixedSize(true);

        myOrdersModels = new ArrayList<>();

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.home_header_bg1));


        for (int i = 1; i <= 5; i++) {
            MyOrdersModel itemList = new MyOrdersModel("COATS AND JACKETS " + i, "Jackets Shinny Fit ", "Delivered on 31st.Aug 2019");
            myOrdersModels.add(itemList);
        }
        myOrdersAdapter = new MyOrdersAdapter(myOrdersModels, this);
        rvWishList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvWishList.setAdapter(myOrdersAdapter);
    }
}
