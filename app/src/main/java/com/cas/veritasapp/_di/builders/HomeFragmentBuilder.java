package com.cas.veritasapp._di.builders;

import com.cas.veritasapp.main.home.dialog.PreviewFragmentDialog;
import com.cas.veritasapp.main.home.dialog.SignatureFragmentDialog;
import com.cas.veritasapp.main.home.fragments.ContributionBioInfoFragment;
import com.cas.veritasapp.main.home.fragments.EmploymentFragment;
import com.cas.veritasapp.main.home.fragments.DashboardFragment;
import com.cas.veritasapp.main.home.fragments.HistoryFragment;
import com.cas.veritasapp.main.home.fragments.NINFragment;
import com.cas.veritasapp.main.home.fragments.NewEnrollmentFragment;
import com.cas.veritasapp.main.home.fragments.NextOfKinFragment;
import com.cas.veritasapp.main.home.fragments.PFACertificationFragment;
import com.cas.veritasapp.main.home.fragments.PersonalDataFragment;
import com.cas.veritasapp.main.home.fragments.SalaryStructureFragment;

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
    abstract NewEnrollmentFragment bindEnrollmentFragment();

    @ContributesAndroidInjector
    abstract PersonalDataFragment bindPersonalDataFragment();

    @ContributesAndroidInjector
    abstract NINFragment bindNINFragment();

    @ContributesAndroidInjector
    abstract ContributionBioInfoFragment bindContributionBioInfoFragment();

    @ContributesAndroidInjector
    abstract NextOfKinFragment bindNextOfKinFragment();

    @ContributesAndroidInjector
    abstract PFACertificationFragment bindPFACertificationFragment();

    @ContributesAndroidInjector
    abstract SalaryStructureFragment bindSalaryStructureFragment();

    @ContributesAndroidInjector
    abstract EmploymentFragment bindEmploymentFragment();

    @ContributesAndroidInjector
    abstract PreviewFragmentDialog bindPreviewFragmentDialog();

    @ContributesAndroidInjector
    abstract SignatureFragmentDialog bindSignatureFragmentDialog();

    @ContributesAndroidInjector
    abstract HistoryFragment bindHistoryFragment();
}
