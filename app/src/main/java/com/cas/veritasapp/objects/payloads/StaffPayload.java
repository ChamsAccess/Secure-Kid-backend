package com.cas.veritasapp.objects.payloads;

import androidx.annotation.NonNull;

import com.cas.veritasapp.core.data.entities.Staff;
import com.cas.veritasapp.core.data.entities.User;
import com.cas.veritasapp.objects.StaffLevel;

/**
 * Created by funmiayinde on 2019-10-14.
 */
public class StaffPayload {
    public String _id;
    public String staff_level;
    public String staff_id;
    public String unique_staff_id_no;
    public String gender;
    public User user;
    public StaffLevel level;
    public boolean is_admin;
    public boolean active;
    public boolean suspended;


    public static StaffPayload create(Staff staff) {
        StaffPayload payload = new StaffPayload();
        payload._id = staff.get_id();
        payload.user = staff.getUser();
        payload.is_admin = staff.isAdmin();
        payload.active = staff.isActive();
        payload.suspended = staff.isSuspended();
        payload.unique_staff_id_no = staff.getUnique_staff_id_no();
        payload.gender = staff.getGender();
        payload.staff_id = staff.getStaff_id();
        return payload;
    }


    @NonNull
    @Override
    public String toString() {
        return "StaffPayload{" +
                "id=" + _id + '\'' +
                "level=" + level + '\'' +
                "is_admin=" + is_admin + '\'' +
                "active=" + active + '\'' +
                "staff_level=" + staff_level + '\'' +
                "unique_staff_id_no=" + unique_staff_id_no + '\'' +
                "gender=" + gender + '\'' +
                "}";
    }
}
