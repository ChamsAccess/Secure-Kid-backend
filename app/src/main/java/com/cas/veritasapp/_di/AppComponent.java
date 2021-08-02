package com.cas.veritasapp._di;


import com.cas.veritasapp.VeritasApp;
import com.cas.veritasapp._di.modules.ContributeActivityModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by funmiayinde on 2019-09-25.
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ContributeActivityModule.class
})
public interface AppComponent {

    void inject(VeritasApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(VeritasApp app);

        AppComponent build();
    }
}
