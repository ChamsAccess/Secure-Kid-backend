package com.cas.veritasapp.core.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.listeners.DataCallback;
import com.cas.veritasapp.core.listeners.OnBackPressListener;
import com.cas.veritasapp.objects.AuthStaff;
import com.cas.veritasapp.objects.NavData;
import com.cas.veritasapp.util.LogUtil;
import com.cas.veritasapp.util.PrefUtil;

/**
 * Created by funmiayinde on 2019-10-10.
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    public T viewDataBinding;
    protected Context context;
    protected Bundle bundle;
    protected AuthStaff authStaff;

    public abstract
    @LayoutRes
    int getLayoutId();

    public int getNavHost() {
        return -1;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        context = this;
        bundle = getIntent().getExtras();
        if (bundle == null) {
            bundle = new Bundle();
        }
        authStaff = (AuthStaff) PrefUtil.getObjectData(context, AuthStaff.class);
        bundle.putSerializable(AppConstant.AUTH_STAFF, authStaff);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public T getViewDataBinding() {
        return viewDataBinding;
    }

    @Override
    public void onBackPressed() {
        try {
            final Fragment navHost = getSupportFragmentManager().getFragments().get(0);
            final Fragment currentFragment = navHost.getChildFragmentManager().getFragments().get(0);
            final NavController navController = Navigation.findNavController(this, getNavHost());
            if (currentFragment instanceof OnBackPressListener) {
                NavData sourceObj = ((OnBackPressListener) currentFragment).onBackPressed();
                LogUtil.info("source obj: ", sourceObj.toString());
                if (sourceObj.getNavigateId() > 0) {
                    navController.navigate(sourceObj.getNavigateId(), sourceObj.getBundle());
                } else {
                    boolean showNav = sourceObj.getBundle().getBoolean(AppConstant.SHOW_BACK_BUTTON);
                    if (showNav) {
                        finish();
                    } else {
                        super.onBackPressed();
                    }
                }
            } else if (!navController.popBackStack()) {
                super.onBackPressed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
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

}
