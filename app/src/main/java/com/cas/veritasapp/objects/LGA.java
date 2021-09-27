package com.cas.veritasapp.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LGA implements Serializable {
    @SerializedName(value = "_id")
    public String id;
    public String name;
    public String code;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "LGA{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
