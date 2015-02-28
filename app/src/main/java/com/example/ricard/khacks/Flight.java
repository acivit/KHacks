package com.example.ricard.khacks;

public class Flight {
    private String name;
    private String prize;
    private String date;
    private String company;

    public Flight() {}

    public Flight(String name, String prize, String date, String company) {
        this.name = name;
        this.prize = prize;
        this.date = date;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
