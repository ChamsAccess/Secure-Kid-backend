package com.cas.veritasapp.objects.payloads;

import com.cas.veritasapp.core.data.entities.Location;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LocationPayload<T> implements Serializable {
    public String street;
    public String city;
    @SerializedName(value = "lga_code")
    public T lgaCode;
    @SerializedName(value = "state_code")
    public T stateCode;
    @SerializedName(value = "zip_code")
    public String zip_code;
    @SerializedName(value = "p_o_box")
    public String POBox;


    public static LocationPayload create(Location location){
        LocationPayload payload = new LocationPayload();
        payload.street = location.getStreet();
        payload.city = location.getCity();
        payload.lgaCode = location.getLga_code();
        payload.stateCode = location.getStateCode();
        payload.zip_code = location.getZip_code();
        payload.POBox = location.getPOBox();
        return payload;
    }

    @Override
    public String toString() {
        return "LocationPayload{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", lgaCode=" + lgaCode +
                ", stateCode=" + stateCode +
                ", zip_code='" + zip_code + '\'' +
                ", POBox='" + POBox + '\'' +
                '}';
    }
}
