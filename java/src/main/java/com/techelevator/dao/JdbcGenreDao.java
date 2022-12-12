package com.techelevator.dao;

import com.techelevator.model.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGenreDao implements GenreDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcGenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> findAllGenres() {
        String sql = "SELECT genre_id, genre_name FROM genres;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Genre> genres = new ArrayList<>();
        while(results.next()){
            Genre genre = new Genre(results.getInt("genre_id"), results.getString("genre_name") );
            genres.add(genre);
        }
        return genres;
    }

    @Override
    public Genre getGenreById(int genreId) {
        return null;
    }

    @Override
    public Genre getGenreByGenreName(String genreName) {
        return null;
    }

    @Override
    public List<Genre> getGenreByBandId(int id) {
        String sql = "SELECT g.genre_id, genre_name\n" +
                "FROM genres as g\n" +
                "\tJOIN band_genre AS bg ON bg.genre_id = g.genre_id\n" +
                "WHERE bg.band_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        List<Genre> genres = new ArrayList<>();
        while(results.next()){
            Genre genre = mapRowToGenre(results);
            genres.add(genre);
        }
        return genres;
    }

    @Override
    public void addGenreToBand(String genreName, int band_id) {
        String sql = "INSERT INTO band_genre(genre_id, band_id) VALUES((SELECT genre_id FROM genres WHERE genre_name = ?),?);";
        jdbcTemplate.update(sql, genreName, band_id);
    }

    @Override
    public void deleteGenreFromBand(int id, Genre[] genres) {
        String sql = "DELETE FROM band_genre WHERE band_id = ? AND genre_id = (SELECT genre_id FROM genres WHERE genre_name = ?);";
        for(Genre genre : genres){
            jdbcTemplate.update(sql,id,genre.getGenreName());
        }
    }

    private Genre mapRowToGenre(SqlRowSet rowSet){
        Genre genre = new Genre();
        genre.setGenreId(rowSet.getInt("genre_id"));
        genre.setGenreName(rowSet.getString("genre_name"));
        return genre;
    }
}
