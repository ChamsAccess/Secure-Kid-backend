package com.cas.veritasapp.core.api.services;

import static com.cas.veritasapp.core.api.Routes.COUNTRIES;
import static com.cas.veritasapp.core.api.Routes.COUNTRY_ID;
import static com.cas.veritasapp.core.api.Routes.STATES;
import static com.cas.veritasapp.core.api.Routes.STATE_ID;

import com.cas.veritasapp.core.data.entities.Country;
import com.cas.veritasapp.core.data.entities.State;
import com.cas.veritasapp.objects.api.ApiResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ResourceService {


    @GET(COUNTRIES)
    Flowable<ApiResponse<List<Country>>> findCountries(@QueryMap Map<String, String> query);

    @GET(COUNTRY_ID)
    Flowable<ApiResponse<Country>> findCountry(@Path("id") String id, @QueryMap Map<String, String> query);

    @GET(STATES)
    Flowable<ApiResponse<List<State>>> findStates(@QueryMap Map<String, String> query);

    @GET(STATE_ID)
    Flowable<ApiResponse<State>> findState(@Path("id") String id, @QueryMap Map<String, String> query);

}
