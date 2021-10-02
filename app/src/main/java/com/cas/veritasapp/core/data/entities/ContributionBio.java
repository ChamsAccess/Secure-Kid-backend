package com.cas.veritasapp.core.data.entities;

import com.cas.veritasapp.objects.Media;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ContributionBio implements Serializable {
    @SerializedName(value = "_id")
    private String _id;
    private Media passport;
    private Media signature;
    private String signature_date;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Media getPassport() {
        return passport;
    }

    public void setPassport(Media passport) {
        this.passport = passport;
    }

    public Media getSignature() {
        return signature;
    }

    public void setSignature(Media signature) {
        this.signature = signature;
    }

    public String getSignature_date() {
        return signature_date;
    }

    public void setSignature_date(String signature_date) {
        this.signature_date = signature_date;
    }

    @Override
    public String toString() {
        return "ContributionBio{" +
                "passport=" + passport +
                ", signature=" + signature +
                ", signature_date='" + signature_date + '\'' +
                '}';
    }
}
