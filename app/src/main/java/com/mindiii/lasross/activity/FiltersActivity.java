package com.mindiii.lasross.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.mindiii.lasross.R;
import com.mindiii.lasross.adapter.FilterColorAdapter;
import com.mindiii.lasross.adapter.FilterSizeAdapter;
import com.mindiii.lasross.model.FilterColorModel;
import com.mindiii.lasross.model.FilterSizeModel;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;
import java.util.List;

public class FiltersActivity extends AppCompatActivity {

    List<FilterSizeModel> sizeModelList;
    FilterSizeAdapter filterSizeAdapter;

    List<FilterColorModel> colorModelList;
    FilterColorAdapter filterColorAdapter;

    RecyclerView recyclerViewSize, recyclerViewColor;
    TextView tvItemSize, tvItemColor, tvItemPrice, tvMinPriceRange, tvMaxPriceRange;
    ImageView ivItemSizeArrow, ivItemColorArrow, ivItemPriceArrow, ivItemSizeIcon, ivItemColorIcon, ivItemPriceIcon;
    LinearLayout llItemSize, llItemColor, llItemPrice, llSizeTouch, llColorTouch, llPriceTouch, llSearchlayout;
    CheckBox cbLarge, cbMedium, cbXLarge, cbSmall, cbBlue, cbGreen, cbGrey, cbOrange, cbRed;
    RelativeLayout rvLarge, rvMedium, rvXLarge, rvSmall, rvBlue, rvGreen, rvGrey, rvOrange, rvRed;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filters_activity_layout_12);

        sizeModelList = new ArrayList<>();

        RangeSeekBar<Integer> seekBar = new RangeSeekBar<Integer>(this);
        seekBar.setRangeValues(0, 100);

        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                Toast.makeText(getApplicationContext(), minValue + "-" + maxValue, Toast.LENGTH_LONG).show();
            }
        });

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.home_header_bg1));

        seekBar.setNotifyWhileDragging(true);

        recyclerViewSize = findViewById(R.id.recyclerViewSize);
        recyclerViewColor = findViewById(R.id.recyclerViewColor);

        tvItemSize = findViewById(R.id.tvItemSize);
        tvItemColor = findViewById(R.id.tvItemColor);
        tvItemPrice = findViewById(R.id.tvItemPrice);
        tvMinPriceRange = findViewById(R.id.tvMinPriceRange);
        tvMaxPriceRange = findViewById(R.id.tvMaxPriceRange);

        ivItemSizeArrow = findViewById(R.id.ivItemSizeArrow);
        ivItemColorArrow = findViewById(R.id.ivItemColorArrow);
        ivItemPriceArrow = findViewById(R.id.ivItemPriceArrow);
        ivItemSizeIcon = findViewById(R.id.ivItemSizeIcon);
        ivItemColorIcon = findViewById(R.id.ivItemColorIcon);
        ivItemPriceIcon = findViewById(R.id.ivItemPriceIcon);

        llItemSize = findViewById(R.id.llItemSize);
        llItemColor = findViewById(R.id.llItemColor);
        llItemPrice = findViewById(R.id.llItemPrice);
        llSizeTouch = findViewById(R.id.llSizeTouch);
        llColorTouch = findViewById(R.id.llColorTouch);
        llPriceTouch = findViewById(R.id.llPriceTouch);
        llSearchlayout = findViewById(R.id.llSearchlayout);

        rvLarge = findViewById(R.id.rvLarge);
        rvMedium = findViewById(R.id.rvMedium);
        rvXLarge = findViewById(R.id.rvXLarge);
        rvSmall = findViewById(R.id.rvSmall);
        rvBlue = findViewById(R.id.rvBlue);
        rvGreen = findViewById(R.id.rvGreen);
        rvGrey = findViewById(R.id.rvGrey);
        rvOrange= findViewById(R.id.rvOrange);
        rvRed = findViewById(R.id.rvRed);

        cbLarge = findViewById(R.id.cbLarge);
        cbMedium = findViewById(R.id.cbMedium);
        cbXLarge = findViewById(R.id.cbXLarge);
        cbSmall = findViewById(R.id.cbSmall);

        cbBlue = findViewById(R.id.cbBlue);
        cbGreen = findViewById(R.id.cbGreen);
        cbGrey = findViewById(R.id.cbGrey);
        cbOrange = findViewById(R.id.cbOrange);
        cbRed = findViewById(R.id.cbRed);


        final Typeface semi_bold = ResourcesCompat.getFont(FiltersActivity.this, R.font.ibmplexsans_semibold);
        final Typeface regular = ResourcesCompat.getFont(FiltersActivity.this, R.font.ibmplexsans_regular);

        CrystalRangeSeekbar crystalRangeSeekbar = findViewById(R.id.rangeSeekbar3);

        crystalRangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMinPriceRange.setText(minValue+"");
                tvMaxPriceRange.setText(maxValue+"");
                Log.e("The value is", "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
            }
        });

        /////////////////SIZE ADAPTER/////////////////////
        for(int i=1;i<=50;i++)
        {
            FilterSizeModel itemList = new FilterSizeModel("large (l)","1252");
            sizeModelList.add(itemList);
        }
        filterSizeAdapter = new FilterSizeAdapter(sizeModelList,this);
        recyclerViewColor.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewColor.setAdapter(filterSizeAdapter);
        //////////////////////////////////////////////////


        /*/////////////////SIZE ADAPTER/////////////////////
        for(int i=1;i<=5;i++)
        {
            HeaderListModel itemList = new HeaderListModel("COATS AND JACKETS "+i,"Jacket Shinny Fit "+i,"$ 39.00");
            headerLists.add(itemList);
        }
        coatAdapter = new CoatAdapter(headerLists,this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(coatAdapter);
        ////////////////////////////////////////////////*/


        /*llItemSize.setOnClickListener(new View.OnClickListener() {
            @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                llSizeTouch.setVisibility(View.VISIBLE);
                llColorTouch.setVisibility(View.GONE);
                llPriceTouch.setVisibility(View.GONE);
                llSearchlayout.setVisibility(View.VISIBLE);

                llItemSize.setBackgroundColor(Color.parseColor("#fe682e"));
                tvItemSize.setTextColor(Color.WHITE);
                tvItemSize.setTypeface(semi_bold);
                ivItemSizeIcon.setColorFilter(Color.WHITE);
                ivItemSizeArrow.setVisibility(View.VISIBLE);
                //setTextViewDrawableColor(tvItemSize, Color.parseColor("#ffffff"));

                llItemColor.setBackgroundColor(Color.parseColor("#f9f7f7"));
                tvItemColor.setTextColor(Color.BLACK);
                tvItemColor.setTypeface(regular);
                ivItemColorIcon.setColorFilter(Color.BLACK);
                ivItemColorArrow.setVisibility(View.INVISIBLE);
                //setTextViewDrawableColor(tvItemColor, Color.BLACK);

                llItemPrice.setBackgroundColor(Color.parseColor("#f9f7f7"));
                tvItemPrice.setTextColor(Color.BLACK);
                tvItemPrice.setTypeface(regular);
                ivItemPriceIcon.setColorFilter(Color.BLACK);
                ivItemPriceArrow.setVisibility(View.INVISIBLE);
                //setTextViewDrawableColor(tvItemPrice, Color.BLACK);
            }
        });*/

        llItemColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewSize.setVisibility(View.VISIBLE);
                llItemColor.setBackgroundColor(Color.parseColor("#fe682e"));
                tvItemColor.setTextColor(Color.WHITE);
                tvItemColor.setTypeface(semi_bold);
                ivItemColorIcon.setColorFilter(Color.WHITE);
                ivItemColorArrow.setVisibility(View.VISIBLE);
                // setTextViewDrawableColor(tvItemColor, Color.WHITE);

                llItemSize.setBackgroundColor(Color.parseColor("#f9f7f7"));
                tvItemSize.setTextColor(Color.BLACK);
                tvItemSize.setTypeface(regular);
                ivItemSizeIcon.setColorFilter(Color.BLACK);
                ivItemSizeArrow.setVisibility(View.INVISIBLE);
                //setTextViewDrawableColor(tvItemSize, Color.BLACK);

                llItemPrice.setBackgroundColor(Color.parseColor("#f9f7f7"));
                tvItemPrice.setTextColor(Color.BLACK);
                tvItemPrice.setTypeface(regular);
                ivItemPriceIcon.setColorFilter(Color.BLACK);
                ivItemPriceArrow.setVisibility(View.INVISIBLE);
                //setTextViewDrawableColor(tvItemPrice, Color.BLACK);
            }
        });
       /* llItemColor.setOnClickListener(new View.OnClickListener() {
            @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                llSizeTouch.setVisibility(View.GONE);
                llColorTouch.setVisibility(View.VISIBLE);
                llPriceTouch.setVisibility(View.GONE);
                llSearchlayout.setVisibility(View.VISIBLE);


                llItemColor.setBackgroundColor(Color.parseColor("#fe682e"));
                tvItemColor.setTextColor(Color.WHITE);
                tvItemColor.setTypeface(semi_bold);
                ivItemColorIcon.setColorFilter(Color.WHITE);
                ivItemColorArrow.setVisibility(View.VISIBLE);
               // setTextViewDrawableColor(tvItemColor, Color.WHITE);

                llItemSize.setBackgroundColor(Color.parseColor("#f9f7f7"));
                tvItemSize.setTextColor(Color.BLACK);
                tvItemSize.setTypeface(regular);
                ivItemSizeIcon.setColorFilter(Color.BLACK);
                ivItemSizeArrow.setVisibility(View.INVISIBLE);
                //setTextViewDrawableColor(tvItemSize, Color.BLACK);

                llItemPrice.setBackgroundColor(Color.parseColor("#f9f7f7"));
                tvItemPrice.setTextColor(Color.BLACK);
                tvItemPrice.setTypeface(regular);
                ivItemPriceIcon.setColorFilter(Color.BLACK);
                ivItemPriceArrow.setVisibility(View.INVISIBLE);
                //setTextViewDrawableColor(tvItemPrice, Color.BLACK);
            }
        });*/



        llItemPrice.setOnClickListener(new View.OnClickListener() {
            @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                llPriceTouch.setVisibility(View.VISIBLE);
                llSearchlayout.setVisibility(View.GONE);
                llSizeTouch.setVisibility(View.GONE);
                llColorTouch.setVisibility(View.GONE);
                //llSearchlayout.setVisibility(View.GONE);

                llItemPrice.setBackgroundColor(Color.parseColor("#fe682e"));
                tvItemPrice.setTextColor(Color.WHITE);
                tvItemPrice.setTypeface(semi_bold);
                ivItemPriceIcon.setColorFilter(Color.WHITE);
                ivItemPriceArrow.setVisibility(View.VISIBLE);
                // setTextViewDrawableColor(tvItemColor, Color.WHITE);

                llItemSize.setBackgroundColor(Color.parseColor("#f9f7f7"));
                tvItemSize.setTextColor(Color.BLACK);
                tvItemSize.setTypeface(regular);
                ivItemSizeIcon.setColorFilter(Color.BLACK);
                ivItemSizeArrow.setVisibility(View.INVISIBLE);
                //setTextViewDrawableColor(tvItemSize, Color.BLACK);

                llItemColor.setBackgroundColor(Color.parseColor("#f9f7f7"));
                tvItemColor.setTextColor(Color.BLACK);
                tvItemColor.setTypeface(regular);
                ivItemColorIcon.setColorFilter(Color.BLACK);
                ivItemColorArrow.setVisibility(View.INVISIBLE);
                //setTextViewDrawableColor(tvItemPrice, Color.BLACK);
            }
        });

        rvLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbLarge.isChecked())
                {
                    cbLarge.setChecked(false);
                    cbLarge.setTypeface(regular);
                }
                else
                {
                    cbLarge.setChecked(true);
                    cbLarge.setTypeface(semi_bold);
                }

            }
        });

        rvMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbMedium.isChecked())
                {
                    cbMedium.setChecked(false);
                    cbMedium.setTypeface(regular);
                }
                else
                {
                    cbMedium.setChecked(true);
                    cbMedium.setTypeface(semi_bold);
                }

            }
        });


        rvXLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbXLarge.isChecked())
                {
                    cbXLarge.setChecked(false);
                    cbXLarge.setTypeface(regular);
                }
                else
                {
                    cbXLarge.setChecked(true);
                    cbXLarge.setTypeface(semi_bold);
                }

            }
        });


        rvSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbSmall.isChecked())
                {
                    cbSmall.setChecked(false);
                    cbSmall.setTypeface(regular);
                }
                else
                {
                    cbSmall.setChecked(true);
                    cbSmall.setTypeface(semi_bold);
                }

            }
        });

        rvBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbBlue.isChecked())
                { cbBlue.setChecked(false);  cbBlue.setTypeface(regular); }
                else
                { cbBlue.setChecked(true);  cbBlue.setTypeface(semi_bold); }
            }
        });

        rvGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbGreen.isChecked())
                { cbGreen.setChecked(false);  cbGreen.setTypeface(regular); }
                else
                { cbGreen.setChecked(true);  cbGreen.setTypeface(semi_bold); }
            }
        });

        rvGrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbGrey.isChecked())
                { cbGrey.setChecked(false);  cbGrey.setTypeface(regular); }
                else
                { cbGrey.setChecked(true);  cbGrey.setTypeface(semi_bold); }
            }
        });

        rvOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbOrange.isChecked())
                { cbOrange.setChecked(false);  cbOrange.setTypeface(regular); }
                else
                { cbOrange.setChecked(true);  cbOrange.setTypeface(semi_bold); }
            }
        });

        rvRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbRed.isChecked())
                { cbRed.setChecked(false);  cbRed.setTypeface(regular); }
                else
                { cbRed.setChecked(true);  cbRed.setTypeface(semi_bold); }
            }
        });
    }

    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.M)
    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                //drawable.setColorFilter(new PorterDuffColorFilter(getColor(color), PorterDuff.Mode.SRC_IN));
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));

            }
        }
    }
}
