package com.cas.veritasapp.core.data.entities;

import androidx.annotation.NonNull;

import com.cas.veritasapp.objects.payloads.CountryPayload;
import com.cas.veritasapp.objects.payloads.EmploymentPayload;
import com.cas.veritasapp.objects.payloads.LocationPayload;
import com.cas.veritasapp.util.ServiceUtil;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Employment implements Serializable {
    @SerializedName(value = "_id")
    private String id;
    @SerializedName(value = "sector_classification")
    private String sectorClassification;
    @SerializedName(value = "employerunder_ipps")
    private String employerUnderIPPS;
    @SerializedName(value = "employee_ipps_number")
    private String employeeIPPSNumber;
    @SerializedName(value = "employer_name")
    private String employerName;
    @SerializedName(value = "employer_card_id")
    private String employerCardId;
    @SerializedName(value = "designation")
    private String designation;
    @SerializedName(value = "date_of_first_appointment")
    private String dateOfFirstAppointment;
    @SerializedName(value = "date_of_current_employment")
    private String dateOfCurrentEmployment;
    @SerializedName(value = "date_of_transfer_service")
    private String dateOfTransferService;
    @SerializedName(value = "nature_of_business")
    private String natureOfBusiness;
    @SerializedName(value = "service_id")
    private String serviceId;
    @SerializedName(value = "employer_phone")
    private String employerPhone;
    @SerializedName(value = "date_joined")
    private String dateJoined;
    @SerializedName(value = "country_code")
    private String countryCode;

    private String country;
    private Country countryObject;

    @SerializedName(value = "house_number")
    private String houseNumber;

    @SerializedName(value = "office_location")
    private Location location;

    public static Employment create(EmploymentPayload payload){
        Employment employment = new Employment();
        employment.setId(payload.id);
        employment.setEmployeeIPPSNumber(payload.employeeIPPSNumber);
        employment.setSectorClassification(payload.sectorClassification);
        employment.setEmployerUnderIPPS(payload.employerUnderIPPS);
        employment.setEmployerCardId(payload.employerCardId);
        employment.setDesignation(payload.designation);
        employment.setDateOfFirstAppointment(payload.dateOfFirstAppointment);
        employment.setDateOfCurrentEmployment(payload.dateOfCurrentEmployment);
        employment.setDateOfTransferService(payload.dateOfTransferService);
        employment.setNatureOfBusiness(payload.natureOfBusiness);
        employment.setServiceId(payload.serviceId);
        employment.setEmployerPhone(payload.employerPhone);
        employment.setDateJoined(payload.dateJoined);
        employment.setCountryCode(payload.countryCode);
        employment.setHouseNumber(payload.houseNumber);
        if (payload.country != null) {
            if (ServiceUtil.isPrimitive(payload.country)) {
                employment.setCountry(payload.country.toString());
            } else {
                CountryPayload countryPayload = ServiceUtil.getObjectValue(payload.country, CountryPayload.class);
                employment.setCountryObject(Country.create(countryPayload));
                employment.setCountry(countryPayload.id);
            }
        }
        if (payload.location != null) {
            if (ServiceUtil.isPrimitive(payload.location)) {
                LocationPayload locationPayload = ServiceUtil.getObjectValue(payload.location, LocationPayload.class);
                employment.setLocation(Location.create(locationPayload));
            }
        }
        return employment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectorClassification() {
        return sectorClassification;
    }

    public void setSectorClassification(String sectorClassification) {
        this.sectorClassification = sectorClassification;
    }

    public String getEmployerUnderIPPS() {
        return employerUnderIPPS;
    }

    public void setEmployerUnderIPPS(String employerUnderIPPS) {
        this.employerUnderIPPS = employerUnderIPPS;
    }

    public String getEmployeeIPPSNumber() {
        return employeeIPPSNumber;
    }

    public void setEmployeeIPPSNumber(String employeeIPPSNumber) {
        this.employeeIPPSNumber = employeeIPPSNumber;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerCardId() {
        return employerCardId;
    }

    public void setEmployerCardId(String employerCardId) {
        this.employerCardId = employerCardId;
    }

    public String getDateOfFirstAppointment() {
        return dateOfFirstAppointment;
    }

    public void setDateOfFirstAppointment(String dateOfFirstAppointment) {
        this.dateOfFirstAppointment = dateOfFirstAppointment;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDateOfCurrentEmployment() {
        return dateOfCurrentEmployment;
    }

    public void setDateOfCurrentEmployment(String dateOfCurrentEmployment) {
        this.dateOfCurrentEmployment = dateOfCurrentEmployment;
    }

    public String getDateOfTransferService() {
        return dateOfTransferService;
    }

    public void setDateOfTransferService(String dateOfTransferService) {
        this.dateOfTransferService = dateOfTransferService;
    }

    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public void setNatureOfBusiness(String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }

    public Country getCountryObject() {
        return countryObject;
    }

    public void setCountryObject(Country countryObject) {
        this.countryObject = countryObject;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getEmployerPhone() {
        return employerPhone;
    }

    public void setEmployerPhone(String employerPhone) {
        this.employerPhone = employerPhone;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @NonNull
    @Override
    public String toString() {
        return "Employment{" +
                "employerUnderIPPS='" + employerUnderIPPS + '\'' +
                ", sectorClassification='" + sectorClassification.toString() + '\'' +
                ", employeeIPPSNumber='" + employeeIPPSNumber + '\'' +
                ", employerName='" + employerName + '\'' +
                ", employerCardId='" + employerCardId + '\'' +
                ", dateOfFirstAppointment='" + dateOfFirstAppointment + '\'' +
                ", dateOfCurrentEmployment='" + dateOfCurrentEmployment + '\'' +
                ", dateOfTransferService='" + dateOfTransferService + '\'' +
                ", natureOfBusiness='" + natureOfBusiness + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", employerPhone='" + employerPhone + '\'' +
                ", dateJoined='" + dateJoined + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", country='" + country + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", location=" + location.toString() +
                '}';
    }
}
