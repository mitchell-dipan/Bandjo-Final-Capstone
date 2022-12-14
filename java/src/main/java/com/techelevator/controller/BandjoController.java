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

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/messages")
    public List<Message> getMessages(Principal principal, @RequestParam String sortBy){

        User user = userDao.findByUsername(principal.getName());
        return messageDao.findAllMessages(user.getId(), sortBy);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/bands")
    public List<Band> getBands(){
        return bandDao.getAllBands();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/bands/{id}")
    public Band viewBandPage(@PathVariable int id){
        return bandDao.viewBandPage(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/bands/{id}/pictures")
    public List<Pictures> getPicturesByBandId(@PathVariable int id){
        return picturesDao.getPicturesByBandId(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/bands/{id}/genres")
    public List<Genre> getGenresByBandId(@PathVariable int id){
        return genreDao.getGenreByBandId(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/bands/{id}/shows")
    public List<Show> getShowsByBandId(@PathVariable int id){
        return showDao.getShowsByBandId(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(path = "/create")
    public Integer createNewBandPage(@RequestBody Band band){
         return bandDao.createBand(band);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(path = "/create/{id}/picture")
    public void createNewPicture(@PathVariable int id, @RequestBody Pictures pictures){
            pictures.setBand_id(id);
            picturesDao.createPicture(pictures);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(path = "/create/{id}/show")
    public void createNewShow(@PathVariable int id, @RequestBody Show show){
        show.setBandId(id);
        showDao.addShowForBand(show);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(path = "/create/{id}/genre")
    public void addGenreToBand(@PathVariable int id, @RequestBody Genre genre){
        genreDao.addGenreToBand(genre.getGenreName(), id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/genres")
    public List<Genre> getGenres(){
        return genreDao.findAllGenres();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(path = "/follow")
    public void addFollower(@RequestParam int bandId, Principal principal){
        User user = userDao.findByUsername(principal.getName());
        bandDao.addFollower(bandId, user.getId());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping(path ="/unfollow")
    public void unfollow(@RequestParam int bandId, Principal principal) {
        User user = userDao.findByUsername(principal.getName());
        bandDao.deleteFollower(bandId, user.getId());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path ="/check")
    public boolean checkIfFollow(@RequestParam int bandId, Principal principal) {
        User user = userDao.findByUsername(principal.getName());
        return bandDao.isFollowing(bandId, user.getId());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(path = "/bands/{bandId}/sendMessage")
    public void sendMessage(@PathVariable int bandId, @RequestParam String messages){
        messageDao.sendMessage(messages, bandId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping(path = "/bands/{id}/editProfile")
    public void updateBands(@PathVariable int id, @RequestBody Band band){
        bandDao.updateBand(id,band);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping(path = "/bands/{id}/deleteGenre")
    public void deleteGenres(@PathVariable int id, @RequestParam int genreId){
        genreDao.deleteGenreFromBand(id,genreId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping(path = "/bands/{id}/deletePictures")
    public void deletePictures(@PathVariable int id, @RequestParam int pictureId){
        picturesDao.deletePicturesFromBand(id,pictureId);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping(path = "/bands/{id}/deleteShows")
    public void deleteShows(@PathVariable int id, @RequestParam int showId){
        showDao.deleteShowFromBand(id,showId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/delete-genres")
    public void deleteGenres(@RequestParam String genreName){
        genreDao.deleteGenreFromData(genreName);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/add-genres")
    public void addGenres(@RequestParam String genreName){
        genreDao.addGenreToData(genreName);
    }

}
