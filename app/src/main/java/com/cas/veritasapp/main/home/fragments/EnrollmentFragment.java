package com.cas.veritasapp.main.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.databinding.FragmentNewEnrollmentBinding;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import org.jetbrains.annotations.NotNull;


import dagger.android.support.AndroidSupportInjection;

public class EnrollmentFragment extends BaseFragment<FragmentNewEnrollmentBinding> {

    FragmentNewEnrollmentBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new_enrollment;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = getViewDataBinding();
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                requireActivity().getSupportFragmentManager(), FragmentPagerItems.with(requireActivity())
                .add("Personal Data", PersonalDataFragment.class)
                .add("Employment Record", PersonalDataFragment.class)
                .add("Salary Structure", PersonalDataFragment.class)
                .add("Next of Kin’s ", PersonalDataFragment.class)
                .add("Contribution’s Biometric Information", PersonalDataFragment.class)
                .add("PFA Certification", PersonalDataFragment.class)
                .create());

        binding.viewpager.setAdapter(adapter);
        binding.viewpagertab.setViewPager(binding.viewpager);
    }
}
