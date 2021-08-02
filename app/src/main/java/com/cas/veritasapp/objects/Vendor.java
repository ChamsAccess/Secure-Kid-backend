package com.cas.veritasapp.objects;

import java.io.Serializable;

/**
 * Created by funmiayinde on 2019-10-14.
 */
public class Vendor implements Serializable {

    private String _id;
    private String name;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
