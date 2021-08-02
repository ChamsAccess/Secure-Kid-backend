package com.cas.veritasapp.main.auth;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseActivity;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.databinding.ActivityAuthBinding;
import com.cas.veritasapp.main.home.HomeActivity;
import com.cas.veritasapp.util.PrefUtil;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by funmiayinde on 2019-10-11.
 */
public class AuthActivity extends BaseActivity<ActivityAuthBinding> implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> injector;

    ActivityAuthBinding binding;


    @Override
    public int getLayoutId() {
        return R.layout.activity_auth;
    }

    public int getNavHost() {
        return R.id.auth_host_navigation;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        if (PrefUtil.getStringData(this, AppConstant.TOKEN) != null) {
            launchActivity(AuthActivity.this, HomeActivity.class);
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return injector;
    }
}
