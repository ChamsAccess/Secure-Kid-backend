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
import com.cas.veritasapp.core.data.entities.Enrollment;
import com.cas.veritasapp.databinding.FragmentNextOfKinBinding;
import com.cas.veritasapp.main.adapter.DropDownAdapter;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentValidator;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.core.data.entities.Country;
import com.cas.veritasapp.objects.DropDownObject;
import com.cas.veritasapp.core.data.entities.LGA;
import com.cas.veritasapp.core.data.entities.Location;
import com.cas.veritasapp.core.data.entities.NextOfKin;
import com.cas.veritasapp.core.data.entities.State;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class NextOfKinFragment extends BaseFragment<FragmentNextOfKinBinding> implements
        View.OnClickListener, MaterialSpinner.OnItemSelectedListener<DropDownObject> {

    FragmentNextOfKinBinding binding;

    @Inject
    EnrollmentViewModel viewModel;

    private NextOfKin nextOfKin;

    private ArrayList<DropDownObject> dropDownObjectArrayList;

    private String countryId;
    private String stateId;
    private String lgaId;

    private Country country;
    private State state;
    private LGA lga;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_next_of_kin;
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
        try {
            nextOfKin = (viewModel.getCurrent() != null && viewModel.getCurrent().getNextOfKinObject() != null)
                    ? viewModel.getCurrent().getNextOfKinObject() : new NextOfKin();

            country = ((
                    viewModel.getCurrent() != null &&
                            viewModel.getCurrent().getNextOfKinObject() != null &&
                            viewModel.getCurrent().getNextOfKinObject().getCountryObject() != null
            )) ? viewModel.getCurrent().getNextOfKinObject().getCountryObject() : new Country();

            lga = ((
                    viewModel.getCurrent() != null &&
                            viewModel.getCurrent().getNextOfKinObject() != null &&
                            viewModel.getCurrent().getNextOfKinObject().getLocation() != null &&
                            viewModel.getCurrent().getNextOfKinObject().getLocation().getLgaObject() != null
            )) ? viewModel.getCurrent().getNextOfKinObject().getLocation().getLgaObject() : new LGA();

            state = ((
                    viewModel.getCurrent() != null &&
                            viewModel.getCurrent().getNextOfKinObject() != null &&
                            viewModel.getCurrent().getNextOfKinObject().getLocation() != null &&
                            viewModel.getCurrent().getNextOfKinObject().getLocation().getStateObject() != null
            )) ? viewModel.getCurrent().getNextOfKinObject().getLocation().getStateObject() : new State();

            binding.saveBtn.setOnClickListener(this);

            viewModel.findCountries(null).observe(getViewLifecycleOwner(), this::performAction);

            binding.countrySpinner.setOnItemSelectedListener(this);
            binding.stateSpinner.setOnItemSelectedListener(this);
            binding.lgaSpinner.setOnItemSelectedListener(this);

            binding.titleRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                int titleRadioGroup = group.getCheckedRadioButtonId();
                RadioButton radioButton = requireActivity().findViewById(titleRadioGroup);
                if (radioButton != null) {
                    nextOfKin.setTitle(radioButton.getText().toString());
                }
            });

            binding.genderRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                int titleRadioGroup = group.getCheckedRadioButtonId();
                RadioButton radioButton = requireActivity().findViewById(titleRadioGroup);
                nextOfKin.setGender(radioButton.getText().toString());
            });
        }catch(NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void setNextOfKinData() {
        Enrollment enrollment = viewModel.getCurrent();
        nextOfKin.setFirst_name(binding.firstNameEditText.getText().toString());
        nextOfKin.setSurname(binding.surnameEditText.getText().toString());
        nextOfKin.setEmail(binding.emailEditText.getText().toString());
        nextOfKin.setPhone_number(binding.phoneNumberEditText.getText().toString());
        nextOfKin.setNationality(countryId);
        nextOfKin.setCountry_code("NG");
        nextOfKin.setHouse_number(binding.houseNumberEditText.getText().toString());
        nextOfKin.setRelationship(binding.relationshipEditText.getText().toString());
        nextOfKin.setMiddle_name(binding.middleNameEditText.getText().toString());

        country = (enrollment != null
                && enrollment.getNextOfKinObject() != null
                && enrollment.getNextOfKinObject().getCountryObject() != null)
                ? enrollment.getNextOfKinObject().getCountryObject() : country;

        nextOfKin.setCountryObject(country);
        nextOfKin.setCountry_code((country != null
                && country.getId() != null
                && (country == null || countryId.isEmpty()))
                ? country.getId()
                : countryId);


        Location location = (enrollment != null && enrollment.getNextOfKinObject() != null
                && enrollment.getNextOfKinObject().getLocation() != null)
                ? enrollment.getNextOfKinObject().getLocation() : new Location();

        state = (enrollment != null
                && enrollment.getNextOfKinObject() != null
                && enrollment.getNextOfKinObject().getLocation() != null
                && enrollment.getNextOfKinObject().getLocation().getLgaObject() != null)
                ? enrollment.getNextOfKinObject().getLocation().getStateObject() : state;
        location.setStateObject(state);
        location.setStateCode((state != null
                && state.getId() != null
                && (stateId == null || stateId.isEmpty()))
                ? state.getId()
                : stateId);

        lga = (enrollment != null
                && enrollment.getNextOfKinObject() != null
                && enrollment.getNextOfKinObject().getLocation() != null
                && enrollment.getNextOfKinObject().getLocation().getLgaObject() != null)
                ? enrollment.getNextOfKinObject().getLocation().getLgaObject() : lga;
        location.setLgaObject(lga);
        location.setLga_code((lga != null
                && lga.getId() != null
                && (lgaId == null || lgaId.isEmpty()))
                ? lga.getId()
                : lgaId);

        location.setPOBox(binding.POBoxEditText.getText().toString());
        location.setZip_code(binding.zipCodeEditText.getText().toString());
        location.setStreet(binding.streetNameEditText.getText().toString());
        location.setCity(binding.streetNameEditText.getText().toString());
        nextOfKin.setLocation(location);

        String validationMessage = EnrollmentValidator.validateNextOfKin(nextOfKin);
        if (!validationMessage.isEmpty()) {
            showToast(validationMessage);
            return;
        }

        viewModel.getCurrent().setNextOfKinObject(nextOfKin);
        showToast("Next of Kin data saved successfully");
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


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveBtn:
                setNextOfKinData();
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
                state.setId(item.get_id());
                state.setCode(item.getCode());
                state.setName(item.getName());
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
}
