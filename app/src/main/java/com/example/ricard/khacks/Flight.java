package com.example.ricard.khacks;

public class Flight {
    private String name;
    private String price;
    private String date;
    private String company;

    public Flight() {}

    public Flight(String name, String price, String date, String company) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
