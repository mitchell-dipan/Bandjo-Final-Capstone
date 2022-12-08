package com.techelevator.dao;

import com.techelevator.model.Band;

import java.util.List;

public interface BandDao {

    List<Band> getAllBands();

    Band viewBandPage(int id);

    void createBand(Band band);

}
