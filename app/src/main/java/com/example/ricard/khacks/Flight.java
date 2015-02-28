package com.example.ricard.khacks;

public class Flight {
    private String depLoc;
    private String arrLoc;
    private String price;
    private String depDate;
    private String arrDate;
    private String company;

    public Flight() {}

    public Flight(String depLoc, String arrLoc, String price, String depDate, String retDate, String company) {
        this.depLoc = depLoc;
        this.arrLoc = arrLoc;
        this.price = price;
        this.depDate = depDate;
        this.arrDate = retDate;
        this.company = company;
    }

    public String getDepLoc() {
        return depLoc;
    }

    public void setDepLoc(String depLoc) {
        this.depLoc = depLoc;
    }

    public String getArrLoc() {
        return arrLoc;
    }

    public void setArrLoc(String arrLoc) {
        this.arrLoc = arrLoc;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public String getArrDate() {
        return arrDate;
    }

    public void setArrDate(String arrDate) {
        this.arrDate = arrDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
