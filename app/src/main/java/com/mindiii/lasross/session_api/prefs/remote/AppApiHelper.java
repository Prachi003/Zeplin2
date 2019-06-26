package com.mindiii.lasross.session_api.prefs.remote;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;

import java.util.HashMap;

public class AppApiHelper implements ApiHelper{


    private final static  String TAG = AppApiHelper.class.getSimpleName();

    private static  AppApiHelper instance;

    public synchronized  static AppApiHelper getAppApiInstance(){

        if(instance == null){
            instance = new AppApiHelper();
        }
        return instance;
    }

    @Override
    public ANRequest doServerLogin(HashMap<String, String> params) {
        return AndroidNetworking.post(WebServices.WEB_LOGIN)
                .addBodyParameter(params)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build();
    }

    @Override
    public ANRequest getHomeProductCategory(HashMap<String, String> params) {
        return AndroidNetworking.post(WebServices.GET_HOME_PRODUCT_CATEGORY)
                .addHeaders(params)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build();
    }

    @Override
    public ANRequest getHomeProduct(HashMap<String, String> params) {
        return AndroidNetworking.post(WebServices.GET_HOME_PRODUCT)
                .addHeaders(params)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build();
    }

    @Override
    public ANRequest getMenuList(HashMap<String, String> params) {
        return AndroidNetworking.post(WebServices.GET_MENU_LIST)
                .addHeaders(params)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build();
    }

    @Override
    public ANRequest getProductDetail(HashMap<String, String> header,HashMap<String, String> params) {
        return AndroidNetworking.post(WebServices.GET_PRODUCT_DETAIL)
                .addHeaders(header)
                .addBodyParameter(params)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build();
    }



    /*@Override
    public ANRequest doServerLogin(HashMap<String, String> params) {
        return AndroidNetworking.post(WebServices.WEB_LOGIN)
                .addBodyParameter(params)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build();
    }*/
}
