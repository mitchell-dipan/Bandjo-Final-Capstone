package com.techelevator.controller;


import com.techelevator.dao.*;
import com.techelevator.model.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class BandjoController {

    private MessageDao messageDao;
    private UserDao userDao;
    private BandDao bandDao;
    private PicturesDao picturesDao;
    private GenreDao genreDao;
    private ShowDao showDao;

    public BandjoController(MessageDao messageDao, UserDao userDao, BandDao bandDao, PicturesDao picturesDao, GenreDao genreDao, ShowDao showDao){
        this.messageDao = messageDao;
        this.userDao = userDao;
        this.bandDao = bandDao;
        this.picturesDao = picturesDao;
        this.genreDao = genreDao;
        this.showDao = showDao;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/messages")
    public List<Message> getMessages(Principal principal){

        User user = userDao.findByUsername(principal.getName());
        return messageDao.findAllMessages(user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/bands")
    public List<Band> getBands(){
        return bandDao.getAllBands();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/bands/{id}")
    public Band viewBandPage(@PathVariable int id){
        return bandDao.viewBandPage(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/bands/{id}/pictures")
    public List<Pictures> getPicturesByBandId(@PathVariable int id){
        return picturesDao.getPicturesByBandId(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/bands/{id}/genres")
    public List<Genre> getGenresByBandId(@PathVariable int id){
        return genreDao.getGenreByBandId(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/bands/{id}/shows")
    public List<Show> getShowsByBandId(@PathVariable int id){
        return showDao.getShowsByBandId(id);
    }
}
