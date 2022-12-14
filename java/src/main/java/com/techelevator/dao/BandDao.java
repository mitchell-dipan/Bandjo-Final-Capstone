package com.techelevator.dao;

import com.techelevator.model.Band;

import java.util.List;

public interface BandDao {

    List<Band> getAllBands();

    Band viewBandPage(int id);

    Integer createBand(Band band);

    void addFollower(int band_id, int user_id);

    void deleteFollower(int band_id, int user_id);

    boolean isFollowing(int band_id, int user_id);

    void updateBand(int id, Band band);
}
