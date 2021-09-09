package com.cas.veritasapp.main.home.dialog;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.listeners.OnItemSelectedListener;
import com.cas.veritasapp.databinding.FilterEnrollmentBinding;
import com.cas.veritasapp.util.AppUtil;
import com.cas.veritasapp.util.DateTimeUtils;
import com.cas.veritasapp.util.LogUtil;
import com.cas.veritasapp.util.ServiceUtil;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FilterFragmentDialog extends DialogFragment implements
        View.OnClickListener,
        MaterialSpinner.OnItemSelectedListener<String> {

    private OnItemSelectedListener<Map> listener;
    private String startDate, endDate;

    private int mYear, mMonth, mDay;

    FilterEnrollmentBinding binding;

    public FilterFragmentDialog(OnItemSelectedListener<Map> onItemSelectedListener) {
        super();
        this.listener = onItemSelectedListener;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
            dialog.setCancelable(false);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.filter_enrollment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initApp();
    }

    private void initApp() {
        binding.rsaPinStatusSpinner.setItems(AppConstant.RSAPIN_STATUS);
        binding.registrationStatusSpinner.setItems(AppConstant.REGISTRATION_STATUS);

        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        binding.rsaPinStatusSpinner.setOnItemSelectedListener(this);
        binding.registrationStatusSpinner.setOnItemSelectedListener(this);
        binding.searchButton.setOnClickListener(this);
        binding.cancelButton.setOnClickListener(this);
        binding.startDateSpinner.setOnClickListener(this);
        binding.endDateSpinner.setOnClickListener(this);
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
            case R.id.cancelButton:
                dismiss();
                break;
            case R.id.searchButton:
                Map<String, String> map = new HashMap<>();
                map.put("startDate", startDate);
                map.put("endDate", endDate);
                map.put("search", binding.searchEditText.getText().toString());
                map.put("registrationStatus", binding.registrationStatusSpinner.getText().toString());
                map.put("rsaPin", binding.rsaPinStatusSpinner.getText().toString());
                listener.ontItemSelected(map, AppConstant.FILTER_ENROLLMENT);
                dismiss();
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
        switch (view.getId()) {
            case R.id.rsaPinStatusSpinner:
                binding.rsaPinStatusSpinner.setText(item);
                break;
            case R.id.registrationStatusSpinner:
                binding.registrationStatusSpinner.setText(item);
                break;
        }
    }

}
