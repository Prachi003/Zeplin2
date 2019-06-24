package com.mindiii.lasross.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.mindiii.lasross.R;
import com.mindiii.lasross.addtocart.adapter.RelatedProductAdapter;
import com.mindiii.lasross.addtocart.model.DescriptionModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RelatedProductActivity16 extends AppCompatActivity {

    RecyclerView rvRecycler;
    RelatedProductAdapter relatedProductAdapter;
    List<DescriptionModel.HeaderListModel> list;
    ImageView ivImage;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.real_product_activity_layout_16);

        rvRecycler = findViewById(R.id.rvRecycler);
        ivImage = findViewById(R.id.ivImage);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        Picasso.with(this)
                .load(R.drawable.splash_bg)
                .resize(700, 800)
                .into(ivImage);


        list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            DescriptionModel.HeaderListModel itemList = new DescriptionModel.HeaderListModel("COATS AND JACKETS", "Jacket Shinny Fit","$ 39.00");
            list.add(itemList);

        }
        relatedProductAdapter = new RelatedProductAdapter(list, this);
        rvRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvRecycler.setAdapter(relatedProductAdapter);
    }
}
