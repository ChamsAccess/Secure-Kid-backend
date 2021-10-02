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
import com.cas.veritasapp.databinding.FragmentNextOfKinBinding;
import com.cas.veritasapp.main.adapter.DropDownAdapter;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.core.data.entities.Country;
import com.cas.veritasapp.objects.DropDownObject;
import com.cas.veritasapp.core.data.entities.LGA;
import com.cas.veritasapp.core.data.entities.Location;
import com.cas.veritasapp.core.data.entities.NextOfKin;
import com.cas.veritasapp.core.data.entities.State;
import com.cas.veritasapp.util.AppUtil;
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
        nextOfKin = (viewModel.getCurrent() != null && viewModel.getCurrent().getNextOfKinObject() != null)
                ? viewModel.getCurrent().getNextOfKinObject() : new NextOfKin();

        binding.saveBtn.setOnClickListener(this);

        viewModel.findCountries(null).observe(getViewLifecycleOwner(), this::performAction);

        binding.countrySpinner.setOnItemSelectedListener(this);
        binding.stateSpinner.setOnItemSelectedListener(this);
        binding.lgaSpinner.setOnItemSelectedListener(this);

        binding.titleRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int titleRadioGroup = group.getCheckedRadioButtonId();
            RadioButton radioButton = requireActivity().findViewById(titleRadioGroup);
            nextOfKin.setTitle(radioButton.getText().toString());
        });

        binding.genderRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int titleRadioGroup = group.getCheckedRadioButtonId();
            RadioButton radioButton = requireActivity().findViewById(titleRadioGroup);
            nextOfKin.setGender(radioButton.getText().toString());
        });
    }

    private void setNextOfKinData() {
        nextOfKin.setFirst_name(binding.firstNameEditText.getText().toString());
        nextOfKin.setSurname(binding.surnameEditText.getText().toString());
        nextOfKin.setEmail(binding.emailEditText.getText().toString());
        nextOfKin.setPhone_number(binding.phoneNumberEditText.getText().toString());
        nextOfKin.setNationality(countryId);
        nextOfKin.setCountry_code("NG");
        nextOfKin.setHouse_number(binding.houseNumberEditText.getText().toString());
        nextOfKin.setRelationship(binding.relationshipEditText.getText().toString());
        nextOfKin.setMiddle_name(binding.middleNameEditText.getText().toString());


        Location location = new Location();
        location.setStreet(binding.streetNameEditText.getText().toString());
        location.setLga_code(lgaId);
        location.setPOBox("");
        location.setStateCode(stateId);
        location.setPOBox(binding.POBoxEditText.getText().toString());
        location.setZip_code(binding.zipCodeEditText.getText().toString());
        nextOfKin.setLocation(location);

        viewModel.getCurrent().setNextOfKinObject(nextOfKin);
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
                showToast("Next of Kin data saved successfully");
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
