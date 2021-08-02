package com.cas.veritasapp.main.auth.rvvm;

import android.text.TextUtils;

import androidx.annotation.NonNull;

/**
 * Created by funmiayinde on 2019-10-14.
 */
public class AuthValidation {
    private String error;
    private String emailError;
    private String passwordError;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public boolean isValidated(@NonNull AuthModel authModel){
        return validatePassword(authModel.getPassword()) &&
                validateEmail(authModel.getEmail());
    }
    public boolean validateEmail(String staffId) {
        boolean validated;
        if (TextUtils.isEmpty(staffId)) {
            setEmailError("Email cannot be empty");
            validated = false;
        } else {
            setEmailError("");
            validated = true;
        }
        return validated;
    }

    public boolean validatePassword(String pin) {
        boolean validated;
        if (TextUtils.isEmpty(pin)) {
            setPasswordError("Password cannot be empty");
            validated = false;
        } else {
            setPasswordError("");
            validated = true;
        }
        return validated;
    }

    @NonNull
    @Override
    public String toString() {
        return "AuthValidation{" +
                "emailError:" + emailError + '\'' +
                "passwordError:" + passwordError + '\'' +
                "}";
    }
}
