package com.example.ricard.khacks;

import android.graphics.Bitmap;

/**
 * Created by pau on 28/02/15.
 */
public class Hackathon {
    private String name;
    private String location;
    private String date;
    private Bitmap image;

    public Hackathon() {};

    public Hackathon(String name, String location, String date, Bitmap image) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
