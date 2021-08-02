package com.cas.veritasapp._di;

import com.cas.veritasapp._di.builders.AuthFragmentBuilder;
import com.cas.veritasapp._di.builders.HomeFragmentBuilder;
import com.cas.veritasapp._di.modules.AuthModule;
import com.cas.veritasapp._di.scopes.ActivityScope;
import com.cas.veritasapp.main.auth.AuthActivity;
import com.cas.veritasapp.main.auth.rvvm.AuthModel;
import com.cas.veritasapp.main.home.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by funmiayinde on 2019-09-25.
 */
@Module
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {
            AuthModule.class,
            AuthFragmentBuilder.class
    })
    abstract AuthActivity bindAuthActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {
//            AuthModule.class,
            HomeFragmentBuilder.class
    })
    abstract HomeActivity bindHomeActivity();
}

