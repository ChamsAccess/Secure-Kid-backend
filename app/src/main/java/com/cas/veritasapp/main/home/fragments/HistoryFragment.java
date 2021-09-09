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
import com.cas.veritasapp.core.listeners.OnItemSelectedListener;
import com.cas.veritasapp.databinding.FragmentHistoryBinding;
import com.cas.veritasapp.main.adapter.HistoryEnrollmentAdapter;
import com.cas.veritasapp.main.home.dialog.FilterFragmentDialog;
import com.cas.veritasapp.main.home.dialog.PreviewFragmentDialog;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.objects.Enrollment;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.util.FixedGridLayoutManager;

import org.jetbrains.annotations.NotNull;

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
    public void onAttach(@NonNull @NotNull Context context) {
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

        FixedGridLayoutManager manager = new FixedGridLayoutManager();
        manager.setTotalColumnCount(1);
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL));

        binding.filterButton.setOnClickListener(this);
    }

    private void fetchEnrollment(Map<String, String> map) {
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
                if (!enrollmentList.isEmpty()) {
                    adapter.submitList(enrollmentList);
                }
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
            showToast(enrollment.get_id());
            PreviewFragmentDialog dialog = new PreviewFragmentDialog(enrollment);
            dialog.show(requireActivity().getSupportFragmentManager(), "Preview Data");
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
