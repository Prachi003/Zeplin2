package com.mindiii.lasross.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

import com.mindiii.lasross.R;
import com.mindiii.lasross.adapter.MyAddressAdapter;
import com.mindiii.lasross.model.MyAddressModel;

import java.util.ArrayList;
import java.util.List;

public class MyAddressesActivity38 extends AppCompatActivity {

    RecyclerView recyclerView;
    List<MyAddressModel> list;
    MyAddressAdapter myAddressAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_addresses_activty_38);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.home_header_bg1));

        recyclerView = findViewById(R.id.recyclerView);

        list = new ArrayList<>();

        for (int i = 1; i <= 2; i++) {
            MyAddressModel itemList = new MyAddressModel("Pearle Desrosier",
                    "Akshya Nagar 1st Block 1st Cross, Rammurthy nagar, Bangalore",
                    "PIN CODE - 560016",
                    "1234567898");
            list.add(itemList);
        }
        myAddressAdapter = new MyAddressAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(myAddressAdapter);

    }
}

