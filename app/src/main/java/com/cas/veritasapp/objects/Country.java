package com.cas.veritasapp.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Country implements Serializable {
    @SerializedName(value = "_id")
    public String id;
    public State[] states;
    public String name;
    public String code;

    public String getId() {
        return id;
    }

    public State[] getStates() {
        return states;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
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
