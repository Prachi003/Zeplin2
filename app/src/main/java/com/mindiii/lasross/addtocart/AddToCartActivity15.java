package com.mindiii.lasross.addtocart;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.mindiii.lasross.R;
import com.mindiii.lasross.activity.RelatedProductActivity16;
import com.mindiii.lasross.adapter.ViewPagerAdapter;

public class AddToCartActivity15 extends AppCompatActivity {

    ViewPager viewPager;
    TextView tvStartNo;
    private int[] layouts = {R.layout.swipe_layout_1,R.layout.swipe_layout_2,R.layout.swipe_layout_3};
    //private int[] images = {R.drawable.splash_bg,R.drawable.dress1,R.drawable.dress2};
    private ViewPagerAdapter viewPagerAdapter;
    LinearLayout dotLayout;
    private ImageView[] ivRhombus;
    LinearLayout llAddToCart;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_cart_activity_layout_15);

        llAddToCart = findViewById(R.id.llAddToCart);

        llAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddToCartActivity15.this, RelatedProductActivity16.class));
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        /*Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        viewPager = findViewById(R.id.viewPager);
        tvStartNo = findViewById(R.id.tvStartNo);

        tvStartNo.setText("("+30+")");

        //viewPagerAdapter = new ViewPagerAdapter(images,this);
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
                /*Picasso.with(this)
                        .load(R.drawable.dress2)
                        .resize(600, 600)
                        .transform(new RoundedTransformation(50, 0))
                        .into(ivRhombus[i]);*/
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
