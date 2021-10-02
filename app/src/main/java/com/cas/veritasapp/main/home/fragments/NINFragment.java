package com.cas.veritasapp.main.home.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.databinding.NinSearchBinding;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.core.data.entities.Enrollment;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.objects.payloads.NinPayload;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class NINFragment extends BaseFragment<NinSearchBinding> implements View.OnClickListener {

    NinSearchBinding binding;

    @Inject
    EnrollmentViewModel viewModel;

    @Override
    public int getLayoutId() {
        return R.layout.nin_search;
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
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
        binding.setLifecycleOwner(this);
        binding.skipButton.setOnClickListener(this);
        binding.searchNinButton.setOnClickListener(this);
//        binding.ninEditTxt.setText("17520041640");


    }

    @Override
    public void onLoad(String key) {
        super.onLoad(key);
        if (AppConstant.FIND_NIN_DATA.equals(key)) {
            showProgressDialog();
        }
    }

    @Override
    public void onSuccess(Object obj, String key) {
        super.onSuccess(obj, key);
        if (AppConstant.FIND_NIN_DATA.equals(key)) {
            if (obj instanceof NinPayload) {
                NinPayload payload = (NinPayload) obj;
                Enrollment enrollment = NinPayload.getNINPayload(payload);
                viewModel.setCurrent(enrollment);
                bundle.putSerializable(AppConstant.ENROLLMENT, enrollment);
                showToast("Loading data,please wait...");
                loadFragment();
            }
        }
    }

    @Override
    public void onError(ApiError apiError, String key) {
        super.onError(apiError, key);
        viewModel.setError(apiError.getMessage());
        showToast(apiError.getMessage());
    }

    private void loadFragment() {
//        AppUtil.loadFragment((AppCompatActivity) requireActivity(), R.id.frame_container,
//                new NewEnrollmentFragment(), bundle);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.skipButton:
                loadFragment();
                break;
            case R.id.searchNinButton:
                String nin = binding.ninEditTxt.getText().toString();
                if (nin.isEmpty()) {
                    showToast("Please enter NIN");
                    return;
                }
                showProgressDialog();
                Map<String, String> request = new HashMap<>();
                request.put("nin", nin);
                viewModel.findNin(request).observe(getViewLifecycleOwner(), this::performAction);
        }
    }
}
