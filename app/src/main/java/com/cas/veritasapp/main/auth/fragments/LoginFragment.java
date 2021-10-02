package com.cas.veritasapp.main.auth.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.core.data.entities.User;
import com.cas.veritasapp.databinding.FragmentLoginBinding;
import com.cas.veritasapp.main.auth.AuthActivity;
import com.cas.veritasapp.main.auth.rvvm.AuthModel;
import com.cas.veritasapp.main.auth.rvvm.AuthValidator;
import com.cas.veritasapp.main.auth.rvvm.AuthViewModel;
import com.cas.veritasapp.main.home.HomeActivity;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.util.AppUtil;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


/**
 * Created by funmiayinde on 2019-10-14.
 */
public class LoginFragment extends BaseFragment<FragmentLoginBinding> implements View.OnClickListener {

    @Inject
    AuthViewModel authViewModel;

    FragmentLoginBinding binding;
    AuthValidator validator;
    public User user;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
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
        binding.setViewModel(authViewModel);
        binding.executePendingBindings();
        binding.setLifecycleOwner(this);
        this.initialize();
    }

    private void initialize() {
        validator = new AuthValidator(authViewModel);
        binding.loginButton.setOnClickListener(this);
    }

    public void loginAction() {
        String email = binding.email.getText().toString().trim();
        String password = binding.password.getText().toString();
        AuthModel authModel = new AuthModel(email, password);
        if (authViewModel.validate(authModel)) {
            binding.loginButton.setEnabled(true);
            authViewModel.login(email, password).observe(this, this::performAction);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                loginAction();
                break;
        }
    }

    @Override
    public void onSuccess(Object obj, String key) {
        super.onSuccess(obj, key);
        binding.loginButton.setEnabled(true);
        User user = (User) obj;
        if (user != null){
            launchActivity(getActivity(), HomeActivity.class);
        }else {
            authViewModel.setError("Your email is not authorized, please contact admin");
        }
    }

    @Override
    public void onError(ApiError apiError, String key) {
        super.onError(apiError, key);
        binding.loginButton.setEnabled(true);
        authViewModel.setError(apiError.getMessage());
    }
}
