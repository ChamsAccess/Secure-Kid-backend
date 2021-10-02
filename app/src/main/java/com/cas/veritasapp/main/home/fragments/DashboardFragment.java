package com.cas.veritasapp.main.home.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.databinding.FragmentEnrollmentBinding;
import com.cas.veritasapp.main.adapter.EnrollmentAdapter;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.core.data.entities.Enrollment;
import com.cas.veritasapp.objects.Stats;
import com.cas.veritasapp.objects.api.ApiError;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class DashboardFragment extends BaseFragment<FragmentEnrollmentBinding> {

    FragmentEnrollmentBinding binding;

    @Inject
    EnrollmentViewModel viewModel;

    private EnrollmentAdapter enrollmentAdapter;
    private ArrayList<Enrollment> enrollmentList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_enrollment;
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
        initiateApiCall();

        enrollmentAdapter = new EnrollmentAdapter(viewRoot, enrollmentList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        binding.enrollments.setLayoutManager(layoutManager);
        binding.enrollments.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.enrollments.getContext(),
                DividerItemDecoration.HORIZONTAL);
        binding.enrollments.addItemDecoration(dividerItemDecoration);
        binding.enrollments.setAdapter(enrollmentAdapter);

        binding.pullToRefresh.setOnRefreshListener(() -> {
            binding.pullToRefresh.setRefreshing(true);
            initiateApiCall();
        });
    }

    private void initiateApiCall(){
        viewModel.getEnrollments(null).observe(getViewLifecycleOwner(), this::performAction);
        viewModel.stats(null).observe(getViewLifecycleOwner(), this::performAction);
    }

    @Override
    public void onLoad(String key) {
        super.onLoad(key);
    }

    @SuppressLint({"WrongConstant", "SetTextI18n"})
    @Override
    public void onSuccess(Object obj, String key) {
        super.onSuccess(obj, key);
        if (obj != null) {
            binding.pullToRefresh.setRefreshing(false);
            if (obj instanceof List && key.equals(AppConstant.FIND_ENROLLMENT)) {
                enrollmentList = (ArrayList<Enrollment>) obj;
                if (!enrollmentList.isEmpty()) {
                    enrollmentAdapter.submitList(enrollmentList);
                }
            }
            if (key.equals(AppConstant.ENROLLMENT_STATS)) {
                Stats stats = (Stats) obj;
                if (stats != null) {
                    binding.totalPendingTextView.setText(Integer.toString(stats.getTotalPending()));
                    binding.totalCompletedTextView.setText(Integer.toString(stats.getTotalCompleted()));
                }
            }
        }
    }

    @Override
    public void onError(ApiError apiError, String key) {
        super.onError(apiError, key);
    }
}
