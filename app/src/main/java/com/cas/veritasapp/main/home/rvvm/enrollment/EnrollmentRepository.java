package com.cas.veritasapp.main.home.rvvm.enrollment;


import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.cas.veritasapp.core.api.RetrofitRequest;
import com.cas.veritasapp.core.api.services.EnrollmentService;
import com.cas.veritasapp.core.api.services.ResourceService;
import com.cas.veritasapp.core.base.BaseRepository;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.network.Resource;
import com.cas.veritasapp.objects.Country;
import com.cas.veritasapp.objects.Enrollment;
import com.cas.veritasapp.objects.Media;
import com.cas.veritasapp.objects.State;
import com.cas.veritasapp.objects.Stats;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.objects.api.ApiResponse;
import com.cas.veritasapp.objects.api._Meta;
import com.cas.veritasapp.objects.payloads.EnrollmentPayload;
import com.cas.veritasapp.objects.payloads.NinPayload;
import com.cas.veritasapp.util.AppUtil;
import com.cas.veritasapp.util.LogUtil;

import org.reactivestreams.Publisher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

@Singleton
public class EnrollmentRepository implements BaseRepository<Enrollment, EnrollmentPayload> {

    private static PagedList.Config mConfig =
            new PagedList.Config.Builder()
                    .setEnablePlaceholders(true)
                    .setPrefetchDistance(2)
                    .setPageSize(10)
                    .build();
    private Context context;
    private EnrollmentService enrollmentService;
    private ResourceService resourceService;

    @Inject
    public EnrollmentRepository(Context context) {
        this.context = context;
        this.enrollmentService = RetrofitRequest.createService(EnrollmentService.class, context);
        this.resourceService = RetrofitRequest.createService(ResourceService.class, context);
    }

    private Flowable<Enrollment> performApiRequest(Flowable<ApiResponse<EnrollmentPayload>> enrollmentFlowable) {
        return enrollmentFlowable
                .map(ApiResponse::getData)
                .map(Enrollment::create)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .flatMap((Function<Enrollment, Publisher<Enrollment>>) enrollment -> {
                    LogUtil.write("Enrollment::::" + enrollment);
                    return Flowable.just(enrollment);
                });

    }

