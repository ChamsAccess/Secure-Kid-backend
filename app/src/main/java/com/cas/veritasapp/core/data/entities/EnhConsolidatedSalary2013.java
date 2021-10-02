package com.cas.veritasapp.core.data.entities;

import java.io.Serializable;

public class EnhConsolidatedSalary2013 implements Serializable {
    public String salary_structure;
    public String cls_at_2013;
    public String step_at_2013;

    public String getSalary_structure() {
        return salary_structure;
    }

    public void setSalary_structure(String salary_structure) {
        this.salary_structure = salary_structure;
    }

    public String getCls_at_2013() {
        return cls_at_2013;
    }

    public void setCls_at_2013(String cls_at_2013) {
        this.cls_at_2013 = cls_at_2013;
    }

    public String getStep_at_2013() {
        return step_at_2013;
    }

    public void setStep_at_2013(String step_at_2013) {
        this.step_at_2013 = step_at_2013;
    }

    @Override
    public String toString() {
        return "EnhConsolidatedSalary2013{" +
                "salary_structure='" + salary_structure + '\'' +
                ", cls_at_2013='" + cls_at_2013 + '\'' +
                ", step_at_2013='" + step_at_2013 + '\'' +
                '}';
    }
}
