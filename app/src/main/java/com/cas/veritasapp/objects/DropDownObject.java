package com.cas.veritasapp.objects;

public class DropDownObject {
    private String _id;
    private String name;
    private String code;
    private boolean modified;

    public DropDownObject(String _id, String name) {
        this._id = _id;
        this.name = name;
    }

    public DropDownObject(String _id, String name, String code) {
        this._id = _id;
        this.name = name;
        this.code = code;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return name;
    }
}
