package com.zash.goco;

public class instantMessage {
     private String  message;
     private String author;

    public instantMessage(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public instantMessage() {
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}
