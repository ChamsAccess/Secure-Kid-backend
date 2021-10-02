package com.cas.veritasapp.core.data.entities;

import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "enconsolidated_salary_2016")
public class EnhConsolidatedSalary2016 implements Serializable {
    private String salary_structure;
    private String cls_at_2016;
    private String step_at_2016;

    public String getSalary_structure() {
        return salary_structure;
    }

    public void setSalary_structure(String salary_structure) {
        this.salary_structure = salary_structure;
    }

    public String getCls_at_2016() {
        return cls_at_2016;
    }

    public void setCls_at_2016(String cls_at_2016) {
        this.cls_at_2016 = cls_at_2016;
    }

    public String getStep_at_2016() {
        return step_at_2016;
    }

    public void setStep_at_2016(String step_at_2016) {
        this.step_at_2016 = step_at_2016;
    }

    @Override
    public String toString() {
        return "EnhConsolidatedSalary2016{" +
                "salary_structure='" + salary_structure + '\'' +
                ", cls_at_2016='" + cls_at_2016 + '\'' +
                ", step_at_2016='" + step_at_2016 + '\'' +
                '}';
    }
}
