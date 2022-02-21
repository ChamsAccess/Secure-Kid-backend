package com.cas.veritasapp.main.home.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.data.entities.Country;
import com.cas.veritasapp.core.data.entities.Enrollment;
import com.cas.veritasapp.core.data.entities.LGA;
import com.cas.veritasapp.core.data.entities.Location;
import com.cas.veritasapp.core.data.entities.Personal;
import com.cas.veritasapp.core.data.entities.State;
import com.cas.veritasapp.databinding.FragmentPersonalDataBinding;
import com.cas.veritasapp.main.adapter.DropDownAdapter;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentValidator;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.objects.DropDownObject;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.objects.payloads.NinPayload;
import com.cas.veritasapp.util.AppUtil;
import com.cas.veritasapp.util.DateTimeUtils;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mikhaellopez.lazydatepicker.LazyDatePicker;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
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

    private String countryId;
    private String residenceStateId;
    private String stateId;
    private String residenceLgaId;
    private String lgaId;

    private Country country;
    private State state;
    private State residenceState;
    private LGA lga;
    private LGA residenceLGA;

    private String startDate, endDate;

    private int mYear, mMonth, mDay;

    private EnrollmentValidator validator;

    @Inject
    EnrollmentViewModel viewModel;

    private ArrayList<DropDownObject> dropDownObjectArrayList = new ArrayList<>();


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

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (viewModel.getCurrent() != null &&
                (viewModel.getCurrent().get_id() != null && !viewModel.getCurrent().get_id().isEmpty())) {
            binding.findNinRelativeLayout.setVisibility(View.GONE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewModel.getCurrent() != null &&
                (viewModel.getCurrent().get_id() != null && !viewModel.getCurrent().get_id().isEmpty())) {
            binding.findNinRelativeLayout.setVisibility(View.GONE);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    private void initApp() {
        if (viewModel.getCurrent() != null &&
                (viewModel.getCurrent().get_id() != null && !viewModel.getCurrent().get_id().isEmpty())) {
            binding.findNinRelativeLayout.setVisibility(View.GONE);
        }
        country = ((viewModel.getCurrent() != null
                && viewModel.getCurrent().getPersonalObject() != null
                && viewModel.getCurrent().getPersonalObject().getCountryObject() != null))
                ? viewModel.getCurrent().getPersonalObject().getCountryObject() : new Country();

        Map<String, String> map = new HashMap();
        if (country != null && country.getId() != null && country.getId().isEmpty()) {
            map.put("name", "Nigeria");
        }
        viewModel.findCountries(map).observe(getViewLifecycleOwner(), this::performAction);

        validator = new EnrollmentValidator(viewModel);

        personal = ((viewModel.getCurrent() != null && viewModel.getCurrent().getPersonalObject() != null))
                ? viewModel.getCurrent().getPersonalObject() : new Personal();

        if (personal != null && personal.getDob() != null) {
            String dateOfBirth = personal.getDob();
            Calendar calendar = AppUtil.toCalendar(AppUtil.stringToDate(dateOfBirth));
            binding.dob.setText(AppUtil.dateFormatter(calendar, DATE_FORMAT));
            personal.setDob(binding.dob.getText().toString());
        }


        if (country != null && (country.getName() != null && country.getId() != null)) {
            dropDownObjectArrayList.add(new DropDownObject(country.getId(), country.getName()));
            DropDownAdapter dropDownAdapter = new DropDownAdapter(getContext(), dropDownObjectArrayList);
            dropDownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.countrySpinner.setAdapter(dropDownAdapter);
//            int index = AppUtil.getSpinnerIndex(binding.countrySpinner, country.getId());
//            binding.countrySpinner.setSelectedIndex(index);
        }

        state = new State();
        residenceState = new State();

        lga = new LGA();
        residenceLGA = new LGA();

        this.initData();


        binding.countrySpinner.setOnItemSelectedListener(this);
        binding.residenceStateSpinner.setOnItemSelectedListener(this);
        binding.stateInfoSpinner.setOnItemSelectedListener(this);
        binding.residenceLgaSpinner.setOnItemSelectedListener(this);
        binding.lgaInfoSpinner.setOnItemSelectedListener(this);

        binding.titleLinearLayout.setOnClickListener(this);
        binding.genderLinearLayout.setOnClickListener(this);
        binding.martialLinearLayout.setOnClickListener(this);
        binding.martialStatusRadioGroup.setOnClickListener(this);
        binding.saveBtn.setOnClickListener(this);
        binding.skipButton.setOnClickListener(this);
        binding.searchNinButton.setOnClickListener(this);

        String dateOfBirth = personal.getDob();
        if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
            Date date = AppUtil.stringToDate(dateOfBirth);
            if (date != null) {
                binding.dob.setClickable(false);
                binding.dob.setEnabled(false);
            }
        }

//        binding.ninEditTxt.setText("17520041640");
        if (viewModel.getCurrent() != null &&
                (viewModel.getCurrent().get_id() != null && !viewModel.getCurrent().get_id().isEmpty())) {
            binding.findNinRelativeLayout.setVisibility(View.GONE);
            binding.personalLinearLayout.setVisibility(View.VISIBLE);
        } else {
            binding.findNinRelativeLayout.setVisibility(View.VISIBLE);
            binding.personalLinearLayout.setVisibility(View.GONE);
        }

    }

    private void initData() {
        try {
            binding.titleRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                int titleRadioGroup = group.getCheckedRadioButtonId();
                RadioButton radioButton = requireActivity().findViewById(titleRadioGroup);
                if (radioButton != null) {
                    personal.setTitle(radioButton.getText().toString());
                }
            });

            binding.genderRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                int titleRadioGroup = group.getCheckedRadioButtonId();
                RadioButton radioButton = requireActivity().findViewById(titleRadioGroup);
                if (radioButton != null) {
                    personal.setGender(radioButton.getText().toString());
                }
            });

            binding.martialStatusRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                int titleRadioGroup = group.getCheckedRadioButtonId();
                RadioButton radioButton = requireActivity().findViewById(titleRadioGroup);
                personal.setMaritalStatus(radioButton.getText().toString());
            });

            binding.dob.setOnClickListener(this);
            binding.dob.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    showDate();
                }
            });
            personal.setFirstName(binding.firstNameEditText.getText().toString());
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    private void setPersonalData() {
        Enrollment enrollment = viewModel.getCurrent();
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
        personal.setDob(binding.dob.getText().toString());

        country = (enrollment != null
                && enrollment.getPersonalObject() != null
                && enrollment.getPersonalObject().getCountryObject() != null)
                ? enrollment.getPersonalObject().getCountryObject()
                : country;
        personal.setCountryObject(country);
        personal.setCountry((country != null
                && country.getId() != null
                && (countryId == null || countryId.isEmpty()))
                ? country.getId()
                : countryId);

        // state of origin
        state = (enrollment != null
                && enrollment.getPersonalObject() != null
                && enrollment.getPersonalObject().getStateObject() != null)
                ? enrollment.getPersonalObject().getStateObject() : residenceState;
        personal.setStateObject(state);
        personal.setState((state != null
                && state.getId() != null
                && (stateId == null || stateId.isEmpty()))
                ? state.getId()
                : stateId);

        // local govt of origin
        lga = (enrollment != null
                && enrollment.getPersonalObject() != null
                && enrollment.getPersonalObject().getLgaObject() != null)
                ? enrollment.getPersonalObject().getLgaObject() : lga;
        personal.setLgaObject(lga);
        personal.setLga((lga != null
                && lga.getId() != null
                && (lgaId == null || lgaId.isEmpty()))
                ? lga.getId()
                : lgaId);

        // residence state
        residenceState = (enrollment != null
                && enrollment.getPersonalObject() != null
                && enrollment.getPersonalObject().getLocation() != null
                && enrollment.getPersonalObject().getLocation().getStateObject() != null)
                ? enrollment.getPersonalObject().getLocation().getStateObject() : residenceState;

        // residence local govt
        residenceLGA = (enrollment != null
                && enrollment.getPersonalObject() != null
                && enrollment.getPersonalObject().getLgaObject() != null)
                ? enrollment.getPersonalObject().getLgaObject() : residenceLGA;
        personal.setLgaObject(residenceLGA);

        Location location = (enrollment != null && enrollment.getPersonalObject() != null
                && enrollment.getPersonalObject().getLocation() != null)
                ? enrollment.getPersonalObject().getLocation() : new Location();
        location.setStreet(binding.streetEditText.getText().toString());
        location.setStateCode(residenceStateId);
        location.setLga_code(residenceLgaId);
        location.setLgaObject(residenceLGA);
        location.setStateObject(residenceState);
        location.setCity(binding.cityEditText.getText().toString());
        location.setZip_code(binding.zipCodeEditText.getText().toString());
        location.setPOBox(binding.POBoxEditText.getText().toString());

        personal.setLocation(location);

        String validationMessage = EnrollmentValidator.validatePersonal(personal);
        if (!validationMessage.isEmpty()) {
            showToast(validationMessage);
            return;
        }
        viewModel.getCurrent().setPersonalObject(personal);
        showToast("Personal data saved successfully");
    }

    private void showDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (mView, year, month, dayOfMonth) -> {
            startDate = AppUtil.dateFormatter(AppUtil.customCalendar(year, month, dayOfMonth));
            binding.dob.setText(DateTimeUtils.formatWithPattern(startDate, DATE_FORMAT));
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
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
            case R.id.dob:
                showDate();
                break;
            case R.id.saveBtn:
                setPersonalData();
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
//        binding.dob.setDate(dateSelected);
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
                            DropDownObject dropDownObject = new DropDownObject(country.getId(), country.getName(), country.getCode());
                            dropDownObjectArrayList.add(dropDownObject);
                        }
                        DropDownAdapter dropDownAdapter = new DropDownAdapter(getContext(), dropDownObjectArrayList);
                        dropDownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.countrySpinner.setAdapter(dropDownAdapter);
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
                    binding.residenceStateSpinner.setAdapter(dropDownAdapter);
                    binding.stateInfoSpinner.setAdapter(dropDownAdapter);
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
                    binding.residenceLgaSpinner.setAdapter(dropDownAdapter);
                    binding.lgaInfoSpinner.setAdapter(dropDownAdapter);
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
    public void onItemSelected(MaterialSpinner view, int position, long id, DropDownObject item) {
        switch (view.getId()) {
            case R.id.residenceStateSpinner:
                viewModel.findState(item.get_id(), null).observe(getViewLifecycleOwner(), this::performAction);
                residenceStateId = item.get_id();
                residenceState.setId(item.get_id());
                residenceState.setName(item.getName());
                residenceState.setCode(item.getCode());
                binding.residenceStateSpinner.setText(item.getName());
                break;
            case R.id.stateInfoSpinner:
                viewModel.findState(item.get_id(), null).observe(getViewLifecycleOwner(), this::performAction);
                stateId = item.get_id();
                state.setId(item.get_id());
                state.setName(item.getName());
                state.setCode(item.getCode());
                binding.stateInfoSpinner.setText(item.getName());
                break;
            case R.id.residenceLgaSpinner:
                binding.residenceLgaSpinner.setText(item.getName());
                residenceLgaId = item.get_id();
                residenceLGA.setId(item.get_id());
                residenceLGA.setName(item.getName());
                residenceLGA.setCode(item.getCode());
                break;
            case R.id.lgaInfoSpinner:
                binding.lgaInfoSpinner.setText(item.getName());
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
}