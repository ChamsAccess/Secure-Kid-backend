package com.cas.veritasapp.main.home.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.databinding.FragmentPersonalDataBinding;
import com.cas.veritasapp.main.home.dialog.PreviewFragmentDialog;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentValidator;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.objects.Enrollment;
import com.cas.veritasapp.objects.Location;
import com.cas.veritasapp.objects.Personal;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.objects.payloads.NinPayload;
import com.cas.veritasapp.util.AppUtil;
import com.mikhaellopez.lazydatepicker.LazyDatePicker;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class PersonalDataFragment extends BaseFragment<FragmentPersonalDataBinding>
        implements View.OnClickListener, LazyDatePicker.OnDatePickListener, LazyDatePicker.OnDateSelectedListener {

    FragmentPersonalDataBinding binding;
    private boolean dismissDialog = false;

    private Personal personal;

    private EnrollmentValidator validator;

    @Inject
    EnrollmentViewModel viewModel;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal_data;
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

        this.initApp();
    }

    private void initApp() {
        validator = new EnrollmentValidator(viewModel);
        if (viewModel.getCurrent() != null && viewModel.getCurrent().getPersonalObject() != null){
            String dateOfBirth = viewModel.getCurrent().getPersonalObject().getDob();
            if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
                Date date = AppUtil.stringToDate(dateOfBirth);
                if (date != null) {
                    binding.dob.setDate(date);
                    binding.dob.setClickable(false);
                    binding.dob.setEnabled(false);
                }
            }
        }
        personal = new Personal();
        binding.personalTextView.setOnClickListener(v -> {
            PreviewFragmentDialog dialog = new PreviewFragmentDialog(viewModel.getCurrent());
            dialog.show(requireActivity().getSupportFragmentManager(), "Preview Data");
        });

        this.initData();

        binding.titleLinearLayout.setOnClickListener(this);
        binding.genderLinearLayout.setOnClickListener(this);
        binding.martialLinearLayout.setOnClickListener(this);
        binding.martialStatusRadioGroup.setOnClickListener(this);
        binding.saveBtn.setOnClickListener(this);

    }

    private void initData() {
        binding.titleRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int titleRadioGroup = group.getCheckedRadioButtonId();
            RadioButton radioButton = requireActivity().findViewById(titleRadioGroup);
            personal.setTitle(radioButton.getText().toString());
        });

        binding.genderRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int titleRadioGroup = group.getCheckedRadioButtonId();
            RadioButton radioButton = requireActivity().findViewById(titleRadioGroup);
            personal.setGender(radioButton.getText().toString());
        });

        binding.martialStatusRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int titleRadioGroup = group.getCheckedRadioButtonId();
            RadioButton radioButton = requireActivity().findViewById(titleRadioGroup);
            personal.setMaritalStatus(radioButton.getText().toString());
        });

        binding.dob.setOnDatePickListener(this);
        binding.dob.setOnDateSelectedListener(this);
        personal.setFirstName(binding.firstNameEditText.getText().toString());
    }

    private void setPersonalData() {
        personal.setFirstName(binding.firstNameEditText.getText().toString());
        personal.setSurname(binding.surnameEditText.getText().toString());
        personal.setMiddleName(binding.middleNameEditText.getText().toString());
        personal.setBvn(binding.bvnEditText.getText().toString());
        personal.setPhoneNumber(binding.phoneNumberEditText.getText().toString());
        personal.setNationality(binding.nationalityEditText.getText().toString());
        personal.setNin(binding.ninEditText.getText().toString());
        personal.setEmail(binding.emailEditText.getText().toString());
        personal.setMaiden(binding.maidenEditText.getText().toString());
        personal.setHouseNumber(binding.houseNumberEditText.getText().toString());
        personal.setCountry(binding.countryCodeEditText.getText().toString());
        Location location = new Location();
        location.setStreet(binding.streetEditText.getText().toString());
        location.setLga_code(binding.lgaCode.getText().toString());
        location.setCity(binding.cityEditText.getText().toString());
        location.setZip_code(binding.zipCodeEditText.getText().toString());
        location.setPOBox(binding.POBoxEditText.getText().toString());
        personal.setLocation(location);

        viewModel.getCurrent().setPersonalObject(personal);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleLinearLayout:
                int titleRadioGroup = binding.titleRadioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = requireActivity().findViewById(titleRadioGroup);
                personal.setTitle(radioButton.getText().toString());
                break;
            case R.id.genderLinearLayout:
                int genderRadioGroup = binding.genderRadioGroup.getCheckedRadioButtonId();
                RadioButton genderRadioButton = requireActivity().findViewById(genderRadioGroup);
                personal.setGender(genderRadioButton.getText().toString());
                break;
            case R.id.martialLinearLayout:
                int martialStatusRadioGroup = binding.martialStatusRadioGroup.getCheckedRadioButtonId();
                RadioButton martialRadioButton = requireActivity().findViewById(martialStatusRadioGroup);
                personal.setMaritalStatus(martialRadioButton.getText().toString());
                break;
            case R.id.martialStatusRadioGroup:
                break;
            case R.id.saveBtn:
                setPersonalData();
                showToast("Personal data saved successfully");
                break;
        }
    }


    @Override
    public void onDatePick(Date dateSelected) {
        binding.dob.setDate(dateSelected);
        personal.setDob(LazyDatePicker.dateToString(dateSelected, DATE_FORMAT));
        viewModel.getCurrent().setPersonalObject(personal);
    }

    @Override
    public void onDateSelected(Boolean dateSelected) {
//        showToast("Date selected:" + dateSelected);
    }


}
