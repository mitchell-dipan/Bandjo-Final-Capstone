//package com.techelevator.dao;
//
//import com.techelevator.model.Show;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Component
//public class JdbcShowDao implements ShowDao{
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public JdbcShowDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public List<Show> findShowsByLocation(String showLocation) {
//        return null;
//    }
//
//    @Override
//    public List<Show> findShowsByDate(LocalDate showDate) {
//        return null;
//    }
//
//    @Override
//    public List<Show> findShowsByDateAndLocation(LocalDate showDate, String showLocation) {
//        return null;
//    }
//
//    @Override
//    public Show getShowByShowName(String showName) {
//        return null;
//    }
//
//    @Override
//    public Show getShowById(int showId) {
//        return null;
//    }
//}
