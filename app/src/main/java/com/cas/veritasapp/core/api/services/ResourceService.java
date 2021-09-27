package com.cas.veritasapp.core.api.services;

import static com.cas.veritasapp.core.api.Routes.COUNTRIES;
import static com.cas.veritasapp.core.api.Routes.COUNTRY_ID;
import static com.cas.veritasapp.core.api.Routes.ENROLLMENTS;
import static com.cas.veritasapp.core.api.Routes.ENROLLMENTS_BY_ID;
import static com.cas.veritasapp.core.api.Routes.ENROLLMENTS_STATS;
import static com.cas.veritasapp.core.api.Routes.FIND_NIN_DATA;
import static com.cas.veritasapp.core.api.Routes.MEDIAS;
import static com.cas.veritasapp.core.api.Routes.STATES;
import static com.cas.veritasapp.core.api.Routes.STATE_ID;

import com.cas.veritasapp.objects.Country;
import com.cas.veritasapp.objects.Enrollment;
import com.cas.veritasapp.objects.Media;
import com.cas.veritasapp.objects.State;
import com.cas.veritasapp.objects.Stats;
import com.cas.veritasapp.objects.api.ApiResponse;
import com.cas.veritasapp.objects.payloads.EnrollmentPayload;
import com.cas.veritasapp.objects.payloads.NinPayload;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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
