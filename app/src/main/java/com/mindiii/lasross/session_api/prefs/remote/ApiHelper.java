package com.mindiii.lasross.session_api.prefs.remote;

import com.androidnetworking.common.ANRequest;

import java.util.HashMap;

public interface ApiHelper {

    ANRequest doServerLogin(HashMap<String, String> params);

    ANRequest getHomeProductCategory(HashMap<String, String> params);

    ANRequest getHomeProduct(HashMap<String, String> params);

    ANRequest getMenuList(HashMap<String, String> params);

    ANRequest getProductDetail(HashMap<String, String>  header,HashMap<String, String> params);

}
