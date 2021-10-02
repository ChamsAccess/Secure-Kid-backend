package com.cas.veritasapp.core.data.entities;

import androidx.room.Entity;

import com.cas.veritasapp.objects.payloads.StatePayload;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "states")
public class State implements Serializable {
    @SerializedName(value = "_id")
    private String id;
    private List<LGA> lgas;
    private String name;
    private String code;

    public static State create(StatePayload payload) {
        State state = new State();
        state.setId(payload.id);
        state.setCode(payload.code);
        state.setLgas(payload.lgas);
        state.setName(payload.name);
        return state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LGA> getLgas() {
        return lgas;
    }

    public void setLgas(List<LGA> lgas) {
        this.lgas = lgas;
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
        return "State{" +
                "id='" + id + '\'' +
                ", lgas=" + lgas +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
