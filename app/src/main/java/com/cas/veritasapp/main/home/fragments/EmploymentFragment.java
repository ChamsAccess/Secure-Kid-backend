package com.cas.veritasapp.main.home.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.databinding.FragmentEmploymentBinding;
import com.cas.veritasapp.main.adapter.DropDownAdapter;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.core.data.entities.Country;
import com.cas.veritasapp.objects.DropDownObject;
import com.cas.veritasapp.core.data.entities.Employment;
import com.cas.veritasapp.core.data.entities.LGA;
import com.cas.veritasapp.core.data.entities.Location;
import com.cas.veritasapp.core.data.entities.State;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.util.AppUtil;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mikhaellopez.lazydatepicker.LazyDatePicker;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class EmploymentFragment extends BaseFragment<FragmentEmploymentBinding>
        implements View.OnClickListener, MaterialSpinner.OnItemSelectedListener<DropDownObject> {

    FragmentEmploymentBinding binding;

    @Inject
    EnrollmentViewModel viewModel;

    private Employment employment;

    private ArrayList<DropDownObject> dropDownObjectArrayList;

    private String countryId;
    private String stateId;
    private String lgaId;


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
        employment = (viewModel.getCurrent() != null && viewModel.getCurrent().getEmploymentObject() != null)
                ? viewModel.getCurrent().getEmploymentObject() : new Employment();
        this.initApp();
    }

    private void initApp() {

        viewModel.findCountries(null).observe(getViewLifecycleOwner(), this::performAction);

        binding.countrySpinner.setOnItemSelectedListener(this);
        binding.stateSpinner.setOnItemSelectedListener(this);
        binding.lgaSpinner.setOnItemSelectedListener(this);

        if (employment.getDateJoined() != null) {
            binding.dateJoined.setDate(AppUtil.stringToDate(employment.getDateJoined()));
        }
        if (employment.getDateOfCurrentEmployment() != null) {
            Date date = AppUtil.stringToDate(employment.getDateOfCurrentEmployment());
            binding.dateOfCurrentEmployment.setDate(AppUtil
                    .stringToDate(LazyDatePicker.dateToString(date, DATE_FORMAT)));
        }
        if (employment.getDateOfFirstAppointment() != null) {
            binding.dateOfFirstAppointment.setDate(AppUtil.stringToDate(employment.getDateOfFirstAppointment()));
        }
        if (employment.getDateOfTransferService() != null) {
            binding.dateOfTransferService.setDate(AppUtil.stringToDate(employment.getDateOfTransferService()));
        }

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
        employment.setCountry(countryId);
        employment.setHouseNumber(binding.employeeHoursNumber.getText().toString());
        employment.setCountryCode("NG");

        Location location = new Location();
        location.setStreet(binding.emloyeeStreeName.getText().toString());
        location.setLga_code(lgaId);
        location.setCity(binding.employeeCityEditText.getText().toString());
        location.setStateCode(stateId);
        location.setZip_code(binding.employeeZipCodeEditText.getText().toString());
        location.setPOBox(binding.POBoxEditText.getText().toString());

        employment.setLocation(location);
    }

    @Override
    public void onLoad(String key) {
        super.onLoad(key);
    }


    @Override
    public void onSuccess(Object obj, String key) {
        super.onSuccess(obj, key);
        if (obj != null) {
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
