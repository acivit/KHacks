package com.example.ricard.khacks;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

    private String translateMonth(String text) {
        String res = null;
        switch (text) {
            case "January": res =  "01";
            case "February": res =  "02";
            case "March": res = "03";
            case "April": res = "04";
            case "May": res = "05";
            case "June": res = "06";
            case "July": res = "07";
            case "August": res = "08";
            case "September": res = "09";
            case "October": res = "10";
            case "November": res = "11";
            case "December": res = "12";
            default: break;
        }
        return res;
    }

    public String setDates(String date) {
        // February 28th - March 1st
        // March 7th - 8th
        Calendar out = null;
        String month = "";
        String day = "";
        String part1; // 004
        String part2; // 034556

        if (date.contains("-")) {
            String string = "004-034556";
            String[] parts = string.split("-");
            part1 = parts[0];
            part2 = parts[1];
        } else {
            throw new IllegalArgumentException("String " + date + " is a non-supported date format");
        }

        int i;
        for (i = 0; i < part1.length(); i++) {
            if (part1.charAt(i) == ' ') break;
            else {
                month += part1.charAt(i);
            }
        }
        while(part1.charAt(i) >= '0' && part1.charAt(i) <= 9) {
            day += part1.charAt(i);
            ++i;
        }
        month = translateMonth(month);
        if (day.length() < 2) day = "0"+day;
        Log.wtf("month", month);
        Log.wtf("day", day);
        //out.set(2015, monthOfYear, dayOfMonth);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //if not null fer :String formattedDate = sdf.format(out.getTime());
        return month;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        // February 28th - March 1st
        this.depDate = depDate;
    }

    private String translation(String ugly) {
        // February 28th - March 1st
        // March 7th - 8th
        return null;
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
