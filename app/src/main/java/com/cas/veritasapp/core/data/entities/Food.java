package com.cas.veritasapp.core.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import com.cas.veritasapp.objects.Category;
import com.cas.veritasapp.objects.Vendor;

import java.io.Serializable;

/**
 * Created by funmiayinde on 2019-10-14.
 */
@Entity(tableName = "foods")
public class Food implements Serializable {

    private static final String TAG = Food.class.getSimpleName();

    @PrimaryKey
    @NonNull
    private String _id;

    private boolean status;
    private boolean is_sold_out;
    private boolean approve_type;
    private int amount;

    private Category category;

    private String section;
    private String image;
    private String name;

    private Vendor vendor;


    @NonNull
    public String get_id() {
        return _id;
    }

    public void set_id(@NonNull String _id) {
        this._id = _id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isIs_sold_out() {
        return is_sold_out;
    }

    public void setIs_sold_out(boolean is_sold_out) {
        this.is_sold_out = is_sold_out;
    }

    public boolean isApprove_type() {
        return approve_type;
    }

    public void setApprove_type(boolean approve_type) {
        this.approve_type = approve_type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @NonNull
    @Override
    public String toString() {
        return "Food {" +
                "_id=" + _id + '\'' +
                "status=" + status + '\'' +
                "is_sold_out=" + is_sold_out + '\'' +
                "approve_type=" + approve_type + '\'' +
                "amount=" + amount + '\'' +
                "category=" + category + '\'' +
                "section=" + section + '\'' +
                "name=" + section + '\'' +
                "vendor=" + section + '\'' +
                "}";

    }
}
