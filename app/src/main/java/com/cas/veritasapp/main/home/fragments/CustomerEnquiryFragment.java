package com.cas.veritasapp.main.home.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.listeners.OnItemSelectedListener;
import com.cas.veritasapp.databinding.CustomerEnquiryBinding;
import com.cas.veritasapp.databinding.FilterEnrollmentBinding;
import com.cas.veritasapp.databinding.NinSearchBinding;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.util.AppUtil;
import com.cas.veritasapp.util.DateTimeFormat;
import com.cas.veritasapp.util.DateTimeUtils;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class CustomerEnquiryFragment  extends BaseFragment<CustomerEnquiryBinding> implements
        View.OnClickListener{

    private String startDate, endDate;

    private int mYear, mMonth, mDay;

    CustomerEnquiryBinding binding;

    @Inject
    EnrollmentViewModel viewModel;

    @Override
    public int getLayoutId() {
        return R.layout.customer_enquiry;
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
        initApp();
    }

    private void initApp() {
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        binding.generateBalanceButton.setOnClickListener(this);
        binding.generatePdfButton.setOnClickListener(this);
        binding.startDateSpinner.setOnClickListener(this);
        binding.endDateSpinner.setOnClickListener(this);
    }

    @Override
    public void onLoad(String key) {
        super.onLoad(key);
        if (AppConstant.SEND_BALANCE.equals(key)) {
            showProgressDialog();
        }
        if (AppConstant.SEND_GENERATE_PDF.equals(key)) {
            showProgressDialog();
        }
    }

    @Override
    public void onError(ApiError apiError, String key) {
        super.onError(apiError, key);
        showToast(apiError.getMessage());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startDateSpinner:
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (mView, year, month, dayOfMonth) -> {
                    startDate = AppUtil.dateFormatter(AppUtil.customCalendar(year, month, dayOfMonth));
                    binding.startDateSpinner.setText(DateTimeUtils.formatWithPattern(startDate, AppConstant.DATE_PATTERN));
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.endDateSpinner:
                DatePickerDialog endDatePickerDialog = new DatePickerDialog(getActivity(), (mView, year, month, dayOfMonth) -> {
                    endDate = AppUtil.dateFormatter(AppUtil.customCalendar(year, month, dayOfMonth));
                    binding.endDateSpinner.setText(DateTimeUtils.formatWithPattern(endDate, AppConstant.DATE_PATTERN));
                }, mYear, mMonth, mDay);
                endDatePickerDialog.show();
                break;
            case R.id.generatePdfButton:
                if (startDate.isEmpty() && endDate.isEmpty()){
                    showToast("Start and end date is required");
                    return;
                }
                Map<String, Object> query = new HashMap<>();
                query.put("startDate", DateTimeUtils.formatWithPattern(startDate, DateTimeFormat.DATE_PATTERN_1));
                query.put("endDate", DateTimeUtils.formatWithPattern(endDate, DateTimeFormat.DATE_PATTERN_1));
                if (!binding.emailEditText.toString().isEmpty()){
                    query.put("email", binding.emailEditText.getText().toString());
                }
                viewModel.generateAndSendPDF(query).observe(getViewLifecycleOwner(), this::performAction);
                break;
            case R.id.generateBalanceButton:
                if (binding.phoneNumberEditText.getText().toString().isEmpty()){
                    showToast("Phone number is required");
                    return;
                }
                Map<String, Object> reqBody = new HashMap<>();
                reqBody.put("phone_number", binding.phoneNumberEditText.getText().toString());
                viewModel.generateAnSendBalance(reqBody).observe(getViewLifecycleOwner(), this::performAction);
                break;
        }
    }

}
