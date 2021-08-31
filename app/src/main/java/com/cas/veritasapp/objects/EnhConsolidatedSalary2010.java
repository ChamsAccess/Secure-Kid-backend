package com.cas.veritasapp.objects;

import java.io.Serializable;

public class EnhConsolidatedSalary2010 implements Serializable {

    public String salary_structure;
    public String cls_at_2013;
    public String step_at_2010;

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

    public String getStep_at_2010() {
        return step_at_2010;
    }

    public void setStep_at_2010(String step_at_2010) {
        this.step_at_2010 = step_at_2010;
    }

    @Override
    public String toString() {
        return "EnhConsolidatedSalary2010{" +
                "salary_structure='" + salary_structure + '\'' +
                ", cls_at_2013='" + cls_at_2013 + '\'' +
                ", step_at_2010='" + step_at_2010 + '\'' +
                '}';
    }
}
