package com.cas.veritasapp.main.home.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.data.entities.Employer;
import com.cas.veritasapp.core.data.entities.Enrollment;
import com.cas.veritasapp.core.listeners.OnItemSelectedListener;
import com.cas.veritasapp.databinding.FragmentEmploymentBinding;
import com.cas.veritasapp.main.adapter.DropDownAdapter;
import com.cas.veritasapp.main.adapter.EmployerAdapter;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentValidator;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.core.data.entities.Country;
import com.cas.veritasapp.objects.DropDownObject;
import com.cas.veritasapp.core.data.entities.Employment;
import com.cas.veritasapp.core.data.entities.LGA;
import com.cas.veritasapp.core.data.entities.Location;
import com.cas.veritasapp.core.data.entities.State;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.util.AppUtil;
import com.cas.veritasapp.util.LogUtil;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mikhaellopez.lazydatepicker.LazyDatePicker;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class EmploymentFragment extends BaseFragment<FragmentEmploymentBinding>
        implements View.OnClickListener, MaterialSpinner.OnItemSelectedListener<DropDownObject>, OnItemSelectedListener {

    FragmentEmploymentBinding binding;

    @Inject
    EnrollmentViewModel viewModel;

    private Employment employment;

    private ArrayList<DropDownObject> dropDownObjectArrayList;

    private EmployerAdapter adapter;
    private ArrayList<Employer> employmentArrayList = new ArrayList();

    private String countryId;
    private String stateId;
    private String lgaId;

    private Country country;
    private State state;
    private LGA lga;


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
        try {
            employmentArrayList = new ArrayList();
            viewModel.findCountries(null).observe(getViewLifecycleOwner(), this::performAction);

            country = ((
                    viewModel.getCurrent() != null &&
                            viewModel.getCurrent().getEmploymentObject() != null &&
                            viewModel.getCurrent().getEmploymentObject().getCountryObject() != null
            )) ? viewModel.getCurrent().getEmploymentObject().getCountryObject() : new Country();

            state = ((
                    viewModel.getCurrent() != null &&
                            viewModel.getCurrent().getEmploymentObject() != null &&
                            viewModel.getCurrent().getEmploymentObject().getLocation() != null &&
                            viewModel.getCurrent().getEmploymentObject().getLocation().getStateObject() != null
            )) ? viewModel.getCurrent().getEmploymentObject().getLocation().getStateObject() : new State();

            lga = ((
                    viewModel.getCurrent() != null &&
                            viewModel.getCurrent().getEmploymentObject() != null &&
                            viewModel.getCurrent().getEmploymentObject().getLocation() != null &&
                            viewModel.getCurrent().getEmploymentObject().getLocation().getLgaObject() != null
            )) ? viewModel.getCurrent().getEmploymentObject().getLocation().getLgaObject() : new LGA();

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

            binding.employerFullNameEditText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    Map<String, Object> map = new HashMap();
                    map.put("employerName", s.toString());
                    LogUtil.error("search-key:::" + s);
                    if (!s.toString().isEmpty()) {
                        searchEmployer(map);
                    }
                }
            });
            viewModel.getCurrent().setEmploymentObject(employment);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void searchEmployer(Map<String, Object> map) {
        viewModel.searchEmployer(map).observe(getViewLifecycleOwner(), this::performAction);
    }

    private void setEmploymentData() {
        Enrollment enrollment = viewModel.getCurrent();
        employment.setEmployerCardId(binding.employeeCardNoEditText.getText().toString());
        employment.setEmployerName(binding.employerFullNameEditText.getText().toString());
        employment.setServiceId(binding.employeeServiceId.getText().toString());
        employment.setEmployerPhone(binding.employerPhoneEditText.getText().toString());
        employment.setEmployeeIPPSNumber(binding.employeeIPPSNumberEditText.getText().toString());
        employment.setNatureOfBusiness(binding.natureOfBusinessEditTxt.getText().toString());

        employment.setHouseNumber(binding.employeeHoursNumber.getText().toString());
        employment.setCountryCode("NG");

        country = (enrollment != null
                && enrollment.getPersonalObject() != null
                && enrollment.getPersonalObject().getCountryObject() != null)
                ? enrollment.getPersonalObject().getCountryObject()
                : country;
        employment.setCountryObject(country);
        employment.setCountryObject(country);
        employment.setCountryCode(country.getId());
        employment.setCountry((country != null
                && country.getId() != null
                && (countryId == null || countryId.isEmpty()))
                ? country.getId()
                : countryId);

        Location location = ((
                enrollment != null &&
                        enrollment.getEmploymentObject() != null &&
                        enrollment.getEmploymentObject().getLocation() != null
        )) ? viewModel.getCurrent().getEmploymentObject().getLocation() : new Location();

        location.setStreet(binding.employeeCityEditText.getText().toString());
        location.setLga_code(lgaId);
        location.setPOBox(binding.POBoxEditText.getText().toString());

        state = (enrollment != null
                && enrollment.getEmploymentObject() != null
                && enrollment.getEmploymentObject().getLocation() != null
                && enrollment.getEmploymentObject().getLocation().getStateObject() != null)
                ? enrollment.getEmploymentObject().getLocation().getStateObject()
                : state;

        location.setStateObject(state);
        location.setStateCode((state != null
                && state.getId() != null
                && (stateId == null || stateId.isEmpty()))
                ? state.getId()
                : stateId);

        lga = (enrollment != null
                && enrollment.getEmploymentObject() != null
                && enrollment.getEmploymentObject().getLocation() != null
                && enrollment.getEmploymentObject().getLocation().getLgaObject() != null)
                ? enrollment.getEmploymentObject().getLocation().getLgaObject()
                : lga;

        location.setLgaObject(lga);
        location.setLga_code((lga != null
                && lga.getId() != null
                && (lgaId == null || lgaId.isEmpty()))
                ? lga.getId()
                : lgaId);

        location.setStreet(binding.emloyeeStreeName.getText().toString());
        location.setCity(binding.employeeCityEditText.getText().toString());
        location.setZip_code(binding.employeeZipCodeEditText.getText().toString());
        location.setPOBox(binding.POBoxEditText.getText().toString());

        employment.setLocation(location);

        String validationMessage = EnrollmentValidator.validateEmployment(employment);
        if (!validationMessage.isEmpty()) {
            showToast(validationMessage);
            return;
        }
        viewModel.getCurrent().setEmploymentObject(employment);
        showToast("Employment data saved successfully");
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
            if (key.equals(AppConstant.GET_SEARCHED_EMPLOYER)) {
                if (obj instanceof List) {
                    employmentArrayList = (ArrayList<Employer>) obj;
                    adapter = new EmployerAdapter(requireActivity(), employmentArrayList, this);
                    binding.employerFullNameEditText.setThreshold(1);
                    binding.employerFullNameEditText.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
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
                break;

        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, DropDownObject item) {
        switch (view.getId()) {
            case R.id.stateSpinner:
                viewModel.findState(item.get_id(), null).observe(getViewLifecycleOwner(), this::performAction);
                stateId = item.get_id();
                state.setId(item.get_id());
                state.setName(item.getName());
                state.setCode(item.getCode());
                binding.stateSpinner.setText(item.getName());
                break;
            case R.id.lgaSpinner:
                binding.lgaSpinner.setText(item.getName());
                lgaId = item.get_id();
                lga.setId(item.get_id());
                lga.setName(item.getName());
                lga.setCode(item.getCode());
                break;
            case R.id.countrySpinner:
                viewModel.findCountry(item.get_id(), null).observe(getViewLifecycleOwner(), this::performAction);
                countryId = item.get_id();
                country.setId(item.get_id());
                country.setName(item.getName());
                country.setCode(item.getCode());
                binding.countrySpinner.setText(item.getName());
                break;
        }
    }

    @Override
    public void ontItemSelected(Object item, String key) {
        if (item != null && key.equals(AppConstant.SHOW_EMPLOYER_DETAILS)){
            LogUtil.error("Employer-details:::" + (Employer) item);
            Employer employer = (Employer) item;
            binding.employeeCardNoEditText.setText(employer.getEmployerID());
            binding.employerFullNameEditText.setText(employer.getName());
            binding.employerPhoneEditText.setText(employer.getPhone());
            binding.emloyeeStreeName.setText(employer.getAddress());
            employmentArrayList.clear();
        }
    }
}
