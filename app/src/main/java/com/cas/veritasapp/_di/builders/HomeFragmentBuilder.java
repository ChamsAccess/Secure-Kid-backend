package com.cas.veritasapp._di.builders;

import com.cas.veritasapp.main.home.fragments.EnrollmentFragment;
import com.cas.veritasapp.main.home.fragments.DashboardFragment;
import com.cas.veritasapp.main.home.fragments.NINFragment;
import com.cas.veritasapp.main.home.fragments.PersonalDataFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by funmiayinde on 2019-09-27.
 */
@Module
public abstract class HomeFragmentBuilder {

    @ContributesAndroidInjector
    abstract DashboardFragment bindHomeFragment();

    @ContributesAndroidInjector
    abstract EnrollmentFragment bindEnrollmentFragment();

    @ContributesAndroidInjector
    abstract PersonalDataFragment bindPersonalDataFragment();

    @ContributesAndroidInjector
    abstract NINFragment bindNINFragment();
}
