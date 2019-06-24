package com.mindiii.lasross.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import com.mindiii.lasross.R;
import com.mindiii.lasross.adapter.WishListAdapter;
import com.mindiii.lasross.addtocart.model.DescriptionModel;
import com.mindiii.lasross.interfc.WishlistListener;

import java.util.ArrayList;
import java.util.List;

public class WishListActivity20 extends AppCompatActivity {

    RecyclerView rvWishList;
    List<DescriptionModel.HeaderListModel> headerLists;
    WishListAdapter wishListAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish_list_activity_layout_20);

        rvWishList = findViewById(R.id.rvWishList);
        rvWishList.setHasFixedSize(true);
        headerLists = new ArrayList<>();
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.home_header_bg1));
        /*if (Build.VERSION.SDK_INT >=                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  21) {
            window.setStatusBarColor(Con                                                                            textCompat.getColor(this,R.color.home_header_bg1)); //status bar or the time bar at the top
        }*/
        for(int i=1;i<=5;i++)
        {
            DescriptionModel.HeaderListModel itemList = new DescriptionModel.HeaderListModel("COATS AND JACKETS "+i,"Jacket Shinny Fit "+i,"$ 39.00");
            headerLists.add(itemList);
        }
        wishListAdapter = new WishListAdapter(new WishlistListener() {
            @Override
            public void onClick() {
              startActivity(new Intent());
            }
        },headerLists, this);
        rvWishList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        rvWishList.setAdapter(wishListAdapter);
    }
}
