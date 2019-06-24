package com.mindiii.lasross.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mindiii.lasross.R;
import com.mindiii.lasross.home.HomeActivity;

public class SubscriptionActivity extends AppCompatActivity {

    LinearLayout llMiddleLayout, llRightLayout, llLeftLayout, llFreeDetails, llSilverDetails, llGoldenDetails;
    ImageView ivLeftImage, ivMiddleImage, ivRightImage;
    TextView tvLeftText, tvMiddleText, tvRightText, tvLeftPlan, tvMiddlePlan, tvRightPlan, tvSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscription_screen);

        llMiddleLayout = findViewById(R.id.llMiddleLayout);
        llRightLayout = findViewById(R.id.llRightLayout);
        llLeftLayout = findViewById(R.id.llLeftLayout);
        llFreeDetails = findViewById(R.id.llFreeDetails);
        llSilverDetails = findViewById(R.id.llSilverDetails);
        llGoldenDetails = findViewById(R.id.llGoldenDetails);

        ivLeftImage = findViewById(R.id.ivLeftImage);
        ivMiddleImage = findViewById(R.id.ivMiddleImage);
        ivRightImage = findViewById(R.id.ivRightImage);

        tvLeftText = findViewById(R.id.tvLeftText);
        tvMiddleText = findViewById(R.id.tvMiddleText);
        tvRightText = findViewById(R.id.tvRightText);
        tvLeftPlan = findViewById(R.id.tvLeftPlan);
        tvMiddlePlan = findViewById(R.id.tvMiddlePlan);
        tvRightPlan = findViewById(R.id.tvRightPlan);
        tvSubscribe = findViewById(R.id.tvSubscribe);

        tvSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SubscriptionActivity.this, HomeActivity.class));
                finish();
            }
        });

        llLeftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvLeftText.getText().toString().equalsIgnoreCase("Golden")) {
                    ivMiddleImage.setImageResource(R.drawable.goldenplan_icon_orange);
                    tvMiddleText.setText(R.string.golden_plan);
                    tvMiddlePlan.setVisibility(View.VISIBLE);

                    ivLeftImage.setImageResource(R.drawable.silverplan_icon_black);
                    tvLeftText.setText(" Silver ");
                    tvLeftPlan.setVisibility(View.VISIBLE);

                    ivRightImage.setImageResource(R.drawable.free_icon_black);
                    tvRightText.setText(R.string.free);
                    tvRightPlan.setVisibility(View.GONE);

                    llGoldenDetails.setVisibility(View.VISIBLE);
                    llSilverDetails.setVisibility(View.GONE);
                    llFreeDetails.setVisibility(View.GONE);
                } else if (tvLeftText.getText().toString().equalsIgnoreCase(" Silver ")) {
                    ivMiddleImage.setImageResource(R.drawable.silverplan_icon_orange);
                    tvMiddleText.setText(R.string.silver_plan);
                    tvMiddlePlan.setVisibility(View.VISIBLE);

                    ivLeftImage.setImageResource(R.drawable.free_icon_black);
                    tvLeftText.setText(R.string.free);
                    tvLeftPlan.setVisibility(View.GONE);

                    ivRightImage.setImageResource(R.drawable.goldenplan_icon_black);
                    tvRightText.setText(R.string.golden_plan);
                    tvRightPlan.setVisibility(View.VISIBLE);

                    llSilverDetails.setVisibility(View.VISIBLE);
                    llFreeDetails.setVisibility(View.GONE);
                    llGoldenDetails.setVisibility(View.GONE);
                } else if (tvLeftText.getText().toString().equalsIgnoreCase("Free")) {
                    ivMiddleImage.setImageResource(R.drawable.free_icon_orange);
                    tvMiddleText.setText(R.string.free);
                    tvMiddlePlan.setVisibility(View.GONE);

                    ivLeftImage.setImageResource(R.drawable.goldenplan_icon_black);
                    tvLeftText.setText(R.string.golden_plan);
                    tvLeftPlan.setVisibility(View.VISIBLE);

                    ivRightImage.setImageResource(R.drawable.silverplan_icon_black);
                    tvRightText.setText("  Silver ");
                    tvRightPlan.setVisibility(View.VISIBLE);

                    llFreeDetails.setVisibility(View.VISIBLE);
                    llSilverDetails.setVisibility(View.GONE);
                    llGoldenDetails.setVisibility(View.GONE);
                }
            }
        });


        llRightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvRightText.getText().toString().equalsIgnoreCase("Golden")) {
                    ivMiddleImage.setImageResource(R.drawable.goldenplan_icon_orange);
                    tvMiddleText.setText(R.string.golden_plan);
                    tvMiddlePlan.setVisibility(View.VISIBLE);

                    ivLeftImage.setImageResource(R.drawable.silverplan_icon_black);
                    tvLeftText.setText(" Silver ");
                    tvLeftPlan.setVisibility(View.VISIBLE);

                    ivRightImage.setImageResource(R.drawable.free_icon_black);
                    tvRightText.setText(R.string.free);
                    tvRightPlan.setVisibility(View.GONE);

                    llGoldenDetails.setVisibility(View.VISIBLE);
                    llSilverDetails.setVisibility(View.GONE);
                    llFreeDetails.setVisibility(View.GONE);
                } else if (tvRightText.getText().toString().equalsIgnoreCase("  Silver ")) {
                    ivMiddleImage.setImageResource(R.drawable.silverplan_icon_orange);
                    tvMiddleText.setText(R.string.silver_plan);
                    tvMiddlePlan.setVisibility(View.VISIBLE);

                    ivLeftImage.setImageResource(R.drawable.free_icon_black);
                    tvLeftText.setText(R.string.free);
                    tvLeftPlan.setVisibility(View.GONE);

                    ivRightImage.setImageResource(R.drawable.goldenplan_icon_black);
                    tvRightText.setText(R.string.golden_plan);
                    tvRightPlan.setVisibility(View.VISIBLE);

                    llSilverDetails.setVisibility(View.VISIBLE);
                    llFreeDetails.setVisibility(View.GONE);
                    llGoldenDetails.setVisibility(View.GONE);
                } else if (tvRightText.getText().toString().equalsIgnoreCase("Free")) {
                    ivMiddleImage.setImageResource(R.drawable.free_icon_orange);
                    tvMiddleText.setText(R.string.free);
                    tvMiddlePlan.setVisibility(View.GONE);

                    ivLeftImage.setImageResource(R.drawable.goldenplan_icon_black);
                    tvLeftText.setText(R.string.golden_plan);
                    tvLeftPlan.setVisibility(View.VISIBLE);

                    ivRightImage.setImageResource(R.drawable.silverplan_icon_black);
                    tvRightText.setText("  Silver ");
                    tvRightPlan.setVisibility(View.VISIBLE);

                    llFreeDetails.setVisibility(View.VISIBLE);
                    llSilverDetails.setVisibility(View.GONE);
                    llGoldenDetails.setVisibility(View.GONE);
                }
            }
        });
    }



    public void onRadioButtonClicked(View v) {
        RadioButton rb1 = findViewById(R.id.rbDefault);
        RadioButton rb2 = findViewById(R.id.rbPoplarity);
        RadioButton rb3 = findViewById(R.id.rbRating);

        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {

            case R.id.rbDefault:
                if (checked)
                    rb1.setTypeface(null, Typeface.BOLD_ITALIC);
                rb2.setTypeface(null, Typeface.NORMAL);
                rb3.setTypeface(null, Typeface.NORMAL);
                break;

        }
    }
}
