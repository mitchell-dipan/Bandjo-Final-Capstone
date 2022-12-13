package com.techelevator.dao;

import com.techelevator.model.Pictures;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPicturesDao implements PicturesDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPicturesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pictures> getPicturesByBandId(int id) {
        String sql = "SELECT pic_url, band_id, picture_id\n" +
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

    @Override
    public void createPicture(Pictures picture) {
        String sql = "INSERT INTO pictures(band_id, pic_url) VALUES(?,?) RETURNING picture_id;";
        int id = jdbcTemplate.queryForObject(sql, Integer.class,picture.getBand_id(), picture.getPic_url());
    }

    @Override
    public void deletePicturesFromBand(int bandId, int pictureId) {
        String sql = "DELETE FROM pictures WHERE band_id = ? AND picture_id = ?;";
            jdbcTemplate.update(sql,bandId,pictureId);
        }
    }
