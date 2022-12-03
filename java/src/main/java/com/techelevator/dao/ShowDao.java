package com.techelevator.dao;

import com.techelevator.model.Show;

import java.time.LocalDate;
import java.util.List;

public interface ShowDao {

    List<Show> findShowsByLocation(String showLocation);

    List<Show> findShowsByDate(LocalDate showDate);

    List<Show> findShowsByDateAndLocation(LocalDate showDate, String showLocation);

    Show getShowByShowName(String showName);

    Show getShowById(int showId);

    List<Show> findShowsByBandName(String bandName);
}
