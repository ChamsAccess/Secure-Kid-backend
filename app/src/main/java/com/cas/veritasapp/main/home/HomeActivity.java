package com.cas.veritasapp.main.home;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseActivity;
import com.cas.veritasapp.databinding.HomeActivityBinding;;
import com.cas.veritasapp.main.home.fragments.DashboardFragment;
import com.cas.veritasapp.main.home.fragments.EnrollmentFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by funmiayinde on 2019-10-11.
 */
public class HomeActivity extends BaseActivity<HomeActivityBinding>
        implements HasSupportFragmentInjector,
        BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    DispatchingAndroidInjector<Fragment> injector;

    HomeActivityBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.home_activity;
    }


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        binding.navigation.setOnNavigationItemSelectedListener(this);
        loadFragment(new DashboardFragment());
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return injector;
    }


    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.navigation_shop:
                fragment = new DashboardFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_wallet:
                fragment = new EnrollmentFragment();
                loadFragment(fragment);
                return true;
        }
        return false;
    }
}
