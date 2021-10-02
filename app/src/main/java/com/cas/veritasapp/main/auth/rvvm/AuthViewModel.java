package com.cas.veritasapp.main.auth.rvvm;

import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.cas.veritasapp.BR;
import com.cas.veritasapp.core.base.BaseViewModel;
import com.cas.veritasapp.core.data.entities.Staff;
import com.cas.veritasapp.core.data.entities.User;
import com.cas.veritasapp.core.network.Resource;

import javax.inject.Inject;

/**
 * Created by funmiayinde on 2019-10-14.
 */
public class AuthViewModel extends BaseViewModel {

    private AuthRepository repository;
    private AuthModel authModel;

    public MutableLiveData<AuthValidation> validation = new MutableLiveData<>();
    public AuthValidation authValidation = new AuthValidation();

    public String error = null;

    @Inject
    public AuthViewModel(AuthRepository repository) {
        this.repository = repository;
        this.authModel = new AuthModel("name@example.com", "password!!!");
        this.validation.setValue(authValidation);
    }

    @Bindable
    public AuthModel getAuthModel() {
        return authModel;
    }

    public void setUserAuth(AuthModel authModel) {
        this.authModel = authModel;
        notifyPropertyChanged(BR.viewModel);
    }

    @Bindable
    public String getError() {
        return error;
    }

    public LiveData<Resource<User>> login(String email, String password) {
        return this.repository.login(email, password);
    }

    public void setError(String error) {
        this.error = error;
        notifyPropertyChanged(BR.error);
    }

    public boolean validate(AuthModel authModel) {
        setError(null);
        authValidation = new AuthValidation();
        boolean isValidated = authValidation.isValidated(authModel);
        if (isValidated) {
            return true;
        } else {
            validation.setValue(authValidation);
            return false;
        }
    }

    public MutableLiveData<AuthValidation> getValidation() {
        return validation;
    }

    public void setValidation(MutableLiveData<AuthValidation> validation) {
        this.validation = validation;
    }

    public AuthValidation getAuthValidation() {
        return authValidation;
    }

    public void setAuthValidation(AuthValidation authValidation) {
        this.authValidation = authValidation;
    }
}
