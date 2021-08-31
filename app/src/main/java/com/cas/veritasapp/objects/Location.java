package com.cas.veritasapp.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable {

    private String street;
    private String city;
    @SerializedName(value = "lga_code")
    private String lga_code;
    @SerializedName(value = "state_code")
    private String stateCode;
    @SerializedName(value = "zip_code")
    private String zip_code;
    @SerializedName(value = "p_o_box")
    private String POBox;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLga_code() {
        return lga_code;
    }

    public void setLga_code(String lga_code) {
        this.lga_code = lga_code;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getPOBox() {
        return POBox;
    }

    public void setPOBox(String POBox) {
        this.POBox = POBox;
    }

    @Override
    public String toString() {
        return "Location{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", lga_code='" + lga_code + '\'' +
                ", zip_code='" + zip_code + '\'' +
                ", POBox='" + POBox + '\'' +
                '}';
    }
}
