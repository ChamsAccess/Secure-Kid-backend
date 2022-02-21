package com.cas.veritasapp.main.home.rvvm.enrollment;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.cas.veritasapp.core.base.CoreViewModel;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.data.entities.Employer;
import com.cas.veritasapp.core.network.Resource;
import com.cas.veritasapp.core.data.entities.Country;
import com.cas.veritasapp.core.data.entities.Enrollment;
import com.cas.veritasapp.objects.Media;
import com.cas.veritasapp.core.data.entities.State;
import com.cas.veritasapp.objects.Stats;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.objects.payloads.EnrollmentErrorPayload;
import com.cas.veritasapp.objects.payloads.EnrollmentPayload;
import com.cas.veritasapp.objects.payloads.NinPayload;
import com.cas.veritasapp.util.AppUtil;
import com.cas.veritasapp.util.PrefUtil;

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

    public MutableLiveData<Resource<List<Employer>>> searchEmployer(Map<String, Object> requestBody){
        return enrollmentRepository.searchEmployer(requestBody);
    }

    public MutableLiveData<Resource<Stats>> stats(Map<String, Object> query){
        return enrollmentRepository.stats(query);
    }

    public MutableLiveData<Resource<EnrollmentErrorPayload>> getEnrollmentError(String id, Map<String, Object> query){
        return enrollmentRepository.getEnrollmentError(id, query);
    }

    public MutableLiveData<Resource<Enrollment>> updateEnrollment(String id, Map<String, Object> requestBody){
        return enrollmentRepository.updateEnrollment(id,requestBody, new HashMap());
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<Resource<List<Enrollment>>> getEnrollments(Map<String, String> query){
        MutableLiveData<Resource<List<Enrollment>>> enrollments = new MutableLiveData<>();
        if (query == null){
            query = new HashMap<>();
        }
        query.put("all", Boolean.toString(true));
        enrollments.setValue(Resource.loading(null));
        enrollmentRepository.load(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(current -> enrollments.setValue(Resource.success(current, AppConstant.FIND_ENROLLMENT)), error -> {
                    ApiError apiError = AppUtil.getError(error);
                    enrollments.setValue(Resource.error(apiError, AppConstant.FIND_ENROLLMENT, null));
                });
        return enrollments;
    }


}
