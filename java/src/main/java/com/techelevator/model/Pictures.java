package com.techelevator.model;

public class Pictures {

    private String pic_url;
    private int band_id;
    private int picture_id;

    public Pictures(String pic_url, int band_id, int picture_id) {
        this.pic_url = pic_url;
        this.band_id = band_id;
        this.picture_id = picture_id;
    }

    public void setBand_id(int id){
        this.band_id = id;
    }

    public String getPic_url() {
        return pic_url;
    }

    public int getBand_id() {
        return band_id;
    }

    public int getPicture_id() {
        return picture_id;
    }
}
