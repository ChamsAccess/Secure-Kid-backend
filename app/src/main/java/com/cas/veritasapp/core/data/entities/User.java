package com.cas.veritasapp.core.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


import com.cas.veritasapp.core.data.converters.TimeConverter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by funmiayinde on 2019-09-27.
 */
@Entity
public class User implements Serializable {
    private static final String TAG = User.class.getSimpleName();

    @PrimaryKey
    @NonNull
    private String _id;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile;
    private String address;

    private boolean account_verified;
    private boolean password_reset;
    private boolean change_password;

    @TypeConverters(TimeConverter.class)
    private Date createdAt;

    public static String getTAG() {
        return TAG;
    }

    @NonNull
    public String get_id() {
        return _id;
    }

    public void set_id(@NonNull String _id) {
        this._id = _id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAccount_verified() {
        return account_verified;
    }

    public void setAccount_verified(boolean account_verified) {
        this.account_verified = account_verified;
    }

    public boolean isPassword_reset() {
        return password_reset;
    }

    public void setPassword_reset(boolean password_reset) {
        this.password_reset = password_reset;
    }

    public boolean isChange_password() {
        return change_password;
    }

    public void setChange_password(boolean change_password) {
        this.change_password = change_password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
