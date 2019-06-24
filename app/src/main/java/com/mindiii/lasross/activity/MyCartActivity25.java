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
import com.mindiii.lasross.adapter.MyCartAdapter;
import com.mindiii.lasross.model.MyCartModel;

import java.util.ArrayList;
import java.util.List;

public class MyCartActivity25 extends AppCompatActivity {

    RecyclerView recyclerView;
    MyCartAdapter myCartAdapter;
    List<MyCartModel> list;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_cart_activity_layout_25);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.home_header_bg1));

        list = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            MyCartModel model = new MyCartModel("COATS AND JACKETS","Jackets Shinny Fit","Large","$ 39.00");
            list.add(model);

        }
        myCartAdapter = new MyCartAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(myCartAdapter);
    }
}
