package com.cas.veritasapp.objects.payloads;

import com.cas.veritasapp.objects.Employment;
import com.cas.veritasapp.objects.Enrollment;
import com.cas.veritasapp.objects.Location;
import com.cas.veritasapp.objects.NextOfKin;
import com.cas.veritasapp.objects.Personal;

import java.io.Serializable;

public class NinPayload implements Serializable {

    public String birthcountry;
    public String birthdate;
    public String centralID;
    public String firstname;
    public String gender;
    public String email;
    public String middlename;
    public String nin;
    public String photo;
    public String pmiddlename;
    public String residence_AdressLine1;
    public String residence_Town;
    public String residence_lga;
    public String residence_state;
    public String self_origin_state;
    public String signature;
    public String surname;
    public String telephoneno;
    public String trackingId;
    public String title;
    public String birthstate;

     public String nok_address1;
     public String nok_firstname;
     public String nok_lga;
     public String nok_state;
     public String nok_surname;
     public String nok_town;



    public static Enrollment getNINPayload(NinPayload payload){
        Enrollment enrollment = new Enrollment();
        Employment employment = new Employment();
        employment.setCountry(payload.birthcountry);
        enrollment.setPhoto(payload.photo);
        enrollment.setSignature(payload.signature);
        Personal personal =  new Personal();
        personal.setTitle(payload.title.equals("miss") ? "ms" : "mrs");
        personal.setFirstName(payload.firstname);
        personal.setDob(payload.birthdate);
        personal.setPlaceOfBirth(payload.birthstate);
        personal.setMiddleName(payload.middlename);
        personal.setSurname(payload.surname);
        personal.setEmail(payload.email);
        personal.setGender((payload.gender.equals("f")) ? "Female" : "Male");
        personal.setNin(payload.nin);
        personal.setNationality(payload.birthcountry);
        personal.setStateOfOriginCode(payload.self_origin_state);
        personal.setPhoneNumber(payload.telephoneno);
        Location location = new Location();
        location.setCity(payload.residence_state);
        location.setLga_code(payload.residence_lga);
        location.setStreet(payload.residence_AdressLine1);
        personal.setStateOfOriginCode(payload.self_origin_state);
        personal.setLocation(location);

        NextOfKin nextOfKin = new NextOfKin();
        nextOfKin.setFirst_name(payload.nok_firstname);
        nextOfKin.setSurname(payload.nok_surname);

        Location nextOfKinLocation =  new Location();
        nextOfKinLocation.setCity(payload.nok_town);
        nextOfKinLocation.setLga_code(payload.nok_lga);
        nextOfKinLocation.setStateCode(payload.nok_state);
        nextOfKinLocation.setStreet(payload.nok_address1);
        nextOfKin.setLocation(nextOfKinLocation);

        enrollment.setPersonalObject(personal);
        enrollment.setNextOfKinObject(nextOfKin);
        return enrollment;
    }

    public String getBirthcountry() {
        return birthcountry;
    }

    public void setBirthcountry(String birthcountry) {
        this.birthcountry = birthcountry;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCentralID() {
        return centralID;
    }

    public void setCentralID(String centralID) {
        this.centralID = centralID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPmiddlename() {
        return pmiddlename;
    }

    public void setPmiddlename(String pmiddlename) {
        this.pmiddlename = pmiddlename;
    }

    public String getResidence_AdressLine1() {
        return residence_AdressLine1;
    }

    public void setResidence_AdressLine1(String residence_AdressLine1) {
        this.residence_AdressLine1 = residence_AdressLine1;
    }

    public String getResidence_Town() {
        return residence_Town;
    }

    public void setResidence_Town(String residence_Town) {
        this.residence_Town = residence_Town;
    }

    public String getResidence_lga() {
        return residence_lga;
    }

    public void setResidence_lga(String residence_lga) {
        this.residence_lga = residence_lga;
    }

    public String getResidence_state() {
        return residence_state;
    }

    public void setResidence_state(String residence_state) {
        this.residence_state = residence_state;
    }

    public String getSelf_origin_state() {
        return self_origin_state;
    }

    public void setSelf_origin_state(String self_origin_state) {
        this.self_origin_state = self_origin_state;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephoneno() {
        return telephoneno;
    }

    public void setTelephoneno(String telephoneno) {
        this.telephoneno = telephoneno;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String toString() {
        return "NinPayload{" +
                "birthcountry='" + birthcountry + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", centralID='" + centralID + '\'' +
                ", firstname='" + firstname + '\'' +
                ", gender='" + gender + '\'' +
                ", middlename='" + middlename + '\'' +
                ", nin='" + nin + '\'' +
                ", photo='" + photo + '\'' +
                ", pmiddlename='" + pmiddlename + '\'' +
                ", residence_AdressLine1='" + residence_AdressLine1 + '\'' +
                ", residence_Town='" + residence_Town + '\'' +
                ", residence_lga='" + residence_lga + '\'' +
                ", residence_state='" + residence_state + '\'' +
                ", self_origin_state='" + self_origin_state + '\'' +
                ", signature='" + signature + '\'' +
                ", surname='" + surname + '\'' +
                ", telephoneno='" + telephoneno + '\'' +
                ", trackingId='" + trackingId + '\'' +
                '}';
    }
}
