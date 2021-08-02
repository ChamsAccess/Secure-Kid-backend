package com.cas.veritasapp._di.builders;


import com.cas.veritasapp.main.auth.fragments.LoginFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by funmiayinde on 2019-10-11.
 */

@Module
public abstract class AuthFragmentBuilder {

    @ContributesAndroidInjector
    abstract LoginFragment bindLoginFragment();

}
