package com.cas.veritasapp.core.data.entities;

import com.cas.veritasapp.objects.payloads.CountryPayload;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Country implements Serializable {
    @SerializedName(value = "_id")
    private String id;
    private List<State> states;
    private String name;
    private String code;

    public static Country create(CountryPayload payload){
        Country country = new Country();
        country.setId(payload.id);
        country.setName(payload.name);
        country.setCode(payload.code);
        country.setStates(payload.states);
        return country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id='" + id + '\'' +
                ", states='" + states + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
