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
import com.cas.veritasapp.databinding.FragmentNewEnrollmentBinding;
import com.cas.veritasapp.databinding.FragmentNextOfKinBinding;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.objects.Location;
import com.cas.veritasapp.objects.NextOfKin;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class NextOfKinFragment extends BaseFragment<FragmentNextOfKinBinding> implements
        View.OnClickListener, MaterialSpinner.OnItemSelectedListener<String> {

    FragmentNextOfKinBinding binding;

    @Inject
    EnrollmentViewModel viewModel;

    private NextOfKin nextOfKin;

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

    private void initApp(){
        nextOfKin = new NextOfKin();

        binding.saveBtn.setOnClickListener(this);

        binding.countrySpinner.setItems(AppConstant.COUNTRIES);
        binding.stateSpinner.setItems(AppConstant.STATES);

        binding.titleRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int titleRadioGroup = group.getCheckedRadioButtonId();
            RadioButton radioButton = requireActivity().findViewById(titleRadioGroup);
            nextOfKin.setTitle(radioButton.getText().toString());
        });
    }

    private void setNextOfKinData() {
        nextOfKin.setFirst_name(binding.firstNameEditText.getText().toString());
        nextOfKin.setSurname(binding.surnameEditText.getText().toString());
        nextOfKin.setEmail(binding.emailEditText.getText().toString());
        nextOfKin.setPhone_number(binding.phoneNumberEditText.getText().toString());
        nextOfKin.setNationality(binding.countrySpinner.getText().toString());
        nextOfKin.setCountry_code("NG");
        nextOfKin.setHouse_number(binding.houseNumberEditText.getText().toString());
        nextOfKin.setRelationship(binding.relationshipEditText.getText().toString());
        nextOfKin.setMiddle_name(binding.middleNameEditText.getText().toString());

        Location location = new Location();
        location.setStreet(binding.streetNameEditText.getText().toString());
        location.setLga_code(binding.lgaCodeEditText.getText().toString());
        location.setPOBox(binding.lgaCodeEditText.getText().toString());
        location.setStateCode(binding.stateSpinner.getText().toString());
        location.setPOBox(binding.POBoxEditText.getText().toString());
        location.setZip_code(binding.zipCodeEditText.getText().toString());
        nextOfKin.setLocation(location);

        viewModel.getCurrent().setNextOfKinObject(nextOfKin);
    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveBtn:
                setNextOfKinData();
                showToast("Next of Kin data saved successfully");
                break;
        }
    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
        switch (view.getId()){
            case R.id.countrySpinner:
                binding.countrySpinner.setText(item);
                break;
            case R.id.stateSpinner:
                binding.stateSpinner.setText(item);
                break;
        }
    }
}
