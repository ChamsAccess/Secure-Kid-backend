package com.cas.veritasapp._di.modules;


import com.cas.veritasapp._di.scopes.ActivityScope;
import com.cas.veritasapp.main.home.HomeActivity;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentRepository;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    @ActivityScope
    @Provides
    EnrollmentRepository enrollmentRepository(HomeActivity activity) {
        return new EnrollmentRepository(activity);
    }

    @ActivityScope
    @Provides
    EnrollmentViewModel provideEnrollmentViewModel(EnrollmentRepository repository){
        return new EnrollmentViewModel(repository);
    }
}
