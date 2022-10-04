package com.cas.veritasapp.main.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.cas.veritasapp.main.home.fragments.CustomerEnquiryFragment;
import com.cas.veritasapp.main.home.fragments.DashboardFragment;
import com.cas.veritasapp.main.home.fragments.HistoryFragment;
import com.cas.veritasapp.main.home.fragments.NINFragment;
import com.cas.veritasapp.main.home.fragments.NewEnrollmentFragment;

/**
 * @author funmiayinde
 */
public class HomeTabAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public HomeTabAdapter(FragmentManager fm) {
        super(fm);
        this.tabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DashboardFragment();
            case 1:
                return new NewEnrollmentFragment();
            case 2:
                return new HistoryFragment();
            case 3:
                return new CustomerEnquiryFragment();
            default:
                return null;
        }
    }

    public void setTabCount(int tabCount) {
        this.tabCount = tabCount;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
