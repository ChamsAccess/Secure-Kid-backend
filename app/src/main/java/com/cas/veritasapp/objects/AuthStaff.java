package com.cas.veritasapp.objects;


import com.cas.veritasapp.core.data.entities.User;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by funmiayinde on 2019-10-10.
 */
public class AuthStaff implements Serializable {

    private static final String TAG = AuthStaff.class.getSimpleName();

    @SerializedName("_id")
    private String id;
    @SerializedName("staff_id")
    private String staffId;
    @SerializedName("unique_staff_id_no")
    private String uniqueStaffIdNo;
    @SerializedName("is_admin")
    private boolean isAdmin;
    private boolean active;
    private boolean suspended;

    private User user;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getUniqueStaffIdNo() {
        return uniqueStaffIdNo;
    }

    public void setUniqueStaffIdNo(String uniqueStaffIdNo) {
        this.uniqueStaffIdNo = uniqueStaffIdNo;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
