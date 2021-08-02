package com.cas.veritasapp.objects;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by funmiayinde on 2019-10-11.
 */
public class NavData implements Serializable {

    @IdRes
    private int navigateId = -1;
    private Bundle bundle;

    public NavData() {
    }

    public NavData(int navigateId, Bundle bundle) {
        this.navigateId = navigateId;
        this.bundle = bundle;
    }

    public int getNavigateId() {
        return navigateId;
    }

    public void setNavigateId(int navigateId) {
        this.navigateId = navigateId;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public String toString() {
        return "SourceObject:{" +
                "navigatedId=" + navigateId +
                ", bundle" + bundle +
                '}';

    }
}

