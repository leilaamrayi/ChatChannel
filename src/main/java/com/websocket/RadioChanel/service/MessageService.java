package com.websocket.RadioChanel.service;
import com.websocket.RadioChanel.model.Channel;
import com.websocket.RadioChanel.model.Message;
import com.websocket.RadioChanel.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public List<Message> getAll(Long channelId) {
        return messageRepository.findByChannelId(channelId);}

    public Message get(Long messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    public Message create(Message message) {
    return  messageRepository.save(message);

    }
    public void deleteById(Long messageId) {
        messageRepository.deleteById(messageId);
    }

}
