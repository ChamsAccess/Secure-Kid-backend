package com.cas.veritasapp.objects.payloads;

import com.cas.veritasapp.core.data.entities.Country;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CountryPayload<T> implements Serializable {
    @SerializedName(value = "_id")
    public String id;
    public List<T> states;
    public String name;
    public String code;

    public static CountryPayload create(Country country){
        CountryPayload payload = new CountryPayload();
        payload.id = country.getId();
        payload.name = country.getName();
        payload.code = country.getCode();
        payload.states = country.getStates();
        return payload;
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
