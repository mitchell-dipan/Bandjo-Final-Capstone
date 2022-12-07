package com.techelevator.controller;


import com.techelevator.dao.MessageDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Message;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class BandjoController {

    private MessageDao messageDao;
    private UserDao userDao;

    public BandjoController(MessageDao messageDao, UserDao userDao){
        this.messageDao = messageDao;
        this.userDao = userDao;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/messages")
    public List<Message> getMessages(Principal principal){

        User user = userDao.findByUsername(principal.getName());
        return messageDao.findAllMessages(user.getId());
    }

}
