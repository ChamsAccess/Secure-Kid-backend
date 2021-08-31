package com.cas.veritasapp.objects;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Media implements Serializable {
    public String _id;
    public String url;
    public String key;
    public String mime_type;
    public Media file;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public Media getFile() {
        return file;
    }

    public void setFile(Media file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Media{" +
                "_id='" + _id + '\'' +
                ", url='" + url + '\'' +
                ", key='" + key + '\'' +
                ", mime_type='" + mime_type + '\'' +
                ", file=" + file +
                '}';
    }
}
