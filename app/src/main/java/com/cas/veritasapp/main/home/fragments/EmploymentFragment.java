package com.cas.veritasapp.main.home.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.databinding.FragmentEmploymentBinding;
import com.cas.veritasapp.databinding.FragmentEnrollmentBinding;
import com.cas.veritasapp.databinding.FragmentNewEnrollmentBinding;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.objects.Employment;
import com.cas.veritasapp.objects.Location;
import com.mikhaellopez.lazydatepicker.LazyDatePicker;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class EmploymentFragment extends BaseFragment<FragmentEmploymentBinding>
        implements View.OnClickListener {

    FragmentEmploymentBinding binding;

    @Inject
    EnrollmentViewModel viewModel;

    private Employment employment;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_employment;
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
        employment = new Employment();
        this.initApp();
    }

    private void initApp() {

        binding.dateJoined.setOnDatePickListener(dateSelected -> {
            binding.dateJoined.setDate(dateSelected);
            employment.setDateJoined(LazyDatePicker.dateToString(dateSelected, DATE_FORMAT));
        });
        binding.dateOfCurrentEmployment.setOnDatePickListener(dateSelected -> {
            binding.dateOfCurrentEmployment.setDate(dateSelected);
            employment.setDateOfCurrentEmployment(LazyDatePicker.dateToString(dateSelected, DATE_FORMAT));
        });
        binding.dateOfFirstAppointment.setOnDatePickListener(dateSelected -> {
            binding.dateOfFirstAppointment.setDate(dateSelected);
            employment.setDateOfFirstAppointment(LazyDatePicker.dateToString(dateSelected, DATE_FORMAT));
        });
        binding.dateOfTransferService.setOnDatePickListener(dateSelected -> {
            binding.dateOfTransferService.setDate(dateSelected);
            employment.setDateOfTransferService(LazyDatePicker.dateToString(dateSelected, DATE_FORMAT));
        });

        binding.saveBtn.setOnClickListener(this);

        binding.sectorClassificationRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int titleRadioGroup = group.getCheckedRadioButtonId();
            RadioButton radioButton = requireActivity().findViewById(titleRadioGroup);
            employment.setSectorClassification(radioButton.getText().toString());
        });

        binding.employeeUnderIPPSRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int titleRadioGroup = group.getCheckedRadioButtonId();
            RadioButton radioButton = requireActivity().findViewById(titleRadioGroup);
            employment.setEmployerUnderIPPS(radioButton.getText().toString());
        });

        viewModel.getCurrent().setEmploymentObject(employment);
    }


    private void setEmploymentData() {
        employment.setEmployerCardId(binding.employeeCardNoEditText.getText().toString());
        employment.setEmployerName(binding.employerFullNameEditText.getText().toString());
        employment.setServiceId(binding.employeeServiceId.getText().toString());
        employment.setEmployerPhone(binding.employerPhoneEditText.getText().toString());
        employment.setEmployeeIPPSNumber(binding.employeeIPPSNumberEditText.getText().toString());
        employment.setNatureOfBusiness(binding.natureOfBusinessEditTxt.getText().toString());
        employment.setCountry(binding.employeeCountryEditText.getText().toString());
        employment.setHouseNumber(binding.employeeHoursNumber.getText().toString());
        employment.setCountryCode("NG");

        Location location = new Location();
        location.setStreet(binding.emloyeeStreeName.getText().toString());
        location.setLga_code(binding.employeeLGCode.getText().toString());
        location.setCity(binding.employeeCityEditText.getText().toString());
        location.setStateCode(binding.employeeStateCodeEditText.getText().toString());
        location.setZip_code(binding.employeeZipCodeEditText.getText().toString());
        location.setPOBox(binding.employeePOXBoxEditText.getText().toString());

        employment.setLocation(location);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveBtn:
                setEmploymentData();
                showToast("Employment data saved successfully");
                viewModel.getCurrent().setEmploymentObject(employment);
                break;

        }
    }
}
