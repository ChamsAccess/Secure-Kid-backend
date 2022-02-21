package com.cas.veritasapp.core.api;


import com.cas.veritasapp.BuildConfig;

/**
 * Created by funmiayinde on 2019-10-10.
 */
public class Routes {

    public static final String BASE_URL = BuildConfig.BASE_URL;
    public static final String AUTH_LOGIN_REQUEST = BASE_URL + "/login";

    // Enrollment
    public static final String FIND_NIN_DATA = BASE_URL + "/enrollments/find-nin-data";
    public static final String ENROLLMENTS = BASE_URL + "/enrollments";
    public static final String ENROLLMENTS_STATS = BASE_URL + "/enrollments/stats";
    public static final String ENROLLMENTS_BY_ID = BASE_URL + "/enrollments/{id}";
    public static final String ENROLLMENTS_ERROR = BASE_URL + "/enrollments/{id}/error";
    public static final String ENROLLMENTS_SEARCH_EMPLOYER = BASE_URL + "/enrollments/search-employer";

    // RESOURCES
    public static final String COUNTRIES = BASE_URL + "/resources/countries";
    public static final String COUNTRY_ID = BASE_URL + "/resources/countries/{id}";
    public static final String STATES = BASE_URL + "/resources/states";
    public static final String STATE_ID = BASE_URL + "/resources/states/{id}";

    // Media
    public static final String MEDIAS = BASE_URL + "/medias";


}
