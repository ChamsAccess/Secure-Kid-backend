package com.cas.veritasapp.main.home.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.listeners.OnItemSelectedListener;
import com.cas.veritasapp.databinding.FragmentHistoryBinding;
import com.cas.veritasapp.databinding.FragmentNewEnrollmentBinding;
import com.cas.veritasapp.main.home.dialog.FilterFragmentDialog;
import com.cas.veritasapp.objects.api.ApiError;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import dagger.android.support.AndroidSupportInjection;

public class HistoryFragment extends BaseFragment<FragmentHistoryBinding>
        implements View.OnClickListener, OnItemSelectedListener<Map> {

    FragmentHistoryBinding binding;
    FilterFragmentDialog filterFragmentDialog;

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
        binding.setLifecycleOwner(this);
        this.initApp();
    }

    private void initApp(){
        filterFragmentDialog = new FilterFragmentDialog(this);
        binding.filterButton.setOnClickListener(this);
    }

    @Override
    public void onLoad(String key) {
        super.onLoad(key);
    }

    @Override
    public void onSuccess(Object obj, String key) {
        super.onSuccess(obj, key);
    }

    @Override
    public void onError(ApiError apiError, String key) {
        super.onError(apiError, key);
    }

    @Override
    public void ontItemSelected(Map item, String key) {
        if (key.equals(AppConstant.FILTER_ENROLLMENT) && item != null){
            showToast("filter" + item.toString());
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.filterButton:
                filterFragmentDialog.setArguments(bundle);
                filterFragmentDialog.show(getFragmentManager(), "FilterDialog");
                break;
        }
    }
}
