package com.mindiii.lasross.session_api.prefs;

import android.content.Context;

import com.androidnetworking.common.ANRequest;
import com.mindiii.lasross.session_api.prefs.newPrefs.AppPrefrencesHelper;
import com.mindiii.lasross.session_api.prefs.newPrefs.PreferencesHelper;
import com.mindiii.lasross.session_api.prefs.remote.ApiHelper;
import com.mindiii.lasross.session_api.prefs.remote.AppApiHelper;

import java.util.HashMap;

public class AppDataManager implements DataManager {

    private static AppDataManager instance;
    private final ApiHelper mApiHelper;
    private final PreferencesHelper mPreferencesHelper;

    private AppDataManager(Context context) {
        mPreferencesHelper = new AppPrefrencesHelper(context);
        mApiHelper = AppApiHelper.getAppApiInstance();
    }

    public synchronized static AppDataManager getInstance(Context context) {
        if (instance == null) {
            instance = new AppDataManager(context);
        }
        return instance;
    }

    @Override
    public ANRequest doServerLogin(HashMap<String, String> params) {
        return mApiHelper.doServerLogin(params);
    }

    @Override
    public ANRequest getHomeProductCategory(HashMap<String, String> params) {
        return mApiHelper.getHomeProductCategory(params);
    }

    @Override
    public ANRequest getHomeProduct(HashMap<String, String> params) {
        return mApiHelper.getHomeProduct(params);
    }

    @Override
    public ANRequest getMenuList(HashMap<String, String> params) {
        return mApiHelper.getMenuList(params);
    }



   /* @Override
    public Boolean isLoggedIn() {
        return mPreferencesHelper.isLoggedIn();
    }*/
}
