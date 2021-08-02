package com.cas.veritasapp.objects.api;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by funmiayinde on 2019-10-10.
 */
public class _Meta implements Serializable {

    private int status_code;
    private boolean success;
    private String token;
    private String message;
    private Pagination pagination;

    private ApiError error;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }

    @NonNull
    @Override
    public String toString() {
        return "_Meta{" +
                "status_code" + status_code +
                ", success=" + success +
                ", token" + token + '\'' +
                ", message'" + message + '\'' +
                ", pagination=" + pagination +
                ", error=" + error +
                '}';

    }
}
