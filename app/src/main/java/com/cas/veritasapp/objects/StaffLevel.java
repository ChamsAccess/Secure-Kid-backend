package com.cas.veritasapp.objects;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by funmiayinde on 2019-09-27.
 */
public class StaffLevel implements Serializable {
    private String _id;
    private boolean active;
    private String name;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return "StaffLevel {" +
                "id:" + _id + "\n" +
                "active:" + active + "\n" +
                "name:" + name +
                "}";
    }
}
