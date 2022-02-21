package com.cas.veritasapp.core.data.entities;

import androidx.room.Entity;

import com.cas.veritasapp.objects.payloads.LGAPayload;
import com.cas.veritasapp.objects.payloads.LocationPayload;
import com.cas.veritasapp.objects.payloads.StatePayload;
import com.cas.veritasapp.util.ServiceUtil;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "locations")
public class Location implements Serializable {

    private String street;
    private String city;
    @SerializedName(value = "lga_code")
    private String lga_code;
    private LGA lgaObject;

    @SerializedName(value = "state_code")
    private String state_code;
    private State stateObject;

    @SerializedName(value = "zip_code")
    private String zip_code;

    @SerializedName(value = "p_o_box")
    private String POBox;


    public static Location create(LocationPayload payload) {
        Location location = new Location();
        location.setCity(payload.city);
        location.setStreet(payload.street);
        location.setPOBox(payload.POBox);
        location.setZip_code(payload.zip_code);
        if (payload.stateCode != null) {
            if (ServiceUtil.isPrimitive(payload.stateCode)) {
                location.setStateCode(payload.stateCode.toString());
            } else {
                StatePayload statePayload = ServiceUtil.getObjectValue(payload.stateCode, StatePayload.class);
                location.setStateObject(State.create(statePayload));
                location.setStateCode(statePayload.id);
            }
        }
        if (payload.lgaCode != null) {
            if (ServiceUtil.isPrimitive(payload.lgaCode)) {
                location.setLga_code(payload.lgaCode.toString());
            } else {
                LGAPayload lgAPayload = ServiceUtil.getObjectValue(payload.lgaCode, LGAPayload.class);
                location.setLgaObject(LGA.create(lgAPayload));
                location.setLga_code(lgAPayload.id);
            }
        }
        return location;
    }

    public String getStreet() {
        return street;
    }

    public LGA getLgaObject() {
        return lgaObject;
    }

    public void setLgaObject(LGA lgaObject) {
        this.lgaObject = lgaObject;
    }

    public State getStateObject() {
        return stateObject;
    }

    public void setStateObject(State stateObject) {
        this.stateObject = stateObject;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public String getStateCode() {
        return state_code;
    }

    public void setStateCode(String stateCode) {
        this.state_code = stateCode;
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
