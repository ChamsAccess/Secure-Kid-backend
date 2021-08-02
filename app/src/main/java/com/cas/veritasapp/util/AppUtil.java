package com.cas.veritasapp.util;


import androidx.databinding.BindingAdapter;

import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.objects.api.ApiResponse;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;


/**
 * Created by funmiayinde on 2019-09-09.
 */
public class AppUtil {


    public static ApiError getError(Throwable throwable) {
        LogUtil.info("error >>>>>", throwable.getMessage());
        String errorString = throwable.getMessage();
        ApiError apiError = new ApiError();
        if (throwable instanceof HttpException){
            ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
            ApiResponse apiResponse = new Gson().fromJson(responseBody.toString(), ApiResponse.class);
            errorString = apiResponse.get_meta().getError().getMessage();
            LogUtil.info("api response:", apiResponse.toString());
            apiError = apiResponse.get_meta().getError();
        }else if (throwable instanceof SocketTimeoutException) {
            errorString = "Server connection timed out";
        }
        apiError.setMessage(errorString);
        return apiError;
    }

    @BindingAdapter("app:errorText")
    public static void setErrorMessage(TextInputLayout view, String message) {
        view.setError(message);
    }

    @BindingAdapter("app:selectedOption")
    public static void setSelected(MaterialSpinner spinner, String item){
        LogUtil.write("setSelected option" + item);
        if (item != null && spinner.getItems() != null) {
//            int index = AppUtil.in
        }
    }

}
