package com.cas.veritasapp.core.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


import com.cas.veritasapp.core.data.converters.StaffLevelConverter;
import com.cas.veritasapp.core.data.converters.TimeConverter;
import com.cas.veritasapp.core.data.converters.UserConverter;
import com.cas.veritasapp.objects.StaffLevel;
import com.cas.veritasapp.objects.payloads.StaffPayload;
import com.cas.veritasapp.util.LogUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by funmiayinde on 2019-09-27.
 */
@Entity(tableName = "staffs")
public class Staff implements Serializable {

    private static final String TAG = Staff.class.getSimpleName();

    @PrimaryKey
    @NonNull
    private String _id;
    private String staff_id;
    private String unique_staff_id_no;
    private String gender;

    @TypeConverters(StaffLevelConverter.class)
    private StaffLevel level;

    @TypeConverters(UserConverter.class)
    private User user;

    @ColumnInfo(name = "user_id")
    private String userId;

    private boolean is_admin;
    private boolean active;
    private boolean suspended;

    @TypeConverters(TimeConverter.class)
    private Date createdAt;

    public static Staff create(StaffPayload payload){
        LogUtil.info("payload: ", payload.toString());
        Staff staff = new Staff();
        staff.set_id(payload._id);
        return staff;
    }

    @NonNull
    public String get_id() {
        return _id;
    }

    public void set_id(@NonNull String _id) {
        this._id = _id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getUnique_staff_id_no() {
        return unique_staff_id_no;
    }

    public void setUnique_staff_id_no(String unique_staff_id_no) {
        this.unique_staff_id_no = unique_staff_id_no;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public StaffLevel getLevel() {
        return level;
    }

    public void setLevel(StaffLevel level) {
        this.level = level;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAdmin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @NonNull
    @Override
    public String toString() {
        return "Staff:{" + "\n" +
                "staff_id:" + staff_id + "\n" +
                "unique_staff_id_no:" + unique_staff_id_no + "\n" +
                "gender:" + gender + "\n" +
                "is_admin:" + is_admin + "\n" +
                "active:" + active + "\n" +
                "suspended:" + suspended;
    }
}
