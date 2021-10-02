package com.cas.veritasapp.objects.payloads;

import com.cas.veritasapp.core.data.entities.LGA;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LGAPayload implements Serializable {
    @SerializedName(value = "_id")
    public String id;
    public String name;
    public String code;

    public static LGAPayload create(LGA lga){
        LGAPayload payload = new LGAPayload();
        payload.id = lga.getId();
        payload.name = lga.getName();
        payload.code = lga.getCode();
        return payload;
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
