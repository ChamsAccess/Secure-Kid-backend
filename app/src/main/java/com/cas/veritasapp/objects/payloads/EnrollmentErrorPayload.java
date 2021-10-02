package com.cas.veritasapp.objects.payloads;

import androidx.annotation.NonNull;

import com.cas.veritasapp.core.data.entities.Staff;
import com.cas.veritasapp.core.data.entities.User;
import com.cas.veritasapp.objects.StaffLevel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by funmiayinde on 2019-10-14.
 */
public class EnrollmentErrorPayload implements Serializable {
    public String _id;
    public String enrollment;
    public String status;
    public ArrayList<String> issues;

    @Override
    public String toString() {
        return "EnrollmentErrorPayload{" +
                "_id='" + _id + '\'' +
                ", enrollment='" + enrollment + '\'' +
                ", status='" + status + '\'' +
                ", issues=" + issues +
                '}';
    }
}
