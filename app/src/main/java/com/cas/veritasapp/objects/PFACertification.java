package com.cas.veritasapp.objects;

import java.io.File;
import java.io.Serializable;

public class PFACertification implements Serializable {
    public String pfa_code;
    public Media signature;
    public String signatureUrl;
    public String user;
    public String enrolled_dated;


    public String getSignatureUrl() {
        return signatureUrl;
    }

    public void setSignatureUrl(String signatureUrl) {
        this.signatureUrl = signatureUrl;
    }

    public String getPfa_code() {
        return pfa_code;
    }

    public void setPfa_code(String pfa_code) {
        this.pfa_code = pfa_code;
    }

    public Media getSignature() {
        return signature;
    }

    public void setSignature(Media signature) {
        this.signature = signature;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEnrolled_dated() {
        return enrolled_dated;
    }

    public void setEnrolled_dated(String enrolled_dated) {
        this.enrolled_dated = enrolled_dated;
    }

    @Override
    public String toString() {
        return "PFACertification{" +
                "pfa_code='" + pfa_code + '\'' +
                ", signature='" + signature + '\'' +
                ", user='" + user + '\'' +
                ", enrolled_dated='" + enrolled_dated + '\'' +
                '}';
    }
}
