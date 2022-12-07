package com.techelevator.controller;


import com.techelevator.dao.BandDao;
import com.techelevator.dao.MessageDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Band;
import com.techelevator.model.Message;
import com.techelevator.model.User;
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

    public BandjoController(MessageDao messageDao, UserDao userDao, BandDao bandDao){
        this.messageDao = messageDao;
        this.userDao = userDao;
        this.bandDao = bandDao;
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

}
