package com.cas.veritasapp.objects;

import java.io.Serializable;

public class ContributionBio implements Serializable {
    public String passport;
    public String passportUrl;
    public String signature;
    public String signatureUrl;
    public String signature_date;


    public String getPassportUrl() {
        return passportUrl;
    }

    public void setPassportUrl(String passportUrl) {
        this.passportUrl = passportUrl;
    }

    public String getSignatureUrl() {
        return signatureUrl;
    }

    public void setSignatureUrl(String signatureUrl) {
        this.signatureUrl = signatureUrl;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
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
                "passport='" + passport + '\'' +
                ", signature='" + signature + '\'' +
                ", signature_date='" + signature_date + '\'' +
                '}';
    }
}
