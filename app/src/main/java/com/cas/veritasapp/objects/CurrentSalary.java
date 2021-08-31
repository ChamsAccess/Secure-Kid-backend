package com.cas.veritasapp.objects;

import java.io.Serializable;

public class CurrentSalary implements Serializable {
    public String salary_structure;
    public String current_gl;
    public String current_step;

    public String getSalary_structure() {
        return salary_structure;
    }

    public void setSalary_structure(String salary_structure) {
        this.salary_structure = salary_structure;
    }

    public String getCurrent_gl() {
        return current_gl;
    }

    public void setCurrent_gl(String current_gl) {
        this.current_gl = current_gl;
    }

    public String getCurrent_step() {
        return current_step;
    }

    public void setCurrent_step(String current_step) {
        this.current_step = current_step;
    }

    @Override
    public String toString() {
        return "CurrentSalary{" +
                "salary_structure='" + salary_structure + '\'' +
                ", current_gl='" + current_gl + '\'' +
                ", current_step='" + current_step + '\'' +
                '}';
    }
}
