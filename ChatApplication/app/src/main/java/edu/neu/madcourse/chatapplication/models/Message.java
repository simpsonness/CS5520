package edu.neu.madcourse.chatapplication.models;

public class Message {
    public String fromUsername;
    public String sticker;
    public String time;

    public Message() {

    }

    public Message(String fu, String s, String t) {
        this.fromUsername = fu;
        this.sticker = s;
        this.time = t;
    }
}
