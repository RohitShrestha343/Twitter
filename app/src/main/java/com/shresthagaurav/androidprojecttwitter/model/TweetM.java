package com.shresthagaurav.androidprojecttwitter.model;

public class TweetM {
    String headingtext;
    String messagetext;
    String image;

    public TweetM(String headingtext, String messagetext, String image) {
        this.headingtext = headingtext;
        this.messagetext = messagetext;
        this.image = image;
    }

    public String getHeadingtext() {
        return headingtext;
    }

    public String getMessagetext() {
        return messagetext;
    }

    public String getImage() {
        return image;
    }
}
