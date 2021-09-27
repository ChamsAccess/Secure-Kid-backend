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
import com.cas.veritasapp.main.adapter.DropDownAdapter;
import com.cas.veritasapp.main.home.dialog.PreviewFragmentDialog;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentValidator;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.objects.Country;
import com.cas.veritasapp.objects.DropDownObject;
import com.cas.veritasapp.objects.Enrollment;
import com.cas.veritasapp.objects.LGA;
import com.cas.veritasapp.objects.Location;
import com.cas.veritasapp.objects.Personal;
import com.cas.veritasapp.objects.State;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.objects.payloads.NinPayload;
import com.cas.veritasapp.util.AppUtil;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mikhaellopez.lazydatepicker.LazyDatePicker;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class PersonalDataFragment extends BaseFragment<FragmentPersonalDataBinding>
        implements View.OnClickListener,
        LazyDatePicker.OnDatePickListener, LazyDatePicker.OnDateSelectedListener,
        MaterialSpinner.OnItemSelectedListener<DropDownObject> {

    FragmentPersonalDataBinding binding;
    private boolean dismissDialog = false;

    private Personal personal;

    private EnrollmentValidator validator;

    @Inject
    EnrollmentViewModel viewModel;

    private ArrayList<DropDownObject> dropDownObjectArrayList;

    private String countryId;
    private String stateId;
    private String lgaId;


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
        if (viewModel.getCurrent() != null && viewModel.getCurrent().getPersonalObject() != null) {
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

        this.initData();

        viewModel.findCountries(null).observe(getViewLifecycleOwner(), this::performAction);


        binding.countrySpinner.setOnItemSelectedListener(this);
        binding.stateSpinner.setOnItemSelectedListener(this);
        binding.lgaSpinner.setOnItemSelectedListener(this);

        binding.titleLinearLayout.setOnClickListener(this);
        binding.genderLinearLayout.setOnClickListener(this);
        binding.martialLinearLayout.setOnClickListener(this);
        binding.martialStatusRadioGroup.setOnClickListener(this);
        binding.saveBtn.setOnClickListener(this);
        binding.skipButton.setOnClickListener(this);
        binding.searchNinButton.setOnClickListener(this);

//        binding.ninEditTxt.setText("17520041640");

        binding.findNinRelativeLayout.setVisibility(View.VISIBLE);
        binding.personalLinearLayout.setVisibility(View.GONE);

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
        personal.setCountry(countryId);
        Location location = new Location();
        location.setStreet(binding.streetEditText.getText().toString());
        location.setStateCode(stateId);
        location.setLga_code(lgaId);
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

            case R.id.skipButton:
                binding.findNinRelativeLayout.setVisibility(View.GONE);
                binding.personalLinearLayout.setVisibility(View.VISIBLE);
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
        if (obj != null) {
            if (AppConstant.FIND_NIN_DATA.equals(key)) {
                if (obj instanceof NinPayload) {
                    NinPayload payload = (NinPayload) obj;
                    Enrollment enrollment = NinPayload.getNINPayload(payload);
                    viewModel.setCurrent(enrollment);
                    bundle.putSerializable(AppConstant.ENROLLMENT, enrollment);
                    binding.findNinRelativeLayout.setVisibility(View.GONE);
                    binding.personalLinearLayout.setVisibility(View.VISIBLE);
                }
            }
            if (key.equals(AppConstant.FIND_COUNTRIES)) {
                if (obj instanceof List) {
                    ArrayList<Country> countries = (ArrayList<Country>) obj;
                    if (countries != null && !countries.isEmpty()) {
                        dropDownObjectArrayList = new ArrayList<>();
                        for (Country country : countries) {
                            DropDownObject dropDownObject = new DropDownObject(country.getId(), country.getName());
                            dropDownObjectArrayList.add(dropDownObject);
                        }
                        DropDownAdapter dropDownAdapter = new DropDownAdapter(getContext(), dropDownObjectArrayList);
                        dropDownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.countrySpinner.setAdapter(dropDownAdapter);

//                        String id = countries.get(159).getId();
//                        int index = AppUtil.getSpinnerIndex(binding.countrySpinner, id);
//                        viewModel.findCountry(id, null).observe(getViewLifecycleOwner(), this::performAction);
//                        binding.countrySpinner.setSelectedIndex(index);
                    }
                }
            }
            if (key.equals(AppConstant.FIND_COUNTRY)) {
                Country country = (Country) obj;
                if (country != null) {
                    dropDownObjectArrayList = new ArrayList<>();
                    for (State state : country.getStates()) {
                        DropDownObject dropDownObject = new DropDownObject(state.getId(), state.getName());
                        dropDownObjectArrayList.add(dropDownObject);
                    }
                    DropDownAdapter dropDownAdapter = new DropDownAdapter(getContext(), dropDownObjectArrayList);
                    dropDownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.stateSpinner.setAdapter(dropDownAdapter);
                }
            }
            if (key.equals(AppConstant.FIND_STATE)) {
                State state = (State) obj;
                if (state != null) {
                    dropDownObjectArrayList = new ArrayList<>();
                    for (LGA lga : state.getLgas()) {
                        DropDownObject dropDownObject = new DropDownObject(lga.getId(), lga.getName());
                        dropDownObjectArrayList.add(dropDownObject);
                    }
                    DropDownAdapter dropDownAdapter = new DropDownAdapter(getContext(), dropDownObjectArrayList);
                    dropDownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.lgaSpinner.setAdapter(dropDownAdapter);
                }
            }

        }
    }

    @Override
    public void onError(ApiError apiError, String key) {
        super.onError(apiError, key);
        viewModel.setError(apiError.getMessage());
        showToast(apiError.getMessage());
    }


    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, DropDownObject item) {
        switch (view.getId()) {
            case R.id.stateSpinner:
                viewModel.findState(item.get_id(), null).observe(getViewLifecycleOwner(), this::performAction);
                stateId = item.get_id();
                binding.stateSpinner.setText(item.getName());
                break;
            case R.id.lgaSpinner:
                binding.lgaSpinner.setText(item.getName());
                lgaId = item.get_id();
                break;
            case R.id.countrySpinner:
                viewModel.findCountry(item.get_id(), null).observe(getViewLifecycleOwner(), this::performAction);
                countryId = item.get_id();
                binding.countrySpinner.setText(item.getName());
                break;
        }
    }
}
