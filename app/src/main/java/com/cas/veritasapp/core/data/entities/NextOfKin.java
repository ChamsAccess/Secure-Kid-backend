package com.cas.veritasapp.core.data.entities;

import androidx.room.Entity;

import com.cas.veritasapp.objects.payloads.CountryPayload;
import com.cas.veritasapp.objects.payloads.LocationPayload;
import com.cas.veritasapp.objects.payloads.NextOfKinPayload;
import com.cas.veritasapp.util.ServiceUtil;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "next_of_kins")
public class NextOfKin implements Serializable {
    @SerializedName(value = "_id")
    public String _id;
    public String title;
    public String first_name;
    public String surname;
    public String middle_name;
    public String phone_number;
    public String email;
    public String relationship;
    public String country_code;
    public String gender;
    public String nationality;

    public String country;
    public Country countryObject;

    public String house_number;
    public Location location;


    public static NextOfKin create(NextOfKinPayload payload) {
        NextOfKin nextOfKin = new NextOfKin();
        nextOfKin.set_id(payload._id);
        nextOfKin.setTitle(payload.title);
        nextOfKin.setFirst_name(payload.first_name);
        nextOfKin.setSurname(payload.surname);
        nextOfKin.setMiddle_name(payload.middle_name);
        nextOfKin.setPhone_number(payload.phone_number);
        nextOfKin.setEmail(payload.email);
        nextOfKin.setRelationship(payload.relationship);
        nextOfKin.setGender(payload.gender);
        nextOfKin.setCountry_code(payload.country_code);
        if (payload.country != null) {
            if (ServiceUtil.isPrimitive(payload.country)) {
                nextOfKin.setCountry(payload.country.toString());
            } else {
                CountryPayload countryPayload = ServiceUtil.getObjectValue(payload.country, CountryPayload.class);
                nextOfKin.setCountryObject(Country.create(countryPayload));
                nextOfKin.setCountry(countryPayload.id);
            }
        }
        if (payload.location != null) {
            if (!ServiceUtil.isPrimitive(payload.location)) {
                LocationPayload locationPayload = ServiceUtil.getObjectValue(payload.location, LocationPayload.class);
                nextOfKin.setLocation(Location.create(locationPayload));
            }
        }
        return nextOfKin;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getCountry_code() {
        return country_code;
    }

    public Country getCountryObject() {
        return countryObject;
    }

    public void setCountryObject(Country countryObject) {
        this.countryObject = countryObject;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "NextOfKin{" +
                "title='" + title + '\'' +
                ", first_name='" + first_name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", relationship='" + relationship + '\'' +
                ", country_code='" + country_code + '\'' +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", country='" + country + '\'' +
                ", house_number='" + house_number + '\'' +
                ", location=" + location +
                '}';
    }
}
