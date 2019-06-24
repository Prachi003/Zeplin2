package com.mindiii.lasross.addtocart;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.mindiii.lasross.R;
import com.mindiii.lasross.addtocart.adapter.DescriptionAdapter;
import com.mindiii.lasross.addtocart.adapter.RelatedProductAdapter;
import com.mindiii.lasross.adapter.ViewPagerAdapter;
import com.mindiii.lasross.addtocart.model.ColorModel;
import com.mindiii.lasross.addtocart.model.DescriptionModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AddToCartLongActivity17 extends AppCompatActivity {

    ViewPager viewPager;
    TextView tvStartNo;
    private int[] layouts = {R.layout.swipe_layout_1,R.layout.swipe_layout_2,R.layout.swipe_layout_3};
    private int[] images = {R.drawable.splash_bg,R.drawable.dress1,R.drawable.dress2};
    private ViewPagerAdapter viewPagerAdapter;
    LinearLayout dotLayout;
    private ImageView[] ivRhombus;
    ImageView ivBackground;

    LinearLayout llAddToCart;

    RecyclerView rvRecycler, rvRecyclerDescription, rvRecyclerColor;
    RelatedProductAdapter relatedProductAdapter;

    DescriptionAdapter descriptionAdapter;
    List<DescriptionModel.HeaderListModel> list;
    List<DescriptionModel> descriptionList;
    //List<ColorModel> colorList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_cart_long_activity_layout_17);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        rvRecycler = findViewById(R.id.rvRecycler);
        rvRecyclerDescription = findViewById(R.id.rvRecyclerDescription);
        rvRecyclerColor = findViewById(R.id.rvRecyclerColor);

        llAddToCart = findViewById(R.id.llAddToCart);

        viewPager = findViewById(R.id.viewPager);

        tvStartNo = findViewById(R.id.tvStartNo);


        ///////////////////COLOR RECYCLER VIEW///////////////////////////
        /*colorList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            DescriptionModel.HeaderListModel itemList = new DescriptionModel.HeaderListModel("COATS AND JACKETS", "Jacket Shinny Fit","$ 39.00");
            list.add(itemList);
        }
        relatedProductAdapter = new RelatedProductAdapter(list, this);
        rvRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvRecycler.setAdapter(relatedProductAdapter);*/
        ////////////////////////////////////////////////////////////////




        ///////////////////UPPER RECYCLER VIEW///////////////////////////
        list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            DescriptionModel.HeaderListModel itemList = new DescriptionModel.HeaderListModel("COATS AND JACKETS", "Jacket Shinny Fit","$ 39.00");
            list.add(itemList);
        }
        relatedProductAdapter = new RelatedProductAdapter(list, this);
        rvRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvRecycler.setAdapter(relatedProductAdapter);
        ////////////////////////////////////////////////////////////////


        ///////////////////DESCRIPTION RECYCLER VIEW///////////////////////////
        descriptionList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            DescriptionModel itemList = new DescriptionModel("This item is ethically produced",
                    "Pellentesque habitant morbi tristique senectus et netus et malesuada " +
                            "fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, " +
                            "ultricies eget, tempor sit amet, ante. Donec eu libero sit amet " +
                            "quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.");
            descriptionList.add(itemList);
        }
        descriptionAdapter = new DescriptionAdapter(descriptionList, this);
        rvRecyclerDescription.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvRecyclerDescription.setAdapter(descriptionAdapter);
        /////////////////////////////////////////////////////////////////////


        ivBackground = findViewById(R.id.ivBackground);
        Picasso.with(this)
                .load(R.drawable.splash_bg)
                .resize(1000, 600)
                .into(ivBackground);

        /*Picasso.with(this)
                .load(R.drawable.splash_bg)
                .fit().centerCrop()
                .into(ivBackground);*/

        tvStartNo.setText("("+30+")");

        viewPagerAdapter = new ViewPagerAdapter(images,this);
        viewPager.setAdapter(viewPagerAdapter);

        dotLayout = findViewById(R.id.dotLayout);
        createDots(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                createDots(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private  void createDots(int position)
    {

        if(dotLayout!=null)
            dotLayout.removeAllViews();

        ivRhombus = new ImageView[layouts.length];

        for(int i=0;i<layouts.length;i++)
        {
            ivRhombus[i] = new ImageView(this);

            if(i == position){
                ivRhombus[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.rhombus_filled_shape));
            }
            else{
                ivRhombus[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.rhombus_unfilled_shape));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30,30);
            params.setMargins(6,0,6,0);

            dotLayout.addView(ivRhombus[i],params);

        }
    }
}
