package com.cas.veritasapp.objects;

import java.io.Serializable;

public class ContributionBio implements Serializable {
    private Media passport;
    private Media signature;
    private String signature_date;


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
