package com.cas.veritasapp.core.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.cas.veritasapp.objects.payloads.CountryPayload;
import com.cas.veritasapp.objects.payloads.LGAPayload;
import com.cas.veritasapp.objects.payloads.LocationPayload;
import com.cas.veritasapp.objects.payloads.PersonalPayload;
import com.cas.veritasapp.objects.payloads.StatePayload;
import com.cas.veritasapp.util.ServiceUtil;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "personals")
public class Personal implements Serializable {
    @SerializedName(value = "_id")
    private String id;
    private String title;
    @SerializedName(value = "first_name")
    private String firstName;
    private String surname;
    @SerializedName(value = "phone_number")
    private String phoneNumber;
    private String maiden;
    private String email;
    @SerializedName(value = "middle_name")
    private String middleName;
    private String gender;
    @SerializedName(value = "marital_status")
    private String maritalStatus;
    private String nationality;
    private String dob;
    private String bvn;
    private String nin;

    @SerializedName(value = "state")
    private String state;
    private State stateObject;

    @SerializedName(value = "lga")
    private String lga;
    private LGA lgaObject;

    @SerializedName(value = "place_of_birth")
    private String placeOfBirth;

    @SerializedName(value = "state_of_origin_code")
    private String stateOfOriginCode;
    private State stateOfOriginObject;

    private String country;
    private Country countryObject;

    @SerializedName(value = "house_number")
    private String houseNumber;

    private Location location;


    public static Personal create(PersonalPayload payload) {
        Personal personal = new Personal();
        personal.setId(payload._id);
        personal.setTitle(payload.title);
        personal.setFirstName(payload.firstName);
        personal.setSurname(payload.surname);
        personal.setPhoneNumber(payload.phoneNumber);
        personal.setMaiden(payload.maiden);
        personal.setEmail(payload.email);
        personal.setMiddleName(payload.middleName);
        personal.setMaritalStatus(payload.maritalStatus);
        personal.setNationality(payload.nationality);
        personal.setDob(payload.dob);
        personal.setBvn(payload.bvn);
        personal.setNin(payload.nin);
        personal.setGender(payload.gender);
        personal.setHouseNumber(payload.houseNumber);
        personal.setPlaceOfBirth(payload.placeOfBirth);

        if (payload.stateOfOriginCode != null) {
            if (ServiceUtil.isPrimitive(payload.stateOfOriginCode)) {
                personal.setStateOfOriginCode(payload.stateOfOriginCode.toString());
            } else {
                StatePayload statePayload = ServiceUtil.getObjectValue(payload.stateOfOriginCode, StatePayload.class);
                personal.setStateOfOriginObject(State.create(statePayload));
                personal.setStateOfOriginCode(statePayload.id);
            }
        }
        if (payload.state != null) {
            if (ServiceUtil.isPrimitive(payload.state)) {
                personal.setState(payload.state.toString());
            } else {
                StatePayload statePayload = ServiceUtil.getObjectValue(payload.state, StatePayload.class);
                personal.setStateObject(State.create(statePayload));
                personal.setState(statePayload.id);
            }
        }
        if (payload.lga != null) {
            if (ServiceUtil.isPrimitive(payload.lga)) {
                personal.setLga(payload.lga.toString());
            } else {
                LGAPayload lgaPayload = ServiceUtil.getObjectValue(payload.lga, LGAPayload.class);
                personal.setLgaObject(LGA.create(lgaPayload));
                personal.setLga(lgaPayload.id);
            }
        }
        if (payload.country != null) {
            if (ServiceUtil.isPrimitive(payload.country)) {
                personal.setCountry(payload.country.toString());
            } else {
                CountryPayload countryPayload = ServiceUtil.getObjectValue(payload.country, CountryPayload.class);
                personal.setCountryObject(Country.create(countryPayload));
                personal.setCountry(countryPayload.id);
            }
        }
        if (payload.location != null) {
            if (!ServiceUtil.isPrimitive(payload.location)) {
                LocationPayload locationPayload = ServiceUtil.getObjectValue(payload.location, LocationPayload.class);
                personal.setLocation(Location.create(locationPayload));
            }
        }
        return personal;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public State getStateObject() {
        return stateObject;
    }

    public void setStateObject(State stateObject) {
        this.stateObject = stateObject;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public LGA getLgaObject() {
        return lgaObject;
    }

    public void setLgaObject(LGA lgaObject) {
        this.lgaObject = lgaObject;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public State getStateOfOriginObject() {
        return stateOfOriginObject;
    }

    public void setStateOfOriginObject(State stateOfOriginObject) {
        this.stateOfOriginObject = stateOfOriginObject;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMaiden() {
        return maiden;
    }

    public void setMaiden(String maiden) {
        this.maiden = maiden;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getStateOfOriginCode() {
        return stateOfOriginCode;
    }

    public void setStateOfOriginCode(String stateOfOriginCode) {
        this.stateOfOriginCode = stateOfOriginCode;
    }

    public Country getCountryObject() {
        return countryObject;
    }

    public void setCountryObject(Country countryObject) {
        this.countryObject = countryObject;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

//    @Override
//    public String toString() {
//        return "Personal{" +
//                "title='" + title + '\'' +
//                ", firstName='" + firstName + '\'' +
//                ", surname='" + surname + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", email='" + email + '\'' +
//                ", middleName='" + middleName + '\'' +
//                ", gender='" + gender + '\'' +
//                ", maritalStatus='" + maritalStatus + '\'' +
//                ", nationality='" + nationality + '\'' +
//                ", dob=" + dob +
//                ", bvn='" + bvn + '\'' +
//                ", nin='" + nin + '\'' +
//                ", placeOfBirth='" + placeOfBirth + '\'' +
//                ", stateOfOriginCode='" + stateOfOriginCode + '\'' +
//                ", country='" + country + '\'' +
//                ", houseNumber='" + houseNumber + '\'' +
//                ", location=" + location.toString() +
//                '}';
//    }
}
