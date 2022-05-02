package com.zillennium.utswap.screens.systems.notificationScreen;

public class Notification {
    String description;
    int image;
    String time;
    String title;

    public Notification(int i, String str, String str2, String str3) {
        this.image = i;
        this.title = str;
        this.description = str2;
        this.time = str3;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(int i) {
        this.image = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }
}
