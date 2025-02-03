package org.example.redsocial.model;

import java.text.DateFormat;

public class Message {

    int messageId;
    String message;

    String fullName;
    String date;


    public Message() {
    }

    public Message(String message, String fullName,String date ) {
        this.message = message;
        this.fullName = fullName;
        this.date = date;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
