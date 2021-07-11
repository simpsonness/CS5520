package edu.neu.madcourse.chatapplication;

public class Token {

    private String token;

    // Constructor with input String.
    public Token(String token) {
        this.token = token;
    }

    // Constructor with no input String.
    public Token(){};

    // Getter for token.
    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
