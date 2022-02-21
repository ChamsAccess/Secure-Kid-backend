package com.cas.veritasapp.main.home.dialog;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseDialogFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.data.entities.Enrollment;
import com.cas.veritasapp.databinding.FragmentEnrollmentErrorBinding;
import com.cas.veritasapp.main.adapter.EnrollmentErrorAdapter;
import com.cas.veritasapp.main.adapter.HistoryEnrollmentAdapter;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.objects.payloads.EnrollmentErrorPayload;
import com.cas.veritasapp.util.FixedGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ErrorFragmentDialog extends BaseDialogFragment<FragmentEnrollmentErrorBinding>
        implements View.OnClickListener {

    @Inject
    EnrollmentViewModel viewModel;

    FragmentEnrollmentErrorBinding binding;
    private final Enrollment enrollment;

    private ArrayList<String> errorList = new ArrayList<>();

    private EnrollmentErrorAdapter adapter;

    public ErrorFragmentDialog(Enrollment enrollment) {
        super();
        this.enrollment = enrollment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.setCancelable(false);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_enrollment_error;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = getViewDataBinding();
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
        binding.setLifecycleOwner(this);
        this.initialize();
    }

    @SuppressLint("SetTextI18n")
    private void initialize() {
        binding.close.setOnClickListener(this);
        adapter = new EnrollmentErrorAdapter(viewRoot, errorList);
        adapter.setHasStableIds(true);


        FixedGridLayoutManager manager = new FixedGridLayoutManager();
        manager.setTotalColumnCount(1);
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL));

        if (enrollment != null) {
//            showToast("Fetching...-status for: "+ enrollment.getPersonalObject().getFirstName());
            viewModel.getEnrollmentError(enrollment.get_id(), null).observe(getViewLifecycleOwner(), this::performAction);
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                dismiss();
                break;
        }
    }

    @Override
    public void onLoad(String key) {
        super.onLoad(key);
    }

    @Override
    public void onSuccess(Object obj, String key) {
        super.onSuccess(obj, key);
        hideProgressDialog();
        if (AppConstant.GET_ENROLLMENT_ERROR.equals(key)) {
            if (obj != null) {
                EnrollmentErrorPayload payload = (EnrollmentErrorPayload) obj;
                errorList = payload.issues;
                adapter.submitList(errorList);
            }
        }
    }

    @Override
    public void onError(ApiError apiError, String key) {
        super.onError(apiError, key);
        if (AppConstant.GET_ENROLLMENT_ERROR.equals(key)) {
            showToast("An error occurred while processing enrollment");
        }
        hideProgressDialog();
        showToast(apiError.getMessage());
    }
}
