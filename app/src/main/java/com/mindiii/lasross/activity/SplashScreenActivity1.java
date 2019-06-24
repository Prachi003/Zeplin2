package com.mindiii.lasross.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.mindiii.lasross.R;
import com.mindiii.lasross.home.HomeActivity;
import com.mindiii.lasross.loginregistration.LoginActivity;
import com.mindiii.lasross.sessionNew.Session;


public class SplashScreenActivity1 extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    private  Session sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_1);

        sharedPref = new Session(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(sharedPref.isLoggedIn()){
                    Intent i = new Intent(SplashScreenActivity1.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i = new Intent(SplashScreenActivity1.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }


            }
        }, SPLASH_TIME_OUT);
    }
}
