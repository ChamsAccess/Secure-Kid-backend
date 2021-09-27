package com.cas.veritasapp.main.home.rvvm.enrollment;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cas.veritasapp.core.base.CoreViewModel;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.network.Resource;
import com.cas.veritasapp.objects.Country;
import com.cas.veritasapp.objects.Enrollment;
import com.cas.veritasapp.objects.Media;
import com.cas.veritasapp.objects.State;
import com.cas.veritasapp.objects.Stats;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.objects.payloads.EnrollmentPayload;
import com.cas.veritasapp.objects.payloads.NinPayload;
import com.cas.veritasapp.util.AppUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class EnrollmentViewModel extends CoreViewModel<
        EnrollmentRepository, Enrollment, EnrollmentPayload, EnrollmentValidation> {

    private EnrollmentRepository enrollmentRepository;

    @Inject
    public EnrollmentViewModel(EnrollmentRepository enrollmentRepository){
        this.enrollmentRepository = enrollmentRepository;
        current = new Enrollment();
    }

    public MutableLiveData<Resource<NinPayload>> findNin(Map<String, String> request){
        return enrollmentRepository.findNin(request);
    }

    public MutableLiveData<Resource<List<Country>>> findCountries(Map<String, String> request){
        return enrollmentRepository.findCountries(request);
    }
    public MutableLiveData<Resource<State>> findState(String id, Map<String, String> request){
        return enrollmentRepository.findState(id, request);
    }
    public MutableLiveData<Resource<Country>> findCountry(String id, Map<String, String> request){
        return enrollmentRepository.findCountry(id, request);
    }
    public MutableLiveData<Resource<Media>> uploadFile(MultipartBody.Part requestBody, String key){
        return enrollmentRepository.uploadFile(requestBody, key);
    }

    public MutableLiveData<Resource<Enrollment>> senNewEnrollment(Map<String, Object> requestBody){
        return enrollmentRepository.senNewEnrollment(requestBody);
    }

    public MutableLiveData<Resource<Stats>> stats(Map<String, Object> query){
        return enrollmentRepository.stats(query);
    }

    public MutableLiveData<Resource<Enrollment>> updateEnrollment(Map<String, Object> requestBody){
        return enrollmentRepository.senNewEnrollment(requestBody);
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<Resource<List<Enrollment>>> getEnrollments(Map<String, String> query){
        MutableLiveData<Resource<List<Enrollment>>> enrollments = new MutableLiveData<>();
        if (query == null){
            query = new HashMap<>();
        }
        enrollments.setValue(Resource.loading(null));
        enrollmentRepository.load(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(current -> {
                    enrollments.setValue(Resource.success(current, AppConstant.FIND_ENROLLMENT));
                }, error -> {
                    ApiError apiError = AppUtil.getError(error);
                    enrollments.setValue(Resource.error(apiError, AppConstant.FIND_ENROLLMENT, null));
                });
        return enrollments;
    }


}
