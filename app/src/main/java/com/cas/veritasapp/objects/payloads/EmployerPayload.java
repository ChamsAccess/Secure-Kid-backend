package com.cas.veritasapp.objects.payloads;

import com.cas.veritasapp.core.data.entities.Employer;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EmployerPayload implements Serializable {
    public String rcno;
    public String name;
    public String address;
    @SerializedName(value = "emaiL_ADDS")
    public String emailADDS;
    public String industry;
    @SerializedName(value = "businesS_TYPE")
    public String businessType;
    @SerializedName(value = "employeR_ID")
    public String employerID;
    public String phone;
    public String sector;

    public static EmployerPayload create(Employer employer) {
        EmployerPayload payload = new EmployerPayload();
        payload.rcno = employer.getRcno();
        payload.name = employer.getName();
        payload.address = employer.getAddress();
        payload.emailADDS = employer.getEmailADDS();
        payload.industry = employer.getIndustry();
        payload.businessType = employer.getBusinessType();
        payload.employerID = employer.getEmployerID();
        payload.phone = employer.getPhone();
        payload.sector = employer.getSector();
        return payload;
    }

    @Override
    public String toString() {
        return "EmployerPayload{" +
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
