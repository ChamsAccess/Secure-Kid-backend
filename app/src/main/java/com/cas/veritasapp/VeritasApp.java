package com.cas.veritasapp;

import android.app.Activity;
import android.app.Application;

import com.cas.veritasapp._di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.DispatchingAndroidInjector;

public class VeritasApp  extends Application implements HasActivityInjector {
    private static final String TAG = VeritasApp.class.getSimpleName();

    @Inject
    DispatchingAndroidInjector<Activity> injector;

    private VeritasApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return injector;
    }
}
