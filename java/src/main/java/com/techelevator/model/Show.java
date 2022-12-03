package com.techelevator.model;

import java.time.LocalDate;

public class Show {

//    Variables for Show
    private int bandId;
    private int showId;
    private String showName;
    private LocalDate showDate;
    private String showLocation;
    private String bandName;

//    Constructors
    public Show(){}

    public Show(int bandId, int showId, String showName, LocalDate showDate, String showLocation, String bandName) {
        this.bandId = bandId;
        this.showId = showId;
        this.showName = showName;
        this.showDate = showDate;
        this.showLocation = showLocation;
        this.bandName = bandName;
    }

//    Getters/Setters


    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public String getShowLocation() {
        return showLocation;
    }

    public void setShowLocation(String showLocation) {
        this.showLocation = showLocation;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }
}
