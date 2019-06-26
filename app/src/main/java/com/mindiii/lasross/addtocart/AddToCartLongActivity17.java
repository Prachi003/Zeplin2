package com.mindiii.lasross.addtocart;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mindiii.lasross.R;
import com.mindiii.lasross.addtocart.adapter.ColorAdapter;
import com.mindiii.lasross.addtocart.adapter.DescriptionAdapter;
import com.mindiii.lasross.addtocart.adapter.RelatedProductAdapter;
import com.mindiii.lasross.adapter.ViewPagerAdapter;
import com.mindiii.lasross.addtocart.model.ColorModel;
import com.mindiii.lasross.addtocart.model.DescriptionModel;
import com.mindiii.lasross.addtocart.model.ProductDetail;
import com.mindiii.lasross.base.BaseActivity;
import com.mindiii.lasross.loginregistration.RegistrationActivity;
import com.mindiii.lasross.sessionNew.Session;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddToCartLongActivity17 extends BaseActivity {

    private ViewPager viewPager;
    private TextView tvStartNo;
    private int[] layouts = {R.layout.swipe_layout_1,R.layout.swipe_layout_2,R.layout.swipe_layout_3};
    private String[] images;
    private ViewPagerAdapter viewPagerAdapter;
    private LinearLayout dotLayout;
    private ImageView[] ivRhombus;
    private ImageView ivBackground;

    private Gson gson;

    private Session session;

    private ProgressDialog progressDialog;

    private LinearLayout llAddToCart;

    private RecyclerView rvRecycler, rvRecyclerDescription, rvRecyclerColor;

    private RelatedProductAdapter relatedProductAdapter;
    private DescriptionAdapter descriptionAdapter;
    private ColorAdapter colorAdapter;

    private List<DescriptionModel.HeaderListModel> list;
    private List<DescriptionModel> descriptionList;
    private List<ColorModel> colorList;

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

        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        rvRecycler = findViewById(R.id.rvRecycler);
        rvRecyclerDescription = findViewById(R.id.rvRecyclerDescription);
        rvRecyclerColor = findViewById(R.id.rvRecyclerColor);

        session = new Session(this);

        llAddToCart = findViewById(R.id.llAddToCart);

        viewPager = findViewById(R.id.viewPager);

        tvStartNo = findViewById(R.id.tvStartNo);

        progressDialog = new ProgressDialog(this);

        ///////////////////PRODUCT DETAIL API METHod/////////////////////
        String id = getIntent().getStringExtra("id");
        String featuredImage = getIntent().getStringExtra("featuredImage");
        //int picInt = Integer.parseInt(featuredImage);
        images = new String[]{featuredImage};

        productDetailApi(id);
        /////////////////////////////////////////////////////////////////


        ///////////////////COLOR RECYCLER VIEW///////////////////////////
        colorList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            ColorModel itemList = new ColorModel(Color.parseColor("#bdbdbd"));
            colorList.add(itemList);
        }
        colorAdapter = new ColorAdapter(colorList, this);
        rvRecyclerColor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvRecyclerColor.setAdapter(colorAdapter);
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

    private void productDetailApi(final String id){
        progressDialog.show();
        progressDialog.setMessage("loading.....");

        HashMap<String, String> paramas = new HashMap();
        paramas.put("productId",id);

        getDataManager().getProductDetail(getDataManager().getHeader(),paramas).getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                ProductDetail productDetail = gson.fromJson(response, ProductDetail.class);
                progressDialog.dismiss();
                Log.e("id is", id );
            }

            @Override
            public void onError(ANError anError) {
                progressDialog.dismiss();
                //Toast.makeText(AddToCartLongActivity17.this, "User already exists",Toast.LENGTH_LONG).show();
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
