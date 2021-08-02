package com.cas.veritasapp.main.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.databinding.FragmentNewEnrollmentBinding;
import com.cas.veritasapp.databinding.NinSearchBinding;

import org.jetbrains.annotations.NotNull;

import dagger.android.support.AndroidSupportInjection;

public class NINFragment extends BaseFragment<NinSearchBinding> {

    NinSearchBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.nin_search;
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
    }
}
