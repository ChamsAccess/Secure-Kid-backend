package com.cas.veritasapp.core.data.entities;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "section_classification")
public class SectorClassification implements Serializable {
    @SerializedName(value = "_id")
    private String _id;
    private boolean public_sector_employees;
    private boolean private_sector_employees;
    private boolean micro_pension_plan;
    private boolean cross_border_employees;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public boolean isPublic_sector_employees() {
        return public_sector_employees;
    }

    public void setPublic_sector_employees(boolean public_sector_employees) {
        this.public_sector_employees = public_sector_employees;
    }

    public boolean isPrivate_sector_employees() {
        return private_sector_employees;
    }

    public void setPrivate_sector_employees(boolean private_sector_employees) {
        this.private_sector_employees = private_sector_employees;
    }

    public boolean isMicro_pension_plan() {
        return micro_pension_plan;
    }

    public void setMicro_pension_plan(boolean micro_pension_plan) {
        this.micro_pension_plan = micro_pension_plan;
    }

    public boolean isCross_border_employees() {
        return cross_border_employees;
    }

    public void setCross_border_employees(boolean cross_border_employees) {
        this.cross_border_employees = cross_border_employees;
    }

    @Override
    public String toString() {
        return "SectorClassification{" +
                "public_sector_employees=" + public_sector_employees +
                ", private_sector_employees=" + private_sector_employees +
                ", micro_pension_plan=" + micro_pension_plan +
                ", cross_border_employees=" + cross_border_employees +
                '}';
    }
}
