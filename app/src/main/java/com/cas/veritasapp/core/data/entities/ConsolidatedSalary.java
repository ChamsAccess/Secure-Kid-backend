package com.cas.veritasapp.core.data.entities;

import java.io.Serializable;

public class ConsolidatedSalary implements Serializable {

    public String salary_structure;
    public String cls_at_june_2007;
    public String step_at_june_2000;

    public String getSalary_structure() {
        return salary_structure;
    }

    public void setSalary_structure(String salary_structure) {
        this.salary_structure = salary_structure;
    }

    public String getCls_at_june_2007() {
        return cls_at_june_2007;
    }

    public void setCls_at_june_2007(String cls_at_june_2007) {
        this.cls_at_june_2007 = cls_at_june_2007;
    }

    public String getStep_at_june_2000() {
        return step_at_june_2000;
    }

    public void setStep_at_june_2000(String step_at_june_2000) {
        this.step_at_june_2000 = step_at_june_2000;
    }

    @Override
    public String toString() {
        return "ConsolidatedSalary2010{" +
                "salary_structure='" + salary_structure + '\'' +
                ", cls_at_june_2010='" + cls_at_june_2007 + '\'' +
                ", step_at_june_2000='" + step_at_june_2000 + '\'' +
                '}';
    }
}
