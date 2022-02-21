package com.cas.veritasapp.core.data.entities;

import com.cas.veritasapp.objects.payloads.EmployerPayload;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Employer implements Serializable {
    private String rcno;
    private String name;
    private String address;
    @SerializedName(value = "emaiL_ADDS")
    private String emailADDS;
    private String industry;
    @SerializedName(value = "businesS_TYPE")
    private String businessType;
    @SerializedName(value = "employeR_ID")
    private String employerID;
    private String phone;
    private String sector;

    public static Employer create(EmployerPayload employerPayload) {
        Employer employer = new Employer();
        employer.rcno = employerPayload.rcno;
        employer.name = employerPayload.name;
        employer.address = employerPayload.address;
        employer.emailADDS = employerPayload.emailADDS;
        employer.industry = employerPayload.industry;
        employer.businessType = employerPayload.businessType;
        employer.employerID = employerPayload.employerID;
        employer.phone = employerPayload.phone;
        employer.sector = employerPayload.sector;
        return employer;
    }

    public String getRcno() {
        return rcno;
    }

    public void setRcno(String rcno) {
        this.rcno = rcno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailADDS() {
        return emailADDS;
    }

    public void setEmailADDS(String emailADDS) {
        this.emailADDS = emailADDS;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getEmployerID() {
        return employerID;
    }

    public void setEmployerID(String employerID) {
        this.employerID = employerID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "rcno='" + rcno + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", emailADDS='" + emailADDS + '\'' +
                ", industry='" + industry + '\'' +
                ", businessType='" + businessType + '\'' +
                ", employerID='" + employerID + '\'' +
                ", phone='" + phone + '\'' +
                ", sector='" + sector + '\'' +
                '}';
    }
}
