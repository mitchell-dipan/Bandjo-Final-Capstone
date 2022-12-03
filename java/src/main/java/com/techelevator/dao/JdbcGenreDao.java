package com.techelevator.dao;

import com.techelevator.model.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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

    private Genre mapRowToGenre(SqlRowSet rowSet){
        Genre genre = new Genre();
        genre.setGenreId(rowSet.getInt("genre_id"));
        genre.setGenreName(rowSet.getString("genre_name"));
        return genre;
    }
}
