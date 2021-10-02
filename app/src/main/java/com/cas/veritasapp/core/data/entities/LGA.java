package com.cas.veritasapp.core.data.entities;

import androidx.room.Entity;

import com.cas.veritasapp.objects.payloads.LGAPayload;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "lgas")
public class LGA implements Serializable {
    @SerializedName(value = "_id")
    private String id;
    private String name;
    private String code;

    public static LGA create(LGAPayload payload){
        LGA lga = new LGA();
        lga.setId(payload.id);
        lga.setName(payload.name);
        lga.setCode(payload.code);
        return lga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "LGA{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