    @SuppressLint("CheckResult")
    public MutableLiveData<Resource<List<Country>>> findCountries(Map<String, String> query) {
        final MutableLiveData<Resource<List<Country>>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null, AppConstant.FIND_COUNTRIES));
        if (query == null){
            query = new HashMap<>();
        }
        query.put("all", "true");
        query.put("population", "[{\"path\": \"states\", \"populate\":\"lgas\", \"options\": {\"sort\": {\"name\": 1}}}]");
        resourceService.findCountries(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {
                    LogUtil.write("country:::" + apiResponse.toString());
                    _Meta meta = apiResponse.get_meta();
                    if (meta.isSuccess()) {
                        data.setValue(Resource.success(apiResponse.getData(), AppConstant.FIND_COUNTRIES));
                    }
                }, error -> {
                    ApiError apiError = AppUtil.getError(error);
                    LogUtil.write("apiError:" + apiError.getMessage());
                    data.setValue(Resource.error(apiError, AppConstant.FIND_COUNTRIES, null));
                });
        return data;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<Resource<Country>> findCountry(String id, Map<String, String> query) {
        final MutableLiveData<Resource<Country>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null, AppConstant.FIND_COUNTRY));
        if (query == null){
            query = new HashMap<>();
        }
        query.put("population", "[{\"path\": \"states\", \"populate\":\"lgas\", \"select\":\"name code\",\"options\": {\"sort\": {\"name\": 1}}}]");
        resourceService.findCountry(id, query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {
                    LogUtil.write("one-country:::" + apiResponse.toString());
                    _Meta meta = apiResponse.get_meta();
                    if (meta.isSuccess()) {
                        data.setValue(Resource.success(apiResponse.getData(), AppConstant.FIND_COUNTRY));
                    }
                }, error -> {
                    ApiError apiError = AppUtil.getError(error);
                    LogUtil.write("apiError:" + apiError.getMessage());
                    data.setValue(Resource.error(apiError, AppConstant.FIND_COUNTRY, null));
                });
        return data;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<Resource<State>> findState(String id, Map<String, String> query) {
        final MutableLiveData<Resource<State>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null, AppConstant.FIND_STATE));
        if (query == null){
            query = new HashMap<>();
        }
        query.put("population", "[{\"path\": \"lgas\", \"select\": \"code name\",  \"options\":  {\"sort\": {\"name\": 1}}}]");
        resourceService.findState(id, query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {
                    LogUtil.write("one-state:::" + apiResponse.toString());
                    _Meta meta = apiResponse.get_meta();
                    if (meta.isSuccess()) {
                        data.setValue(Resource.success(apiResponse.getData(), AppConstant.FIND_STATE));
                    }
                }, error -> {
                    ApiError apiError = AppUtil.getError(error);
                    LogUtil.write("apiError:" + apiError.getMessage());
                    data.setValue(Resource.error(apiError, AppConstant.FIND_STATE, null));
                });
        return data;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<Resource<NinPayload>> findNin(Map<String, String> request) {
        final MutableLiveData<Resource<NinPayload>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null, AppConstant.FIND_NIN_DATA));
        enrollmentService.findNinData(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {
                    LogUtil.write("NIN-DATA:::" + apiResponse.toString());
                    _Meta meta = apiResponse.get_meta();
                    if (meta.isSuccess()) {
                        data.setValue(Resource.success(apiResponse.getData(), AppConstant.FIND_NIN_DATA));
                    }
                }, error -> {
                    ApiError apiError = AppUtil.getError(error);
                    LogUtil.write("apiError:" + apiError.getMessage());
                    data.setValue(Resource.error(apiError, AppConstant.FIND_NIN_DATA, null));
                });
        return data;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<Resource<Media>> uploadFile(MultipartBody.Part requestBody, String key) {
        final MutableLiveData<Resource<Media>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null, AppConstant.UPLOAD_ACTION));
        enrollmentService.uploadFile(requestBody, new HashMap<>())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {
                    LogUtil.write("Media-DATA:::" + apiResponse.toString());
                    _Meta meta = apiResponse.get_meta();
                    if (meta.isSuccess()) {
                        data.setValue(Resource.success(apiResponse.getData(), key));
                    }
                }, error -> {
                    ApiError apiError = AppUtil.getError(error);
                    LogUtil.write("apiError:" + apiError.getMessage());
                    data.setValue(Resource.error(apiError, key, null));
                });
        return data;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<Resource<Enrollment>> senNewEnrollment(Map<String, Object> request) {
        final MutableLiveData<Resource<Enrollment>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null, AppConstant.CREATE_ENROLLMENT));
        enrollmentService.senNewEnrollment(request, new HashMap<>())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {
                    LogUtil.write("Enrollment-DATA:::" + apiResponse.toString());
                    _Meta meta = apiResponse.get_meta();
                    if (meta.isSuccess()) {
                        data.setValue(Resource.success(apiResponse.getData(), AppConstant.CREATE_ENROLLMENT));
                    }
                }, error -> {
                    ApiError apiError = AppUtil.getError(error);
                    LogUtil.write("apiError:" + apiError.getMessage());
                    data.setValue(Resource.error(apiError, AppConstant.CREATE_ENROLLMENT, null));
                });
        return data;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<Resource<Stats>> stats(Map<String, Object> query) {
        final MutableLiveData<Resource<Stats>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null, AppConstant.ENROLLMENT_STATS));
        if (query ==  null){
            query = new HashMap<>();
        }
        enrollmentService.stats(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {
                    LogUtil.write("Enrollment-STATS:::" + apiResponse.toString());
                    _Meta meta = apiResponse.get_meta();
                    if (meta.isSuccess()) {
                        data.setValue(Resource.success(apiResponse.getData(), AppConstant.ENROLLMENT_STATS));
                    }
                }, error -> {
                    ApiError apiError = AppUtil.getError(error);
                    LogUtil.write("apiError:" + apiError.getMessage());
                    data.setValue(Resource.error(apiError, AppConstant.ENROLLMENT_STATS, null));
                });
        return data;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<Resource<Enrollment>> updateEnrollment(String id, Map<String, Object> payload, HashMap map) {
        final MutableLiveData<Resource<Enrollment>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null, AppConstant.UPDATE_ENROLLMENT));
        enrollmentService.updateEnrollment(id, payload, map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {
                    LogUtil.write("Enrollment-DATA:::" + apiResponse.toString());
//                    _Meta meta = apiResponse.get_meta();
//                    if (meta.isSuccess()) {
//                        data.setValue(Resource.success(apiResponse.getData(), AppConstant.UPDATE_ENROLLMENT));
//                    }
                }, error -> {
//                    ApiError apiError = AppUtil.getError(error);
//                    LogUtil.write("apiError:" + apiError.getMessage());
//                    data.setValue(Resource.error(apiError, AppConstant.CREATE_ENROLLMENT, null));
                });
        return data;
    }

    @Override
    public Flowable<Enrollment> create(EnrollmentPayload enrollmentPayload, HashMap map) {
        return null;
    }

    public Flowable<List<Enrollment>> load(Map<String, String> query) {
        return enrollmentService.findEnrollments(query)
                .map(ApiResponse::getData)
                .map((Enrollment::update))
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .flatMap((Function<List<Enrollment>, Publisher<List<Enrollment>>>) Flowable::just);
    }


    @SuppressLint("CheckResult")
    @Override
    public Flowable<Enrollment> edit(String id, EnrollmentPayload enrollmentPayload, HashMap hashMap) {
        return null;
    }

    @Override
    public Flowable<Enrollment> get(String id, HashMap hashMap) {
        return null;
    }

    @Override
    public Flowable<PagedList<Enrollment>> find(HashMap query) {
        return null;
    }

    @Override
    public Flowable<Enrollment> delete(String id) {
        return null;
    }
}
