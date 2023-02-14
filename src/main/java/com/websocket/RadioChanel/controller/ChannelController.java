package com.websocket.RadioChanel.controller;

import com.websocket.RadioChanel.model.Channel;
import com.websocket.RadioChanel.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChannelController {
    @Autowired
    ChannelService channelService;

    @GetMapping("channels")

    public List<Channel> getAll() {
        return channelService.getAll();
    }

    @GetMapping("channels/{channelId}")
    public Channel get(@PathVariable long id) {
        return channelService.get(id);
    }

    @PostMapping("channels")
    public Channel create(@RequestBody Channel channel) {
        return channelService.create(channel);
    }

    @DeleteMapping("channels/{channelId}")
    public void deleteById(@PathVariable Long channelId) {
        channelService.deleteById(channelId);
    }


}




