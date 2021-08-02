package com.cas.veritasapp.objects.api;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by funmiayinde on 2019-10-10.
 */
public class ApiResponse<T> implements Serializable {
    private _Meta _meta;
    private T data;

    public _Meta get_meta() {
        return _meta;
    }

    public void set_meta(_Meta _meta) {
        this._meta = _meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        return "ApiResponse:{" +
                "_Meta':" + _meta + '\'' +
                " data:" + data +
                "";
    }
}
