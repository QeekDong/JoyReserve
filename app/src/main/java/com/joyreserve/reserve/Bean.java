package com.joyreserve.reserve;

/**
 * Created by Cloud on 2017/4/5.
 */

public class Bean {
    String time;
    String sponsor;
    String theme;
    String people;
    int color;

    public Bean(){}

    public Bean(String time, String sponsor, String theme, String people, int color) {
        this.time = time;
        this.sponsor = sponsor;
        this.theme = theme;
        this.people = people;
        this.color = color;
    }
    public Bean(String time, int color) {
        this.time = time;
        this.color = color;
    }

    public String getTime() {
        return time;
    }

    public int getColor() {
        return color;
    }

    public String getSponsor() {
        return sponsor;
    }

    public String getTheme() {
        return theme;
    }

    public String getPeople() {
        return people;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}
