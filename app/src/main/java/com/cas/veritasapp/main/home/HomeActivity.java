package com.cas.veritasapp.main.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseActivity;
import com.cas.veritasapp.databinding.HomeActivityBinding;;
import com.cas.veritasapp.main.adapter.HomeTabAdapter;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by funmiayinde on 2019-10-11.
 */
public class HomeActivity extends BaseActivity<HomeActivityBinding>
        implements HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Fragment> injector;

    HomeActivityBinding binding;

    @Inject
    HomeTabAdapter tabAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        initApp();
    }

    private void initApp() {
        tabAdapter.setTabCount(3);
        binding.viewPager.setAdapter(tabAdapter);
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Dashboard"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("New Enrollment"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("History"));


        binding.viewPager.setOffscreenPageLimit(binding.tabLayout.getTabCount());
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return injector;
    }


}
