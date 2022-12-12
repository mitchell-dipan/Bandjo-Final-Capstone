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
        String sql = "SELECT name, description, members, user_id, band_id, profile_pic " +
                "FROM bands;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        List<Band> bands = new ArrayList<>();
        while(results.next()){
            Band band = new Band(results.getString("name"),results.getString("members"),
                    results.getString("description"), results.getInt("user_id"), results.getInt("band_id"),
                    results.getString("profile_pic"));
            bands.add(band);
        }
        return bands;
    }

    @Override
    public Band viewBandPage(int id) {
        String sql = "SELECT name, description, members, user_id, band_id, profile_pic from bands WHERE band_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()){
            Band band = new Band(results.getString("name"),results.getString("members"),
                    results.getString("description"), results.getInt("user_id"), results.getInt("band_id"),
                    results.getString("profile_pic"));
            return band;
        }

        return null;
    }

    @Override
    public Integer createBand(Band band) {
        String sql = "INSERT INTO bands(name, description, members, user_id, profile_pic)\n" +
                "VALUES(?,?,?,?,?) RETURNING band_id;";

        Integer newId = jdbcTemplate.queryForObject(sql,Integer.class, band.getName(), band.getDescription(), band.getMembers(), band.getUserID(), band.getProfilePic());

        return newId;
    }

    @Override
    public void addFollower(int band_id, int user_id) {
        String sql = "INSERT INTO followers(band_id, user_id) VALUES(?,?);";
        jdbcTemplate.update(sql, band_id, user_id);
    }

    @Override
    public void deleteFollower(int band_id, int user_id) {
        String sql ="DELETE FROM followers WHERE band_id = ? AND user_id = ?;";
        jdbcTemplate.update(sql, band_id, user_id);
    }

    @Override
    public boolean isFollowing(int band_id, int user_id) {
        String sql = "SELECT user_id FROM followers WHERE band_id = ? AND user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,band_id,user_id);
        if(results.next()){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public void updateBand(int id, Band band) {
        String sql = "UPDATE bands\n" +
                "SET name = ?, description = ?, members = ?, profile_pic = ?\n" +
                "WHERE band_id = ?;";
        jdbcTemplate.update(sql, band.getName(), band.getDescription(), band.getMembers(), band.getProfilePic(), id);
    }

}
