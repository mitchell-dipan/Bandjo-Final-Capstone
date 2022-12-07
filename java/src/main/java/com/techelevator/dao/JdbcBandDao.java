package com.techelevator.dao;

import com.techelevator.model.Band;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBandDao implements BandDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcBandDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Band> getAllBands() {
        String sql = "SELECT name, description, members, user_id, band_id " +
                "FROM bands;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        List<Band> bands = new ArrayList<>();
        while(results.next()){
            Band band = new Band(results.getString("name"),results.getString("description"),
                    results.getString("members"), results.getInt("user_id"), results.getInt("band_id"));
            bands.add(band);
        }
        return bands;
    }
}
