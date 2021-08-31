package com.cas.veritasapp.core.api.services;

import static com.cas.veritasapp.core.api.Routes.ENROLLMENTS;
import static com.cas.veritasapp.core.api.Routes.ENROLLMENTS_BY_ID;
import static com.cas.veritasapp.core.api.Routes.FIND_NIN_DATA;
import static com.cas.veritasapp.core.api.Routes.MEDIAS;

import com.cas.veritasapp.objects.Enrollment;
import com.cas.veritasapp.objects.Media;
import com.cas.veritasapp.objects.api.ApiResponse;
import com.cas.veritasapp.objects.payloads.EnrollmentPayload;
import com.cas.veritasapp.objects.payloads.NinPayload;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface EnrollmentService {


    @POST(FIND_NIN_DATA)
    Flowable<ApiResponse<NinPayload>> findNinData(@Body Map<String, String> map);

    @POST(ENROLLMENTS)
    Flowable<ApiResponse<EnrollmentPayload>> createEnrollment(@Body EnrollmentPayload payload,
                                                              @QueryMap Map<String, String> query);

    @POST(ENROLLMENTS)
    Flowable<ApiResponse<Enrollment>> senNewEnrollment(@Body Map<String, Object> payload,
                                                       @QueryMap Map<String, String> query);

    @GET(ENROLLMENTS)
    Flowable<ApiResponse<List<EnrollmentPayload>>> findEnrollments(@QueryMap Map<String, String> query);

    @GET(ENROLLMENTS_BY_ID)
    Flowable<ApiResponse<List<EnrollmentPayload>>> findEnrollment(@Path("id") String enrollmentId,
                                                                  @QueryMap Map<String, String> query);

    @PUT(ENROLLMENTS_BY_ID)
    Flowable<ApiResponse<List<EnrollmentPayload>>> editEnrollment(@Path("id") String enrollmentId,
                                                                  @Body EnrollmentPayload payload,
                                                                  @QueryMap Map<String, String> query);

    @DELETE(ENROLLMENTS_BY_ID)
    Flowable<ApiResponse<List<EnrollmentPayload>>> deleteEnrollment(@Path("id") String enrollmentId,
                                                                    @Body EnrollmentPayload payload,
                                                                    @QueryMap Map<String, String> query);

    @Multipart
    @POST(MEDIAS)
    Flowable<ApiResponse<Media>> uploadFile(@Part MultipartBody.Part file,
                                            @QueryMap Map<String, String> query);

}
