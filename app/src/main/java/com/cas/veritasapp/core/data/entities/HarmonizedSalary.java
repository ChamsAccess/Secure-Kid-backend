package com.cas.veritasapp.core.data.entities;

import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "harmoinzed_salaries")
public class HarmonizedSalary implements Serializable {

    public String salary_structure;
    public String cls_at_june_2004;
    public String step_at_june_2004;

    public String getSalary_structure() {
        return salary_structure;
    }

    public void setSalary_structure(String salary_structure) {
        this.salary_structure = salary_structure;
    }

    public String getCls_at_june_2004() {
        return cls_at_june_2004;
    }

    public void setCls_at_june_2004(String cls_at_june_2004) {
        this.cls_at_june_2004 = cls_at_june_2004;
    }

    public String getStep_at_june_2004() {
        return step_at_june_2004;
    }

    public void setStep_at_june_2004(String step_at_june_2004) {
        this.step_at_june_2004 = step_at_june_2004;
    }

    @Override
    public String toString() {
        return "HarmonizedSalary2004{" +
                "salary_structure='" + salary_structure + '\'' +
                ", cls_at_june_2004='" + cls_at_june_2004 + '\'' +
                ", step_at_june_2004='" + step_at_june_2004 + '\'' +
                '}';
    }
}
