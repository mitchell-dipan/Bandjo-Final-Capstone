package com.techelevator.model;

import java.time.LocalDate;

public class Message {


//    Instance variables

    private LocalDate messageDate;
    private String messageContent;
    private int bandId;
    private int messageId;
    private String name;

//    Constructor


    public Message(LocalDate messageDate, String messageContent, int bandId, int messageId, String name) {
        this.messageDate = messageDate;
        this.messageContent = messageContent;
        this.bandId = bandId;
        this.messageId = messageId;
        this.name = name;
    }

//    Getters

    public String getName(){
        return this.name;
    }

    public LocalDate getMessageDate() {
        return messageDate;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public int getBandId() {
        return bandId;
    }

    public int getMessageId() {
        return messageId;
    }
}
