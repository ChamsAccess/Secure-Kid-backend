package com.cas.veritasapp.objects.payloads;

import com.cas.veritasapp.core.data.entities.Personal;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PersonalPayload<T> implements Serializable {
    public String _id;
    public String title;
    @SerializedName(value = "first_name")
    public String firstName;
    public String surname;
    @SerializedName(value = "phone_number")
    public String phoneNumber;
    public String maiden;
    public String email;
    @SerializedName(value = "middle_name")
    public String middleName;
    public String gender;
    @SerializedName(value = "marital_status")
    public String maritalStatus;
    public String nationality;
    public String dob;
    public String bvn;
    public String nin;
    @SerializedName(value = "place_of_birth")
    public String placeOfBirth;
    @SerializedName(value = "state_of_origin_code")
    public T stateOfOriginCode;
    public T country;
    public T state;
    public T lga;
    @SerializedName(value = "house_number")
    public String houseNumber;
    public T location;
    
    public static PersonalPayload create(Personal personal){
        PersonalPayload payload= new PersonalPayload();
        payload._id = personal.getId();
        payload.firstName = personal.getFirstName();
        payload.surname = personal.getSurname();
        payload.phoneNumber = personal.getPhoneNumber();
        payload.maiden = personal.getMaiden();
        payload.email = personal.getEmail();
        payload.middleName = personal.getMiddleName();
        payload.gender = personal.getGender();
        payload.country = personal.getCountry();
        payload.state = personal.getState();
        payload.lga = personal.getLga();
        payload.maritalStatus = personal.getMaritalStatus();
        payload.nationality = personal.getNationality();
        payload.dob = personal.getDob();
        payload.bvn = personal.getBvn();
        payload.nin = personal.getNin();
        payload.placeOfBirth = personal.getPlaceOfBirth();
        payload.stateOfOriginCode = personal.getStateOfOriginCode();
        payload.houseNumber = personal.getHouseNumber();
        payload.location = personal.getLocation();
        return payload;
    }

    @Override
    public String toString() {
        return "PersonalPayload{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", maiden='" + maiden + '\'' +
                ", email='" + email + '\'' +
                ", middleName='" + middleName + '\'' +
                ", gender='" + gender + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", nationality=" + nationality +
                ", dob='" + dob + '\'' +
                ", bvn='" + bvn + '\'' +
                ", nin='" + nin + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", stateOfOriginCode=" + stateOfOriginCode +
                ", country=" + country +
                ", houseNumber='" + houseNumber + '\'' +
                ", location=" + location +
                '}';
    }
}
