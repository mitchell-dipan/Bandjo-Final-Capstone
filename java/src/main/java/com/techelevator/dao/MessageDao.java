package com.techelevator.dao;



import com.techelevator.model.Message;

import java.util.List;

public interface MessageDao {

    public List<Message> findAllMessages(int user_id, String sortBy);

    public void sendMessage(String message, int bandId);

}
