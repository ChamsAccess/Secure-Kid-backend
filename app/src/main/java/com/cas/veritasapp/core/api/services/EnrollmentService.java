package com.cas.veritasapp.core.api.services;

import static com.cas.veritasapp.core.api.Routes.ENROLLMENTS;
import static com.cas.veritasapp.core.api.Routes.ENROLLMENTS_BY_ID;
import static com.cas.veritasapp.core.api.Routes.ENROLLMENTS_SEARCH_EMPLOYER;
import static com.cas.veritasapp.core.api.Routes.ENROLLMENTS_STATS;
import static com.cas.veritasapp.core.api.Routes.FIND_NIN_DATA;
import static com.cas.veritasapp.core.api.Routes.MEDIAS;

import com.cas.veritasapp.core.api.Routes;
import com.cas.veritasapp.core.data.entities.Employer;
import com.cas.veritasapp.core.data.entities.Enrollment;
import com.cas.veritasapp.objects.Media;
import com.cas.veritasapp.objects.Stats;
import com.cas.veritasapp.objects.api.ApiResponse;
import com.cas.veritasapp.objects.payloads.EnrollmentErrorPayload;
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

public interface EnrollmentService {


    @POST(FIND_NIN_DATA)
    Flowable<ApiResponse<NinPayload>> findNinData(@Body Map<String, String> map);

    @POST(ENROLLMENTS)
    Flowable<ApiResponse<EnrollmentPayload>> createEnrollment(@Body EnrollmentPayload payload,
                                                              @QueryMap Map<String, String> query);

    @POST(ENROLLMENTS)
    Flowable<ApiResponse<Enrollment>> senNewEnrollment(@Body Map<String, Object> payload,
                                                       @QueryMap Map<String, String> query);

    @POST(ENROLLMENTS_SEARCH_EMPLOYER)
    Flowable<ApiResponse<List<Employer>>> searchEmployer(@Body Map<String, Object> payload);

    @PUT(ENROLLMENTS_BY_ID)
    Flowable<ApiResponse<Enrollment>> updateEnrollment(@Path("id") String enrollmentId,
                                                       @Body Map<String, Object> payload,
                                                     @QueryMap Map<String, String> query);

    @GET(ENROLLMENTS)
    Flowable<ApiResponse<List<EnrollmentPayload>>> findEnrollments(@QueryMap Map<String, String> query);

    @GET(ENROLLMENTS_STATS)
    Flowable<ApiResponse<Stats>> stats(@QueryMap Map<String, Object> query);

    @GET(ENROLLMENTS_BY_ID)
    Flowable<ApiResponse<List<EnrollmentPayload>>> findEnrollment(@Path("id") String enrollmentId,
                                                                  @QueryMap Map<String, String> query);

    @GET(Routes.ENROLLMENTS_ERROR)
    Flowable<ApiResponse<EnrollmentErrorPayload>> findEnrollmentErrors(@Path("id") String enrollmentId,
                                                                       @QueryMap Map<String, Object> query);

    @GET(Routes.ENROLLMENTS_GENERATE_AND_SEND_PDF)
    Flowable<ApiResponse<Object>> generateAndSendPDF(@QueryMap Map<String, Object> query);

    @POST(Routes.ENROLLMENTS_GET_BALANCE)
    Flowable<ApiResponse<Object>> generateAnSendBalance(@Body Map<String, Object> body);

    @DELETE(ENROLLMENTS_BY_ID)
    Flowable<ApiResponse<List<EnrollmentPayload>>> deleteEnrollment(@Path("id") String enrollmentId,
                                                                    @Body EnrollmentPayload payload,
                                                                    @QueryMap Map<String, String> query);

    @Multipart
    @POST(MEDIAS)
    Flowable<ApiResponse<Media>> uploadFile(@Part MultipartBody.Part file,
                                            @QueryMap Map<String, String> query);

}
