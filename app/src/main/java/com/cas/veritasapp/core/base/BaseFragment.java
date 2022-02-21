package com.cas.veritasapp.core.base;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.helpers.FragmentToolbarHelper;
import com.cas.veritasapp.core.helpers.ToolbarHelper;
import com.cas.veritasapp.core.listeners.NetworkRequestListener;
import com.cas.veritasapp.core.listeners.OnBackPressListener;
import com.cas.veritasapp.core.network.Resource;
import com.cas.veritasapp.main.PictureActivity;
import com.cas.veritasapp.objects.AuthStaff;
import com.cas.veritasapp.objects.NavData;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.util.ProgressUtil;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by funmiayinde on 2019-10-11.
 */
public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment implements
        NetworkRequestListener, OnBackPressListener {

    protected View viewRoot;
    protected ToolbarHelper toolbarHelper;
    protected Bundle bundle;
    protected AuthStaff authStaff;
    protected Context context;
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 3;
    public static final int REQUEST_IMAGE_CAPTURE = 2;
//    public static final String DATE_FORMAT = "YYYY-mm-dd";
    public static final String DATE_FORMAT = "dd-MM-yyyy";

    private Toolbar toolbar;
    private int sourceNavId;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDependencies(view);
        checkPermission();
    }

    public void initDependencies(View view) {
        bundle = getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        setSourceNavId();
        toolbarHelper = new ToolbarHelper(build(), view);
        toolbarHelper.buildToolbar();
        progressUtil = new ProgressUtil();
    }

    public T getViewDataBinding(){
        return viewDataBinding;
    }

    public void setSourceNavId() {
        sourceNavId = bundle.getInt(AppConstant.SOURCE_NAV_ID);
        bundle.remove(AppConstant.SOURCE_NAV_ID);
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

    public void startPictureActivity() {
        Intent intent = new Intent();
        intent.setClass(requireActivity(), PictureActivity.class);
        Date dt = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String realFileName = "passport_" + year + "_" + month + "_" + day + "_"
                + dt.getHours() + "_" + dt.getMinutes() + "_" + dt.getSeconds();
        intent.putExtra("pictureName", realFileName);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkPermission(){
        if (ActivityCompat.checkSelfPermission(requireActivity(),
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireActivity(),
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            requireActivity().requestPermissions(
                    new String[]
                            {
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.CAMERA,
                            },
                    REQUEST_ID_MULTIPLE_PERMISSIONS);

        } else {
            Log.e("DB", "PERMISSION GRANTED");
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

    @Override
    public NavData onBackPressed() {
        if (sourceNavId > 0) {
            return new NavData(sourceNavId, bundle);
        }
        return new NavData(getSourceNavigationId(),bundle);
    }

    public
    @IdRes
    int getSourceNavigationId() {
        return -1;
    }

    public void launchActivity(Context context, Class<?> clazz, Bundle bundle, boolean isNewTask) {
        Intent intent = new Intent(context, clazz);
        if (bundle != null)
            intent.putExtras(bundle);
        if (isNewTask)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    public void launchActivity(Context context, Class<?> clazz, boolean isNewTask) {
        Intent intent = new Intent(context, clazz);
        if (isNewTask)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void launchActivity(Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
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
