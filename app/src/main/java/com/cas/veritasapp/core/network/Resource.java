package com.cas.veritasapp.core.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cas.veritasapp.objects.api.ApiError;


/**
 * Created by funmiayinde on 2019-10-11.
 */
public class Resource<T> {

    @NonNull
    public final Status status;

    @NonNull
    public final T data;

    @Nullable
    public ApiError error;

    @Nullable
    public final String key;

    private Resource(@NonNull Status status, @Nullable T data,
                     @Nullable String key,
                     @Nullable ApiError error) {

        this.status = status;
        this.data = data;
        this.error = error;
        this.key = key;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(Status.SUCCESS, data, null, null);
    }

    public static <T> Resource<T> success(@Nullable T data, String key) {
        return new Resource<>(Status.SUCCESS, data, key, null);
    }

    public static <T> Resource<T> error(@Nullable ApiError error, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, null, error);
    }

    public static <T> Resource<T> error(@Nullable ApiError error, String key, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, key, error);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null, null);
    }

    public static <T> Resource<T> loading(@Nullable T data, String key) {
        return new Resource<>(Status.LOADING, data, key, null);
    }

    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", data=" + data +
                ", appError=" + error +
                ", key='" + key + '\'' +
                '}';
    }

}
