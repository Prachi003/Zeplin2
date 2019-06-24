package com.mindiii.lasross.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.mindiii.lasross.R;

public class AddCartActivity37 extends AppCompatActivity {

    ImageView ivUnChecked, ivChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_cart_activty_37);

        ivUnChecked = findViewById(R.id.ivUnChecked);
        ivChecked = findViewById(R.id.ivChecked);

        ivUnChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ivUnChecked.getVisibility()== View.VISIBLE)
                {
                    ivChecked.setVisibility(View.VISIBLE);
                    ivUnChecked.setVisibility(View.GONE);
                }
            }
        });

        ivChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ivChecked.getVisibility()== View.VISIBLE)
                {
                    ivChecked.setVisibility(View.GONE);
                    ivUnChecked.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
