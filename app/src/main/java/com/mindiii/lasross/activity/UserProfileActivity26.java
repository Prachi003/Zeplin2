package com.mindiii.lasross.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.mindiii.lasross.R;
import com.mindiii.lasross.adapter.UserProfileAdapter;
import com.mindiii.lasross.model.UserProfileModel;
import com.mindiii.lasross.sessionNew.Session;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserProfileActivity26 extends AppCompatActivity  {

    private UserProfileAdapter userProfileAdapter;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_activity_layout_26);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        ImageView ivProfileEdit = findViewById(R.id.ivProfileEdit);
        ImageView iv_back_Icon = findViewById(R.id.iv_back_Icon);

        ImageView ivUserImage = findViewById(R.id.ivUserImage);

        TextView tvUserName = findViewById(R.id.tvUserName);
        TextView tvAddress = findViewById(R.id.tvAddress);

        List<UserProfileModel> list = new ArrayList<>();

        //pref = new SharedPref(this);
        //SharedPref pref;
        Session session = new Session(this);

        if (!session.getDescription().isEmpty()){
            tvAddress.setText(session.getDescription());
        }
        else
            tvAddress.setText("N/A");



        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.home_header_bg1));


        if(!session.getProfile_image().isEmpty()){

            Picasso.with(this)
                    .load(session.getProfile_image())
                    .resize(600, 600)
                    .into(ivUserImage);

        }


        tvUserName.setText(session.getFirst_name() +" "+ session.getLast_Name());

        iv_back_Icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ivProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfileActivity26.this, EditProfileActivity.class));
                finish();
                //openDialog();
            }
        });

        /*for(int i=1;i<=5;i++)
        {
            UserProfileModel userProfileModel = new UserProfileModel("My Orders "+i,"It is a long established fact that a reader");
            list.add(userProfileModel);
        }

        userProfileAdapter = new UserProfileAdapter(list,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(userProfileAdapter);*/
    }

    /*private void openDialog() {

        final Dialog dialog = new Dialog(UserProfileActivity26.this);//,android.R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog_artboard_18);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setTitle("Add Photo!");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        lp.windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setAttributes(lp);

        dialog.show();
    }
*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
