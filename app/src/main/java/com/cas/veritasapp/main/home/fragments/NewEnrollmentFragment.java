package com.cas.veritasapp.main.home.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.databinding.FragmentNewEnrollmentBinding;
import com.cas.veritasapp.util.AppUtil;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import org.jetbrains.annotations.NotNull;

import dagger.android.support.AndroidSupportInjection;

public class NewEnrollmentFragment extends BaseFragment<FragmentNewEnrollmentBinding> {

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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = getViewDataBinding();
        binding.executePendingBindings();
        binding.setLifecycleOwner(this);

//        AppUtil.loadFragment((AppCompatActivity) requireActivity(), R.id.frame_container,
//                new PersonalDataFragment());

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                requireActivity().getSupportFragmentManager(), FragmentPagerItems.with(requireActivity())
                .add("Personal Data", PersonalDataFragment.class)
                .add("Employment Record", EmploymentFragment.class)
                .add("Salary Structure", SalaryStructureFragment.class)
                .add("Next of Kin’s ", NextOfKinFragment.class)
                .add("Contribution’s Biometric Information", ContributionBioInfoFragment.class)
                .add("PFA Certification", PFACertificationFragment.class)
                .create());

        binding.viewpager.setAdapter(adapter);
        binding.viewpagertab.setViewPager(binding.viewpager);
    }
}
