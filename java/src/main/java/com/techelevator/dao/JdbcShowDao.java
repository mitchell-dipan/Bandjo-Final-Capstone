package com.techelevator.dao;

import com.techelevator.model.Show;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcShowDao implements ShowDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcShowDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Show> findShowsByLocation(String showLocation) {
        return null;
    }

    @Override
    public List<Show> findShowsByDate(LocalDate showDate) {
        return null;
    }

    @Override
    public List<Show> findShowsByDateAndLocation(LocalDate showDate, String showLocation) {
        return null;
    }

    @Override
    public Show getShowByShowName(String showName) {
        return null;
    }

    @Override
    public Show getShowById(int showId) {
        return null;
    }

    @Override
    public List<Show> getShowsByBandId(int id) {
        String sql = "SELECT band_id, show_id, show_name, show_date, show_location\n" +
                "FROM shows\n" +
                "WHERE band_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        List<Show> shows = new ArrayList<>();
        while(results.next()){

            LocalDate localDate = null;
            if(results.getDate("show_date") != null){
                localDate = results.getDate("show_date").toLocalDate();
            }

            Show show = new Show(results.getInt("band_id"), results.getInt("show_id"), results.getString("show_name"),
                   localDate, results.getString("show_location"));
            shows.add(show);
        }
        return shows;
    }
}
