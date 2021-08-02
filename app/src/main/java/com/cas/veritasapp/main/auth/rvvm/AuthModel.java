package com.cas.veritasapp.main.auth.rvvm;

import androidx.annotation.NonNull;

import dagger.Module;

/**
 * Created by funmiayinde on 2019-10-14.
 */
public class AuthModel {

    private String email;
    private String password;
    private String token;


    public AuthModel(String email) {
        this.email = email;
    }

    public AuthModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @NonNull
    @Override
    public String toString() {
        return "AuthModel{" +
                "email= " + email + '\'' +
                "password=" + password + '\'' +
                "token=" + token + '\'' +
                "}";
    }
}
