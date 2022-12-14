package com.techelevator.model;

public class Band {

    private String name;
    private String description;
    private String members;
    private int user_id;
    private int band_id;
    private String profilePic;

    public Band(String name, String description, String members, int user_id, int band_id, String profilePic){
        this.name = name;
        this.description = description;
        this.members = members;
        this.user_id = user_id;
        this.band_id = band_id;
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMembers() {
        return members;
    }

    public int getUserID() {
        return user_id;
    }

    public int getBandID() {
        return band_id;
    }

    public String getProfilePic(){
        return profilePic;
    }
}
