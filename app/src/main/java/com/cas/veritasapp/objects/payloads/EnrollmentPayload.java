package com.cas.veritasapp.objects.payloads;


import androidx.room.PrimaryKey;

import com.cas.veritasapp.core.data.entities.Enrollment;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.reactivex.annotations.NonNull;

public class EnrollmentPayload<T> implements Serializable {

    @PrimaryKey
    @NonNull
    public String _id;
    public String enrolled_by;
    public String createdAt;
    public String status;
    @SerializedName(value = "rsa_pin")
    public String rsaPin;
    @SerializedName(value = "t_pin")
    public String tPin;
    public boolean submitted;
    public T personal;
    public T next_of_kin;
    public T employment;
    public T salary;
    public T pfa_certification;
    public T contribution_bio;

    public static EnrollmentPayload create(Enrollment enrollment) {
        EnrollmentPayload payload = new EnrollmentPayload();
        payload._id = enrollment.get_id();
        payload.createdAt = enrollment.getCreateAt();
        payload.submitted = enrollment.isSubmitted();
        payload.tPin = enrollment.gettPin();
        payload.status = enrollment.getStatus();
        payload.personal = enrollment.getPersonal();
        payload.next_of_kin = enrollment.getNext_of_kin();
        payload.employment = enrollment.getEmployment();
        payload.salary = enrollment.getSalary();
        payload.pfa_certification = enrollment.getPfa_certification();
        payload.contribution_bio = enrollment.getContribution_bio();
        return payload;
    }

    @Override
    public String toString() {
        return "EnrollmentPayload{" +
                "_id='" + _id + '\'' +
                ", enrolled_by='" + enrolled_by + '\'' +
                ", personal=" + personal +
                ", next_of_kin=" + next_of_kin +
                ", employment=" + employment +
                ", salary=" + salary +
                ", pfa_certification=" + pfa_certification +
                ", createdAt=" + createdAt +
                ", contribution_bio=" + contribution_bio +
                '}';
    }
}
