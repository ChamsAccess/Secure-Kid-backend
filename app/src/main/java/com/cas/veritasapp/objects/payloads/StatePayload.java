package com.cas.veritasapp.objects.payloads;

import com.cas.veritasapp.core.data.entities.State;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StatePayload<T> implements Serializable {
    @SerializedName(value = "_id")
    public String id;
    public List<T> lgas;
    public String name;
    public String code;


    public static StatePayload create(State state) {
        StatePayload payload = new StatePayload();
        payload.id = state.getId();
        payload.name = state.getId();
        payload.code = state.getCode();
        payload.lgas = state.getLgas();
        return payload;
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
