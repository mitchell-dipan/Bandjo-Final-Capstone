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
        return null;
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
    public void addGenreToBand(int genre_id, int band_id) {
        String sql = "INSERT INTO band_genre(genre_id, band_id) VALUES(?,?);";
        jdbcTemplate.update(sql, genre_id, band_id);
    }

    private Genre mapRowToGenre(SqlRowSet rowSet){
        Genre genre = new Genre();
        genre.setGenreId(rowSet.getInt("genre_id"));
        genre.setGenreName(rowSet.getString("genre_name"));
        return genre;
    }
}
