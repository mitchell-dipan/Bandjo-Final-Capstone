package com.techelevator.dao;

import com.techelevator.model.Pictures;

import java.util.List;

public interface PicturesDao {

    List<Pictures> getPicturesByBandId(int id);

    void createPicture(Pictures picture);

    void deletePicturesFromBand(int bandId, int pictureId);
}
