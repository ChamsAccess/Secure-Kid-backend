package com.cas.veritasapp.core.listeners;


import com.cas.veritasapp.objects.api.ApiError;

/**
 * Created by funmiayinde on 2019-10-11.
 */
public interface NetworkRequestListener {
    void onLoad(String key);

    void onSuccess(Object obj, String key);

    void onError(ApiError apiError, String key);
}
