package com.mindiii.lasross.session_api.prefs.remote;

import com.mindiii.lasross.BuildConfig;

public class WebServices {

    static final String WEB_LOGIN = BuildConfig.BASE_URL + "service/login";

    static final String WEB_FORGOT_PASSWORD = BuildConfig.BASE_URL + "service/forgot_password";

    static final String GET_HOME_PRODUCT_CATEGORY = BuildConfig.BASE_URL + "product/productCategory";

    static final String GET_HOME_PRODUCT = BuildConfig.BASE_URL + "product/getProduct";

    static final String GET_MENU_LIST = BuildConfig.BASE_URL + "product/menuList";

}
