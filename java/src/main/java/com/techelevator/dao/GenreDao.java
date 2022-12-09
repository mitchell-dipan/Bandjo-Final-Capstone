package com.techelevator.dao;

import com.techelevator.model.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> findAllGenres();

    Genre getGenreById(int genreId);

    Genre getGenreByGenreName(String genreName);

    List<Genre> getGenreByBandId(int id);

    void addGenreToBand(String genreName, int band_id);

}
