package com.cas.veritasapp.main.home.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.data.entities.User;
import com.cas.veritasapp.core.listeners.OnItemSelectedListener;
import com.cas.veritasapp.databinding.FragmentHistoryBinding;
import com.cas.veritasapp.main.adapter.HistoryEnrollmentAdapter;
import com.cas.veritasapp.main.home.dialog.ErrorFragmentDialog;
import com.cas.veritasapp.main.home.dialog.FilterFragmentDialog;
import com.cas.veritasapp.main.home.dialog.PreviewFragmentDialog;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.core.data.entities.Enrollment;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.util.FixedGridLayoutManager;
import com.cas.veritasapp.util.LogUtil;
import com.cas.veritasapp.util.PrefUtil;
import com.cas.veritasapp.util.ServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class HistoryFragment extends BaseFragment<FragmentHistoryBinding>
        implements View.OnClickListener, OnItemSelectedListener {

    FragmentHistoryBinding binding;
    FilterFragmentDialog filterFragmentDialog;
    @Inject
    EnrollmentViewModel viewModel;

    private ArrayList<Enrollment> enrollmentList = new ArrayList<>();

    private HistoryEnrollmentAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = getViewDataBinding();
        binding.executePendingBindings();
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        this.initApp();
        fetchEnrollment(new HashMap<>());
    }

    private void initApp() {
        filterFragmentDialog = new FilterFragmentDialog(this);
        adapter = new HistoryEnrollmentAdapter(viewRoot, enrollmentList, this);
        adapter.setHasStableIds(true);

        binding.filterButton.setOnClickListener(this);

        FixedGridLayoutManager manager = new FixedGridLayoutManager();
        manager.setTotalColumnCount(1);
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL));

    }

    private void fetchEnrollment(Map<String, String> map) {
        String userId = PrefUtil.getStringData(requireActivity(), AppConstant.USER_ID);
        map.put("enrolled_by", userId);
        viewModel.getEnrollments(map).observe(getViewLifecycleOwner(), this::performAction);
    }

    @Override
    public void onLoad(String key) {
        super.onLoad(key);
    }

    @Override
    public void onSuccess(Object obj, String key) {
        super.onSuccess(obj, key);
        if (obj != null) {
            if (obj instanceof List) {
                enrollmentList = (ArrayList<Enrollment>) obj;
                adapter.submitList(enrollmentList);
            }
        }
    }

    @Override
    public void onError(ApiError apiError, String key) {
        super.onError(apiError, key);
    }

    @Override
    public void ontItemSelected(Object obj, String key) {
        if (key.equals(AppConstant.FILTER_ENROLLMENT) && obj != null) {
            fetchEnrollment((Map) obj);
        }
        if (key.equals(AppConstant.ENROLLMENT) && obj != null) {
            Enrollment enrollment = (Enrollment) obj;
            PreviewFragmentDialog dialog = new PreviewFragmentDialog(enrollment, AppConstant.SHOW_ENROLLMENT_DETAILS);
            dialog.show(requireActivity().getSupportFragmentManager(), "Preview Data");
        }
        if (key.equals(AppConstant.ENROLLMENT_ERRORS)) {
            Enrollment enrollment = (Enrollment) obj;
            ErrorFragmentDialog dialog = new ErrorFragmentDialog(enrollment);
            dialog.show(requireActivity().getSupportFragmentManager(), "Enrollment Errors");
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.filterButton:
                filterFragmentDialog.setArguments(bundle);
                filterFragmentDialog.show(getFragmentManager(), "FilterDialog");
                break;
        }
    }
}
