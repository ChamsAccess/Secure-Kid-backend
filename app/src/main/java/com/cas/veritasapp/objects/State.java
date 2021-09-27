package com.cas.veritasapp.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class State implements Serializable {
    @SerializedName(value = "_id")
    public String id;
    public LGA[] lgas;
    public String name;
    public String code;

    public String getId() {
        return id;
    }

    public LGA[] getLgas() {
        return lgas;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "State{" +
                "id='" + id + '\'' +
                ", lgas=" +  lgas+
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
