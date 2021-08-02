package com.cas.veritasapp._di.modules;



import com.cas.veritasapp._di.scopes.ActivityScope;
import com.cas.veritasapp.main.auth.AuthActivity;
import com.cas.veritasapp.main.auth.rvvm.AuthRepository;
import com.cas.veritasapp.main.auth.rvvm.AuthViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by funmiayinde on 2019-10-20.
 */
@Module
public class AuthModule {

    @ActivityScope
    @Provides
    AuthRepository provideAuthRepository(AuthActivity authActivity){
        return new AuthRepository(authActivity);
    }

    @ActivityScope
    @Provides
    AuthViewModel provideAuthViewModel(AuthRepository authRepository){
        return new AuthViewModel(authRepository);
    }
}
