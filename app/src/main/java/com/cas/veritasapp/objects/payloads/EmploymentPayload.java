package com.cas.veritasapp.objects.payloads;

import androidx.annotation.NonNull;

import com.cas.veritasapp.core.data.entities.Employment;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EmploymentPayload<T> implements Serializable {
    @SerializedName(value = "_id")
    public String id;
    @SerializedName(value = "sector_classification")
    public String sectorClassification;
    @SerializedName(value = "employerunder_ipps")
    public String employerUnderIPPS;
    @SerializedName(value = "employee_ipps_number")
    public String employeeIPPSNumber;
    @SerializedName(value = "employer_name")
    public String employerName;
    @SerializedName(value = "employer_card_id")
    public String employerCardId;
    @SerializedName(value = "designation")
    public String designation;
    @SerializedName(value = "date_of_first_appointment")
    public String dateOfFirstAppointment;
    @SerializedName(value = "date_of_current_employment")
    public String dateOfCurrentEmployment;
    @SerializedName(value = "date_of_transfer_service")
    public String dateOfTransferService;
    @SerializedName(value = "nature_of_business")
    public String natureOfBusiness;
    @SerializedName(value = "service_id")
    public String serviceId;
    @SerializedName(value = "employer_phone")
    public String employerPhone;
    @SerializedName(value = "date_joined")
    public String dateJoined;
    @SerializedName(value = "country_code")
    public String countryCode;
    public T country;
    @SerializedName(value = "house_number")
    public String houseNumber;
    @SerializedName(value = "location")
    public T location;

    public static EmploymentPayload create(Employment employment){
        EmploymentPayload payload = new EmploymentPayload();
        payload.id = employment.getId();
        payload.sectorClassification = employment.getSectorClassification();
        payload.country = employment.getCountry();
        payload.employeeIPPSNumber = employment.getEmployeeIPPSNumber();
        payload.employerCardId = employment.getEmployerCardId();
        payload.employerName = employment.getEmployerName();
        payload.designation = employment.getDesignation();
        payload.dateOfFirstAppointment = employment.getDateOfFirstAppointment();
        payload.dateOfCurrentEmployment = employment.getDateOfCurrentEmployment();
        payload.dateOfTransferService = employment.getDateOfTransferService();
        payload.natureOfBusiness = employment.getNatureOfBusiness();
        payload.serviceId = employment.getServiceId();
        payload.employerPhone = employment.getEmployerPhone();
        payload.dateJoined = employment.getDateJoined();
        payload.countryCode = employment.getCountryCode();
        payload.country = employment.getCountry();
        payload.location = employment.getLocation();
        return payload;
    }


//    @NonNull
//    @Override
//    public String toString() {
//        return "Employment{" +
//                "employerUnderIPPS='" + employerUnderIPPS + '\'' +
//                ", sectorClassification='" + sectorClassification.toString() + '\'' +
//                ", employeeIPPSNumber='" + employeeIPPSNumber + '\'' +
//                ", employerName='" + employerName + '\'' +
//                ", employerCardId='" + employerCardId + '\'' +
//                ", dateOfFirstAppointment='" + dateOfFirstAppointment + '\'' +
//                ", dateOfCurrentEmployment='" + dateOfCurrentEmployment + '\'' +
//                ", dateOfTransferService='" + dateOfTransferService + '\'' +
//                ", natureOfBusiness='" + natureOfBusiness + '\'' +
//                ", serviceId='" + serviceId + '\'' +
//                ", employerPhone='" + employerPhone + '\'' +
//                ", dateJoined='" + dateJoined + '\'' +
//                ", countryCode='" + countryCode + '\'' +
//                ", country='" + country + '\'' +
//                ", houseNumber='" + houseNumber + '\'' +
//                ", location=" + location.toString() +
//                '}';
//    }
}
