package com.cas.veritasapp.objects.api;

import androidx.annotation.NonNull;

/**
 * Created by funmiayinde on 2019-10-10.
 */
public class Pagination {
    private String current_page;
    private int current;
    private int next;
    private int per_page = 10;
    private int total_count;


    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    @NonNull
    @Override
    public String toString() {
        return "Pagination{" +
                "current_page='" + current_page + '\'' +
                ", current=" + current +
                ", next=" + next +
                ", per_page" + per_page +
                ", total_count" + total_count +
                "}";

    }
}
