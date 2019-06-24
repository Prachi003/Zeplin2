package com.mindiii.lasross.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.mindiii.lasross.R;
import com.mindiii.lasross.adapter.ExampleTimeLineAdapter;
import com.mindiii.lasross.model.TimeLineModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity22 extends AppCompatActivity {

    ImageView ivImage;
    List<TimeLineModel> mDataList;
    ExampleTimeLineAdapter exampleTimeLineAdapter;
    RecyclerView recyclerView;
    SeekBar seekBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail_activity_layout_22);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.home_header_bg1));
        ivImage = findViewById(R.id.ivImage);
        mDataList = new ArrayList<>();
        /*setDataListItems();
        initRecyclerView();*/

        Picasso.with(this)
                .load(R.drawable.dress1)
                .resize(500, 650)
                .into(ivImage);

        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
            }
        });
    }

   /* public void setDataListItems(){
        mDataList.add(new TimeLineModel("Item successfully delivered", "", "INACTIVE"));
        mDataList.add(new TimeLineModel("Courier is out to delivery your order", "", "ACTIVE"));
        mDataList.add(new TimeLineModel("Item has reached courier facility at New Delhi", "", "COMPLETED"));
    }

    public  void initRecyclerView(){
        exampleTimeLineAdapter = new ExampleTimeLineAdapter(mDataList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(exampleTimeLineAdapter);
    }*/
}

