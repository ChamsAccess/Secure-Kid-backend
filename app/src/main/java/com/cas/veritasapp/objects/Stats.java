package com.cas.veritasapp.objects;

import com.google.gson.annotations.SerializedName;

public class Stats {
    @SerializedName(value = "total_pending")
    private int totalPending;
    @SerializedName(value =  "total_completed")
    private int totalCompleted;

    public int getTotalPending() {
        return totalPending;
    }

    public int getTotalCompleted() {
        return totalCompleted;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "total_pending=" + totalPending +
                ", total_completed=" + totalCompleted +
                '}';
    }
}
