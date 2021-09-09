package com.cas.veritasapp.objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.cas.veritasapp.core.data.converters.ContributionBioConverter;
import com.cas.veritasapp.core.data.converters.EmploymentConverter;
import com.cas.veritasapp.core.data.converters.NextOfKinConverter;
import com.cas.veritasapp.core.data.converters.PFACertificationConverter;
import com.cas.veritasapp.core.data.converters.PersonalConverter;
import com.cas.veritasapp.core.data.converters.SalaryConverter;
import com.cas.veritasapp.objects.payloads.EnrollmentPayload;
import com.cas.veritasapp.util.ServiceUtil;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "enrollments")
public class Enrollment implements Serializable {

    @PrimaryKey
    @NonNull
    private String _id;
    private String enrolled_by;
    private String createAt;
    public String createdAt;
    @SerializedName(value = "rsa_pin")
    private String rsaPin;
    @SerializedName(value = "t_pin")
    private String tPin;

    private String photo;
    private String signature;
    private String agentSignature;

    private boolean submitted;

    @TypeConverters(PersonalConverter.class)
    private Personal personalObject;
    private String personal;

    @TypeConverters(NextOfKinConverter.class)
    private NextOfKin nextOfKinObject;
    private String next_of_kin;

    @TypeConverters(EmploymentConverter.class)
    private Employment employmentObject;
    private String employment;

    @TypeConverters(SalaryConverter.class)
    private Salary salaryObject;
    private String salary;

    @TypeConverters(PFACertificationConverter.class)
    private PFACertification pfa_certificationObject;
    private String pfa_certification;

    @TypeConverters(ContributionBioConverter.class)
    public ContributionBio contributionBioObject;
    private String contribution_bio;


    @NonNull
    public String get_id() {
        return _id;
    }

    public void set_id(@NonNull String _id) {
        this._id = _id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getRsaPin() {
        return rsaPin;
    }

    public String gettPin() {
        return tPin;
    }

    public void settPin(String tPin) {
        this.tPin = tPin;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public void setRsaPin(String rsaPin) {
        this.rsaPin = rsaPin;
    }

    public static Enrollment create(EnrollmentPayload payload) {
        Enrollment enrollment = new Enrollment();
        enrollment.set_id(payload._id);
        enrollment.setCreateAt(payload.createdAt);
        enrollment.setRsaPin(payload.rsaPin);
        enrollment.settPin(payload.tPin);
        enrollment.setSubmitted(payload.sumbitted);
        enrollment.setEnrolled_by(payload.enrolled_by);
        if (payload.personal != null) {
            if (ServiceUtil.isPrimitive(payload.personal)) {
                enrollment.setPersonal(payload.personal.toString());
            } else {
                Personal personal = ServiceUtil.getObjectValue(payload.personal, Personal.class);
                enrollment.setPersonalObject(personal);
            }
        }
        if (payload.employment != null) {
            if (ServiceUtil.isPrimitive(payload.employment)) {
                enrollment.setEmployment(payload.employment.toString());
            } else {
                Employment employment = ServiceUtil.getObjectValue(payload.employment, Employment.class);
                enrollment.setEmploymentObject(employment);
            }
        }
        if (payload.contribution_bio != null) {
            if (ServiceUtil.isPrimitive(payload.contribution_bio)) {
                enrollment.setContribution_bio(payload.contribution_bio.toString());
            } else {
                ContributionBio contributionBio = ServiceUtil.getObjectValue(payload.contribution_bio, ContributionBio.class);
                enrollment.setContributionBioObject(contributionBio);
            }
        }
        if (payload.salary != null) {
            if (ServiceUtil.isPrimitive(payload.salary)) {
                enrollment.setSalary(payload.salary.toString());
            } else {
                Salary salary = ServiceUtil.getObjectValue(payload.salary, Salary.class);
                enrollment.setSalaryObject(salary);
            }
        }
        if (payload.pfa_certification != null) {
            if (ServiceUtil.isPrimitive(payload.pfa_certification)) {
                enrollment.setPfa_certification(payload.pfa_certification.toString());
            } else {
                PFACertification pfaCertification = ServiceUtil.getObjectValue(payload.salary, PFACertification.class);
                enrollment.setPfa_certificationObject(pfaCertification);
            }
        }
        return enrollment;
    }

    public static List<Enrollment> update(List<EnrollmentPayload> payloads) {
        List<Enrollment> list = new ArrayList<>();
        for (EnrollmentPayload payload : payloads) {
            Enrollment enrollment = Enrollment.create(payload);
            list.add(enrollment);
        }
        return list;
    }

    public String getEnrolled_by() {
        return enrolled_by;
    }

    public void setEnrolled_by(String enrolled_by) {
        this.enrolled_by = enrolled_by;
    }

    public Personal getPersonalObject() {
        return personalObject;
    }

    public void setPersonalObject(Personal personalObject) {
        this.personalObject = personalObject;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public NextOfKin getNextOfKinObject() {
        return nextOfKinObject;
    }

    public void setNextOfKinObject(NextOfKin nextOfKinObject) {
        this.nextOfKinObject = nextOfKinObject;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSignature() {
        return signature;
    }

    public String getAgentSignature() {
        return agentSignature;
    }

    public void setAgentSignature(String agentSignature) {
        this.agentSignature = agentSignature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getNext_of_kin() {
        return next_of_kin;
    }

    public void setNext_of_kin(String next_of_kin) {
        this.next_of_kin = next_of_kin;
    }

    public Employment getEmploymentObject() {
        return employmentObject;
    }

    public void setEmploymentObject(Employment employmentObject) {
        this.employmentObject = employmentObject;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public Salary getSalaryObject() {
        return salaryObject;
    }

    public void setSalaryObject(Salary salaryObject) {
        this.salaryObject = salaryObject;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public PFACertification getPfa_certificationObject() {
        return pfa_certificationObject;
    }

    public void setPfa_certificationObject(PFACertification pfa_certificationObject) {
        this.pfa_certificationObject = pfa_certificationObject;
    }

    public String getPfa_certification() {
        return pfa_certification;
    }

    public void setPfa_certification(String pfa_certification) {
        this.pfa_certification = pfa_certification;
    }

    public ContributionBio getContributionBioObject() {
        return contributionBioObject;
    }

    public void setContributionBioObject(ContributionBio contributionBioObject) {
        this.contributionBioObject = contributionBioObject;
    }

    public String getContribution_bio() {
        return contribution_bio;
    }

    public void setContribution_bio(String contribution_bio) {
        this.contribution_bio = contribution_bio;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrolled_by='" + enrolled_by + '\'' +
                ", photo=" + photo +
                ", signature=" + signature +
                ", personal=" + personal +
                ", next_of_kin=" + next_of_kin +
                ", employment=" + employment +
                ", salary=" + salary +
                ", pfa_certification=" + pfa_certification +
                ", contribution_bio=" + contribution_bio +
                '}';
    }
}
