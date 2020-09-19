package com.example.pjmanagement;

public class ChattingItem {

    String Name;
    String message;
    String time;
    String pofileUrl;

    public ChattingItem(String name, String message, String time, String pofileUrl) {
        this.name = name;
        this.message = massage;
        this.time = tiem;
        this.pofileUrl = pofileUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

     public void setTime(String time) {
        this.time = time;
     }

    public String getPofileUrl() {
        return pofileUrl;
    }

    public void setPofileUrl(String pofileUrl) {
        this.pofileUrl = pofileUrl;
    }
}