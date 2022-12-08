package com.techelevator.dao;

import com.techelevator.model.Pictures;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcPicturesDao implements PicturesDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPicturesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pictures> getPicturesByBandId(int id) {
        String sql = "SELECT pic_url\n" +
                "from pictures\n" +
                "WHERE band_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        List<Pictures> pictures = new ArrayList<>();
        while(results.next()){
            Pictures picture = new Pictures(results.getString("pic_url"), results.getInt("band_id"), results.getInt("picture_id"));
            pictures.add(picture);
        }

        return pictures;
    }
}
