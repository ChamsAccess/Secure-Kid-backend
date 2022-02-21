package com.cas.veritasapp.main.auth.rvvm;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.cas.veritasapp.core.api.RetrofitRequest;
import com.cas.veritasapp.core.api.services.AuthService;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.data.entities.User;
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
    public LiveData<Resource<User>> login(String email, String password) {
        final MutableLiveData<Resource<User>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));
        authService.login(email, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {
                    _Meta meta = apiResponse.get_meta();
                    PrefUtil.saveData(context, meta.getToken());
                    AppUtil.updateToken(meta.getToken(), context);
                    User user = apiResponse.getData();
                    LogUtil.info("user data --->: ", user.toString());
                    PrefUtil.saveData(context, AppConstant.USER_ID, user.getId());
                    data.setValue(Resource.success(user));
                }, error -> {
                    ApiError apiError = AppUtil.getError(error);
                    LogUtil.write("apiError:" + apiError.getMessage());
                    data.setValue(Resource.error(apiError, null));
                });
        return data;
    }
}
