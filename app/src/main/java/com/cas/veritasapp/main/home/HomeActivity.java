package com.cas.veritasapp.main.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseActivity;
import com.cas.veritasapp.databinding.HomeActivityBinding;;
import com.cas.veritasapp.main.home.fragments.DashboardFragment;
import com.cas.veritasapp.main.home.fragments.HistoryFragment;
import com.cas.veritasapp.main.home.fragments.NINFragment;
import com.cas.veritasapp.util.AppUtil;
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

//    @Override
//    public int getNavHost() {
//        return R.id.home_host_nav;
//    }

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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_shop:
                loadFragment(new DashboardFragment());
                return true;
            case R.id.navigation_wallet:
                loadFragment(new NINFragment());
                return true;
            case R.id.navigation_history:
                loadFragment(new HistoryFragment());
                return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment){
        AppUtil.loadFragment(HomeActivity.this, R.id.frame_container, fragment);
    }


}
