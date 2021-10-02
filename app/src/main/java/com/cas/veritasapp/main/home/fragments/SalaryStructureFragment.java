package com.cas.veritasapp.main.home.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.databinding.FragmentSalaryStructureBinding;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.core.data.entities.ConsolidatedSalary;
import com.cas.veritasapp.objects.CurrentSalary;
import com.cas.veritasapp.core.data.entities.EnhConsolidatedSalary2010;
import com.cas.veritasapp.core.data.entities.EnhConsolidatedSalary2013;
import com.cas.veritasapp.core.data.entities.EnhConsolidatedSalary2016;
import com.cas.veritasapp.core.data.entities.HarmonizedSalary;
import com.cas.veritasapp.core.data.entities.Salary;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class SalaryStructureFragment extends BaseFragment<FragmentSalaryStructureBinding> {

    FragmentSalaryStructureBinding binding;

    @Inject
    EnrollmentViewModel viewModel;

    private Salary salary;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_salary_structure;
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
        salary = (viewModel.getCurrent() != null && viewModel.getCurrent().getSalaryObject() != null)
            ? viewModel.getCurrent().getSalaryObject() : new Salary();
        binding.saveBtn.setOnClickListener(v -> {
            setSalaryData();
            showToast("Salary data saved successfully");
        });
    }

    private void setSalaryData() {
        HarmonizedSalary harmonized_salary_2004 = new HarmonizedSalary();
        ConsolidatedSalary consolidated_salary_2010 = new ConsolidatedSalary();
        EnhConsolidatedSalary2010 enh_consolidated_salary_2010 = new EnhConsolidatedSalary2010();
        EnhConsolidatedSalary2013 enh_consolidated_salary_2013 = new EnhConsolidatedSalary2013();
        EnhConsolidatedSalary2016 enh_consolidated_salary_2016 = new EnhConsolidatedSalary2016();
        CurrentSalary current_salary = new CurrentSalary();

        harmonized_salary_2004.setSalary_structure(binding.harmonizedSalaryStructure2004.getText().toString());
        harmonized_salary_2004.setCls_at_june_2004(binding.cLJune2004.getText().toString());
        harmonized_salary_2004.setStep_at_june_2004(binding.stepJune2004.getText().toString());

        consolidated_salary_2010.setSalary_structure(binding.consolidatedSalaryStructureJune2010.getText().toString());
        consolidated_salary_2010.setCls_at_june_2007(binding.cLJune2007.getText().toString());
        consolidated_salary_2010.setStep_at_june_2000(binding.stepJune2000.getText().toString());

        enh_consolidated_salary_2010.setSalary_structure(binding.eNHConsolidatedSalaryStructure2010.getText().toString());
        enh_consolidated_salary_2010.setStep_at_2010(binding.eNHStepJune2010.getText().toString());
        enh_consolidated_salary_2010.setCls_at_2013(binding.eNHCL2013.getText().toString());

        enh_consolidated_salary_2013.setSalary_structure(binding.eNHConsolidatedSalaryStructure2013.getText().toString());
        enh_consolidated_salary_2013.setStep_at_2013(binding.eNCStep2013.getText().toString());
        enh_consolidated_salary_2013.setCls_at_2013(binding.enhCL2013.getText().toString());

        enh_consolidated_salary_2016.setSalary_structure(binding.eNHConsolidatedSalaryStructure2016.getText().toString());
        enh_consolidated_salary_2016.setCls_at_2016(binding.enhCL2016.getText().toString());
        enh_consolidated_salary_2016.setStep_at_2016(binding.eNC2Step2016.getText().toString());

        current_salary.setSalary_structure(binding.currentSalaryStructure.getText().toString());
        current_salary.setCurrent_gl(binding.currentGl.getText().toString());
        current_salary.setCurrent_step(binding.currentStep.getText().toString());

        salary.setHarmonized_salary_2004(harmonized_salary_2004);
        salary.setConsolidated_salary_2010(consolidated_salary_2010);
        salary.setEnh_consolidated_salary_2013(enh_consolidated_salary_2013);
        salary.setEnh_consolidated_salary_2016(enh_consolidated_salary_2016);
        salary.setCurrent_salary(current_salary);

        viewModel.getCurrent().setSalaryObject(salary);
    }
}

