package com.cas.veritasapp.objects.api;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by funmiayinde on 2019-10-10.
 */
public class ApiError implements Serializable {

    private int code;
    private String message;
    private Object messages;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getMessages() {
        return messages;
    }

    public void setMessages(Object messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public String toString() {
        return "APIError{" +
                "code" + code +
                ", message'" + message + '\'' +
                ", messages" + messages +
                '}';
    }
}
