package com.cas.veritasapp.objects;

import java.io.Serializable;

public class Salary implements Serializable {

    public HarmonizedSalary harmonized_salary_2004;
    public ConsolidatedSalary consolidated_salary_2010;
    public EnhConsolidatedSalary2010 enh_consolidated_salary_2010;
    public EnhConsolidatedSalary2013 enh_consolidated_salary_2013;
    public EnhConsolidatedSalary2016 enh_consolidated_salary_2016;
    public CurrentSalary current_salary;

    public HarmonizedSalary getHarmonized_salary_2004() {
        return harmonized_salary_2004;
    }

    public void setHarmonized_salary_2004(HarmonizedSalary harmonized_salary_2004) {
        this.harmonized_salary_2004 = harmonized_salary_2004;
    }

    public ConsolidatedSalary getConsolidated_salary_2010() {
        return consolidated_salary_2010;
    }

    public void setConsolidated_salary_2010(ConsolidatedSalary consolidated_salary_2010) {
        this.consolidated_salary_2010 = consolidated_salary_2010;
    }

    public EnhConsolidatedSalary2010 getEnh_consolidated_salary_2010() {
        return enh_consolidated_salary_2010;
    }

    public void setEnh_consolidated_salary_2010(EnhConsolidatedSalary2010 enh_consolidated_salary_2010) {
        this.enh_consolidated_salary_2010 = enh_consolidated_salary_2010;
    }

    public EnhConsolidatedSalary2013 getEnh_consolidated_salary_2013() {
        return enh_consolidated_salary_2013;
    }

    public void setEnh_consolidated_salary_2013(EnhConsolidatedSalary2013 enh_consolidated_salary_2013) {
        this.enh_consolidated_salary_2013 = enh_consolidated_salary_2013;
    }

    public EnhConsolidatedSalary2016 getEnh_consolidated_salary_2016() {
        return enh_consolidated_salary_2016;
    }

    public void setEnh_consolidated_salary_2016(EnhConsolidatedSalary2016 enh_consolidated_salary_2016) {
        this.enh_consolidated_salary_2016 = enh_consolidated_salary_2016;
    }

    public CurrentSalary getCurrent_salary() {
        return current_salary;
    }

    public void setCurrent_salary(CurrentSalary current_salary) {
        this.current_salary = current_salary;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "harmonized_salary_2004=" + harmonized_salary_2004.toString() +
                ", consolidated_salary_2010=" + consolidated_salary_2010.toString() +
                ", enh_consolidated_salary_2010=" + enh_consolidated_salary_2010.toString() +
                ", enh_consolidated_salary_2013=" + enh_consolidated_salary_2013.toString() +
                ", enh_consolidated_salary_2016=" + enh_consolidated_salary_2016.toString() +
                ", current_salary=" + current_salary.toString() +
                '}';
    }
}
