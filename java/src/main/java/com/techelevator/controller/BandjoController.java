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
    public List<Message> getMessages(Principal principal, @RequestParam String sortBy){

        User user = userDao.findByUsername(principal.getName());
        return messageDao.findAllMessages(user.getId(), sortBy);
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

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/create")
    public Integer createNewBandPage(@RequestBody Band band){
         return bandDao.createBand(band);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/create/{id}/picture")
    public void createNewPicture(@PathVariable int id, @RequestBody Pictures pictures){
            pictures.setBand_id(id);
            picturesDao.createPicture(pictures);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/create/{id}/show")
    public void createNewShow(@PathVariable int id, @RequestBody Show show){
        show.setBandId(id);
        showDao.addShowForBand(show);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/create/{id}/genre")
    public void addGenreToBand(@PathVariable int id, @RequestBody Genre genre){
        genreDao.addGenreToBand(genre.getGenreName(), id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/genres")
    public List<Genre> getGenres(){
        return genreDao.findAllGenres();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/follow")
    public void addFollower(@RequestParam int bandId, Principal principal){
        User user = userDao.findByUsername(principal.getName());
        bandDao.addFollower(bandId, user.getId());
    }
    
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(path ="/unfollow")
    public void unfollow(@RequestParam int bandId, Principal principal) {
        User user = userDao.findByUsername(principal.getName());
        bandDao.deleteFollower(bandId, user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path ="/check")
    public boolean checkIfFollow(@RequestParam int bandId, Principal principal) {
        User user = userDao.findByUsername(principal.getName());
        return bandDao.isFollowing(bandId, user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/bands/{bandId}/sendMessage")
    public void sendMessage(@PathVariable int bandId, @RequestParam String messages){
        messageDao.sendMessage(messages, bandId);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping(path = "/bands/{id}/updateband")
    public void updateBands(@PathVariable int id, @RequestBody Band band){
        bandDao.updateBand(band);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(path = "/bands/{id}/deletegenres")
    public void deleteGenres(@PathVariable int id, @RequestBody Genre[] genres){
        genreDao.deleteGenreFromBand(id,genres);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(path = "/bands/{id}/deletepictures")
    public void deleteGenres(@PathVariable int id, @RequestBody Pictures[] pictures){
        picturesDao.deletePicturesFromBand(id,pictures);
    }

}
