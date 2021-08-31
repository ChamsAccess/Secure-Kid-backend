package com.cas.veritasapp.core.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.helpers.FragmentToolbarHelper;
import com.cas.veritasapp.core.helpers.ToolbarHelper;
import com.cas.veritasapp.core.listeners.NetworkRequestListener;
import com.cas.veritasapp.core.listeners.OnBackPressListener;
import com.cas.veritasapp.core.network.Resource;
import com.cas.veritasapp.objects.AuthStaff;
import com.cas.veritasapp.objects.NavData;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.util.ProgressUtil;


/**
 * Created by funmiayinde on 2019-10-11.
 */
public abstract class BaseDialogFragment<T extends ViewDataBinding> extends DialogFragment implements
        NetworkRequestListener {

    protected View viewRoot;
    protected ToolbarHelper toolbarHelper;
    protected Bundle bundle;
    protected AuthStaff authStaff;
    protected Context context;

    private Toolbar toolbar;
    private ProgressUtil progressUtil;

    public T viewDataBinding;

    public abstract
    @LayoutRes
    int getLayoutId();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        viewRoot = viewDataBinding.getRoot();
        return viewRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDependencies(view);
    }

    public void initDependencies(View view) {
        bundle = getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        toolbarHelper = new ToolbarHelper(build(), view);
        toolbarHelper.buildToolbar();
        progressUtil = new ProgressUtil();
    }

    public T getViewDataBinding(){
        return viewDataBinding;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (bundle == null) {
            bundle = new Bundle();
        }
    }

    public void showProgressDialog() {
        progressUtil.displayProgress(context);
    }

    public void hideProgressDialog(){
        progressUtil.closeProgress();
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void performAction(Resource resource){
        switch (resource.status){
            case LOADING:
                onLoad(resource.key);
                break;
            case SUCCESS:
                onSuccess(resource.data, resource.key);
                break;
            case ERROR:
                onError(resource.error, resource.key);
                break;
        }
    }

    protected FragmentToolbarHelper build() {
        return new FragmentToolbarHelper.Builder().build();
    }

    @Override
    public void onLoad(String key) {
        if (context != null){
            progressUtil.displayProgress(context);
        }
    }

    @Override
    public void onSuccess(Object obj, String key) {
        if (progressUtil.getDialog() != null && progressUtil.getDialog().isShowing()){
            progressUtil.closeProgress();
        }
    }

    @Override
    public void onError(ApiError apiError, String key) {
        if (progressUtil.getDialog() != null && progressUtil.getDialog().isShowing()){
            progressUtil.closeProgress();
        }
    }

    public Toolbar getToolbar(){
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar, boolean isBackButtonEnabled){
        this.toolbar = toolbar;
        if (isBackButtonEnabled) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
                this.toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
            }
        }
    }
}
