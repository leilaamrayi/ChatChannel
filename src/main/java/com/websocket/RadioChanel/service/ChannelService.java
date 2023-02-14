package com.websocket.RadioChanel.service;
import com.websocket.RadioChanel.handler.ChannelMessagesHandler;
import com.websocket.RadioChanel.model.Channel;
import com.websocket.RadioChanel.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {
    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    ChannelMessagesHandler channelMessagesHandler;

    public List<Channel> getAll() {
        return channelRepository.findAll();
    }

    public Channel get(Long channelId) {
        return channelRepository.findById(channelId).orElse(null);
    }

    public Channel create(Channel channel) {
        var savedChannel = channelRepository.save(channel);
        channelMessagesHandler.channelCreated(savedChannel);
        return savedChannel;
    }

    public void deleteById(Long channelId) {
        channelRepository.deleteById(channelId);
    }

}




