package com.cas.veritasapp.core.api;


import com.cas.veritasapp.BuildConfig;

/**
 * Created by funmiayinde on 2019-10-10.
 */
public class Routes {

    public static final String BASE_URL = BuildConfig.BASE_URL;
    public static final String AUTH_LOGIN_REQUEST = BASE_URL + "/login";
    public static final String GET_STAFF_ORDER = BASE_URL + "/orders/history";
}
