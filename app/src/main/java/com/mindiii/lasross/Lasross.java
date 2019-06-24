package com.mindiii.lasross;

import android.app.Application;
import android.content.res.Configuration;

import com.androidnetworking.AndroidNetworking;
import com.mindiii.lasross.session_api.prefs.AppDataManager;

public class Lasross extends Application {

    private static Lasross instance;
    private static AppDataManager appInstance;

    protected static synchronized Lasross getInstance() {

        if (instance != null) {

            return instance;
        }
        return new Lasross();
    }


    public static  AppDataManager getDataManager(){
        return appInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
        instance = this;
        appInstance = AppDataManager.getInstance(this);

    }


    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
