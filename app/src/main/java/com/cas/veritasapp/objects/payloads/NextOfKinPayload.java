package com.cas.veritasapp.objects.payloads;

import com.cas.veritasapp.core.data.entities.NextOfKin;

import java.io.Serializable;

public class NextOfKinPayload<T> implements Serializable {
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
    public T country;
    public String house_number;
    public T location;

    public static NextOfKinPayload create(NextOfKin nextOfKin){
        NextOfKinPayload payload = new NextOfKinPayload();
        payload._id = nextOfKin.get_id();
        payload.title = nextOfKin.getTitle();
        payload.first_name = nextOfKin.getFirst_name();
        payload.surname = nextOfKin.getSurname();
        payload.middle_name = nextOfKin.getMiddle_name();
        payload.phone_number = nextOfKin.getPhone_number();
        payload.email = nextOfKin.getEmail();
        payload.relationship = nextOfKin.getRelationship();
        payload.country_code = nextOfKin.getCountry_code();
        payload.gender = nextOfKin.getGender();
        payload.nationality = nextOfKin.getNationality();
        payload.country = nextOfKin.getCountry();
        payload.house_number = nextOfKin.getHouse_number();
        payload.location = nextOfKin.getLocation();
        return payload;
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
