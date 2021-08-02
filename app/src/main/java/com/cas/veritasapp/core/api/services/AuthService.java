package com.cas.veritasapp.core.api.services;



import com.cas.veritasapp.core.api.Routes;
import com.cas.veritasapp.core.data.entities.Staff;
import com.cas.veritasapp.objects.api.ApiResponse;
import com.cas.veritasapp.objects.payloads.StaffPayload;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by funmiayinde on 2019-10-10.
 */
public interface AuthService {

    @FormUrlEncoded
    @POST(Routes.AUTH_LOGIN_REQUEST)
    Flowable<ApiResponse<Staff>> login(@Field("staff_id") String staff_id,
                                       @Field("pin") String pin);

    @GET(Routes.AUTH_LOGIN_REQUEST)
    Flowable<ApiResponse<StaffPayload>> getStaff(@QueryMap Map<String, String> query);

}
