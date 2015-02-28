package com.example.ricard.khacks;

/**
 * Created by pau on 28/02/15.
 */
public class Hackathon {
    private String name;
    private String location;
    private String date;

    public Hackathon() {};

    public Hackathon(String name, String location, String date) {
        this.name = name;
        this.location = location;
        this.date = date;
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
}
