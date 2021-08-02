package com.cas.veritasapp.main.auth.rvvm;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.cas.veritasapp.core.api.RetrofitRequest;
import com.cas.veritasapp.core.api.services.AuthService;
import com.cas.veritasapp.core.data.entities.Staff;
import com.cas.veritasapp.core.network.Resource;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.objects.api._Meta;
import com.cas.veritasapp.util.AppUtil;
import com.cas.veritasapp.util.LogUtil;
import com.cas.veritasapp.util.PrefUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by funmiayinde on 2019-10-14.
 */
public class AuthRepository {

    public Context context;

    private AuthService authService;

    @Inject
    public AuthRepository(Context context) {
        this.context = context;
        authService = RetrofitRequest.createService(AuthService.class, context);
    }

    @SuppressLint("CheckResult")
    public LiveData<Resource<Staff>> login(String email, String password) {
        final MutableLiveData<Resource<Staff>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));
        authService.login(email, password)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {
                    _Meta meta = apiResponse.get_meta();
                    PrefUtil.saveData(context, meta.getToken());
//                    Staff staff = apiResponse.getData();
//                    LogUtil.info("staff data --->: ", staff.toString());
//                    data.setValue(Resource.success(staff));
                }, error -> {
                    ApiError apiError = AppUtil.getError(error);
                    data.setValue(Resource.error(apiError, null));
                });
        return data;
    }
}
