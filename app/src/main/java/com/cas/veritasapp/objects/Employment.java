package com.cas.veritasapp.objects;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Employment {
    @SerializedName(value = "sector_classification;")
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
    public String country;
    @SerializedName(value = "house_number")
    public String houseNumber;
    @SerializedName(value = "office_location")
    public Location location;

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
