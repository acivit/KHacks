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

    private int translateMonth(String text) {
        int res = 0;
        switch (text) {
            case "January":
                res =  1;
                break;
            case "February":
                res =  2;
                break;
            case "March":
                res = 3;
                break;
            case "April":
                res = 4;
                break;
            case "May":
                res = 5;
                break;
            case "June":
                res = 6;
                break;
            case "July":
                res = 7;
                break;
            case "August":
                res = 8;
                break;
            case "September":
                res = 9;
                break;
            case "October":
                res = 10;
                break;
            case "November":
                res = 11;
                break;
            case "December":
                res = 12;
                break;
            default: break;
        }
        return res;
    }

    public void setDates(String date) {
        // February 28th - March 1st
        // March 7th - 8th
        Calendar out = null;
        String month1 = "";
        int month2;
        String day1 = "";
        String day2 = "";
        String part1;
        String part2;

        Log.wtf("hola", "don pepito");

        if (date.contains("-")) {
            String[] parts = date.split("-");
            part1 = parts[0];
            part2 = parts[1];
        } else {
            throw new IllegalArgumentException("String " + date + " is a non-supported date format");
        }

        int i;
        for (i = 0; i < part1.length(); i++) {
            if (part1.charAt(i) == ' ') {
                i++;
                break;
            }
            else {
                month1 += part1.charAt(i);
            }
        }
        Log.i("i", i+"");
        while(i < part1.length() && part1.charAt(i) >= '0' && part1.charAt(i) <= '9') {
            day1 += part1.charAt(i);
            Log.i("char at i", part1.charAt(i)+"");
            ++i;
        }
        int monthInt = translateMonth(month1);
        if (part2.length() <= 5) month2 = monthInt;
        else month2 = monthInt+1;
        Log.wtf("day", day1);
        //out.set(2015, monthOfYear, dayOfMonth);

        Log.i("HOLAPUTA",part2.charAt(part2.length()-1)+"");
        day2 = part2.charAt((part2.length()-4))+"" + part2.charAt((part2.length()-3))+"";
        //if (day2.charAt(0) == ' ') day2 = day2.charAt(1)+"";
        Log.wtf("dia2", day2);

        Calendar c1 = Calendar.getInstance();
        c1.set(2015, monthInt-1, Integer.valueOf(day1));
        c1.roll(Calendar.DAY_OF_MONTH, -1);

        Log.wtf("month", monthInt+"");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate1 = sdf1.format(c1.getTime());

        Calendar c2 = Calendar.getInstance();
        c2.set(2015, month2-1, Integer.valueOf(day2));
        c2.roll(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate2 = sdf2.format(c2.getTime());

        Log.i("formated", formattedDate1);
        Log.i("formated", formattedDate2);
        setDepDate(formattedDate1);
        setArrDate(formattedDate2);
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
