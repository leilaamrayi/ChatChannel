package com.websocket.RadioChanel.controller;

import com.websocket.RadioChanel.model.Channel;
import com.websocket.RadioChanel.model.Message;
import com.websocket.RadioChanel.service.ChannelService;
import com.websocket.RadioChanel.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    ChannelService channelService;

    @GetMapping("messages")
    public List<Message> getAll(Long channelId) {
        return messageService.getAll(channelId);
    }

    @GetMapping("messages/{messagesId}")
    public Message get(@PathVariable long messageId) {
        return messageService.get(messageId);
    }

    @PostMapping("messages")
    public Message createMessage(@RequestBody Message message) {
        return messageService.create(message);
    }

    @DeleteMapping("messages/{messageId}")
    public void deleteMessageById(@PathVariable Long messageId) {
        messageService.deleteById(messageId);
    }

    @GetMapping("/sub/channels/")
    public List<Channel> getAll() {
        return channelService.getAll();
    }

}







